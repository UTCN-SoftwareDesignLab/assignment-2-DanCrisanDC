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
    @Autowired
    private BookRepository bookRepository;

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
