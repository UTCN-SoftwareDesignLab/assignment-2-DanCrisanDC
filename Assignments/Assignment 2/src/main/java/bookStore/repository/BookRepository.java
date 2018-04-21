package bookStore.repository;

import bookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByNameAndAuthorAndGenre(String name, String author, String genre);
}