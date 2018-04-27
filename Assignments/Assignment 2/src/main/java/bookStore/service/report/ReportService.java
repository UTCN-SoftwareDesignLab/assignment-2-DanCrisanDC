package bookStore.service.report;

import bookStore.model.Book;

import java.util.List;

public interface ReportService {

    void generateReport(String type);

    List<Book> findOutOfStock();
}
