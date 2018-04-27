package bookStore.service.report;

import bookStore.model.Book;
import bookStore.repository.BookRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class PDFReportWriter implements ReportWriter {

    private BookRepository bookRepository;

    @Autowired
    public PDFReportWriter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createReport(List<Book> books) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document,page);
            PDFont font = PDType1Font.TIMES_ROMAN;
            contentStream.beginText();
            contentStream.setFont(font,12);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(50, 700);

            for (Book book : books) {

                contentStream.showText("id ="+ book.getId());
                contentStream.newLine();
                contentStream.showText("title ="+book.getName());
                contentStream.newLine();
                contentStream.showText("author ="+book.getAuthor());
                contentStream.newLine();
                contentStream.showText("genre ="+book.getGenre());
                contentStream.newLine();
                contentStream.showText("price ="+book.getPrice());
                contentStream.newLine();
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();

            document.save("D:\\Documents\\facultate\\anul 3\\SEM 2\\SD\\Assignments\\Assignment 2\\OutOfStockBooks.pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
