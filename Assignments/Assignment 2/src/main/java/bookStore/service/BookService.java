package bookStore.service;

import bookStore.dto.BookDto;
import bookStore.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> getAll();

    Book findByNameAndAuthorAndGenre(String name, String author, String genre);

    boolean create(BookDto bookDto);
}
