package bookStore.service.report;

import bookStore.model.Book;
import bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class CSVReportWriter implements ReportWriter {

    private BookRepository bookRepository;

    @Autowired
    public CSVReportWriter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createReport(List<Book> books) {

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("OutOfStockBooks.csv");
            fileWriter.append("id, title, author, genre, price");
            fileWriter.append("\n");

            for(Book book : books) {
                fileWriter.append(book.getId() + "");
                fileWriter.append(",");
                fileWriter.append(book.getName() + "");
                fileWriter.append(",");
                fileWriter.append(book.getAuthor() + "");
                fileWriter.append(",");
                fileWriter.append(book.getGenre() + "");
                fileWriter.append(",");
                fileWriter.append(book.getPrice() + "");
                fileWriter.append(",");

                fileWriter.append("\n");
            }

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
