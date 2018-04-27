package bookStore.service.report;

import bookStore.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReportWriter {

    void createReport(List<Book> books);
}
