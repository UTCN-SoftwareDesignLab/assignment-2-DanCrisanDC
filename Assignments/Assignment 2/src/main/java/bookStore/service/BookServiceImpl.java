package bookStore.service;

import bookStore.dto.BookDto;
import bookStore.model.Book;
import bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{


    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findByNameAndAuthorAndGenre(String name, String author, String genre) {
        return bookRepository.findByNameAndAuthorAndGenre(name, author, genre);
    }

    @Override
    public boolean create(BookDto bookDto) {
        Book book = new Book(bookDto.getName(), bookDto.getAuthor(), bookDto.getGenre(), bookDto.getPrice(), bookDto.getQuantity());
        bookRepository.save(book);
        return true;
    }
}
