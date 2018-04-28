package bookStore.service.report;

import bookStore.model.Book;
import bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{

    private ReportWriter reportWriter;
    @Autowired
    private Delegator delegator;
    private BookRepository bookRepository;

    @Autowired
    public ReportServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void generateReport(String type){
        reportWriter=delegator.chooseReportType(type);
        reportWriter.createReport(findOutOfStock());
    }

    @Override
    public List<Book> findOutOfStock() {
        return bookRepository.findAllByQuantity(0);
    }

}
