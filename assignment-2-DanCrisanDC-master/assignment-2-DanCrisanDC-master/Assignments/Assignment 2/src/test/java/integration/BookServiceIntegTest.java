package integration;

import bookStore.dto.BookDto;
import bookStore.model.Book;
import bookStore.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Configuration
@EnableJpaRepositories(basePackages = "bookStore.repository")
@PropertySource("classpath:application.properties")
@EntityScan(basePackages ={"bookStore.model"})
@ComponentScan({"bookStore.application", "bookStore.model", "bookStore.repository", "bookStore.service", "bookStore.controller", "bookStore.dto"})

public class BookServiceIntegTest {

    @Autowired
    private BookService bookService;

    @Before
    public void setup(){
        bookService.deleteAll();
    }

    @Test
    public void createBook(){
        BookDto bookDto = new BookDto();
        bookDto.setName("Test Book");
        bookService.create(bookDto);
        Assert.assertNotNull(bookService.findByName("Test Book"));
    }

    @Test
    public void findAll(){
        int size = bookService.getAll().size();

        BookDto bookDto1 = new BookDto();
        BookDto bookDto2 = new BookDto();

        bookService.create(bookDto1);
        bookService.create(bookDto2);
        Assert.assertTrue(bookService.getAll().size() == size+2);
    }
}
