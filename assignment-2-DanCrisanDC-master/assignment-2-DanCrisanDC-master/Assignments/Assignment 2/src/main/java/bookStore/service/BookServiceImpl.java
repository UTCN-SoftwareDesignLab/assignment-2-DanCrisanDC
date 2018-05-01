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
    public List<Book> findByNameOrAuthorOrGenre(String name, String author, String genre) {
        return bookRepository.findByNameOrAuthorOrGenre(name, author, genre);
    }

    @Override
    public Book findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public boolean delete(int id) {
        bookRepository.deleteById(id);
        if(bookRepository.findById(id) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean update(BookDto bookDto) {

        Book book = bookRepository.findById(bookDto.getId()).get();
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setPrice(bookDto.getPrice());
        book.setQuantity(bookDto.getQuantity());
        bookRepository.save(book);

        if(book != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean create(BookDto bookDto) {
        Book book = new Book(bookDto.getName(), bookDto.getAuthor(), bookDto.getGenre(), bookDto.getPrice(), bookDto.getQuantity());
        bookRepository.save(book);
        if(book != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Book sell(String name, int quantity) {
        Book book = bookRepository.findByName(name);

        if(quantity >= 0) {
            if (book.getQuantity() - quantity > 0) {
                book.setQuantity(book.getQuantity() - quantity);
            } else {
                return null;
            }
            bookRepository.save(book);
            return book;
        } else {
            return null;
        }
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public List<Book> searchByField(String field) {
        return bookRepository.findByField(field);
    }
}
