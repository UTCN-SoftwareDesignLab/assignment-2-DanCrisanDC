package bookStore.repository;

import bookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNameOrAuthorOrGenre(String name, String author, String genre);
    List<Book> findAllByQuantity(int quantity);
}