package bookStore.repository;

import bookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNameOrAuthorOrGenre(String name, String author, String genre);
    List<Book> findAllByQuantity(int quantity);
    Book findByName(String name);
    @Query("SELECT b FROM Book b WHERE b.name LIKE %:field% OR b.author LIKE %:field% OR b.genre LIKE %:field%")
    List<Book> findByField(@Param("field") String field);
}