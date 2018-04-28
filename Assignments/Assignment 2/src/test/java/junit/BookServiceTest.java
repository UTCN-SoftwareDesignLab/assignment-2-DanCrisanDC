package junit;

import bookStore.dto.BookDto;
import bookStore.model.Book;
import bookStore.repository.BookRepository;
import bookStore.service.BookService;
import bookStore.service.BookServiceImpl;
import bookStore.service.report.ReportService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class BookServiceTest {

    private BookService bookService;
    private ReportService reportService;
    @Mock
    private BookRepository bookRepository;


    @Before
    public void setup() {
        this.bookService = new BookServiceImpl(bookRepository);

        Book book1 = new Book("Test", "Dan", "Comedy", 10.0,5);
        Book book2 = new Book("Facultate", "Crisan", "Drama", 12.5, 0);

        List<Book> books1 = new ArrayList<>();
        books1.add(book1);
        books1.add(book2);

        Mockito.when(bookRepository.findByNameOrAuthorOrGenre("Test","Crisan","")).thenReturn(books1);

        List<Book> books2 = new ArrayList<>();
        books2.add(book2);
        Mockito.when(bookRepository.findAllByQuantity(0)).thenReturn(books2);
    }

    @Test
    public void findByNameOrAuthorOrGenre(){
        List<Book> books = bookService.findByNameOrAuthorOrGenre("Test","Crisan","");
        Assert.assertEquals(books.size(), 2);
    }

    @Test
    public void findAllByQuantity(){
        List<Book> books = reportService.findOutOfStock();
        Assert.assertEquals(books.size(), 1);
    }
}
