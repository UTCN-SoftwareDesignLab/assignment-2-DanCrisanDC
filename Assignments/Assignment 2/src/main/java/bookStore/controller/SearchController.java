package bookStore.controller;

import bookStore.dto.BookDto;
import bookStore.model.Book;
import bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping (value = "/bookTest")
public class SearchController {

    private BookService bookService;

    @Autowired
    public SearchController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public String getPage() {
        return "bookTest";
    }

    @PostMapping(params = "search")
    public String searchBook(@RequestParam("name") String name, @RequestParam("author") String author, @RequestParam("genre") String genre, Model model) {

        List<Book> books = bookService.findByNameOrAuthorOrGenre(name, author, genre);
        model.addAttribute("book", books);
        return "resultsPage";
    }

    @PostMapping(params = "sell")
    public String sellBooks(@RequestParam("name") String name, @RequestParam("author") String author, @RequestParam("genre") String genre, @RequestParam("quantity") int quantity, Model model) {
        Book book = bookService.sell(name, author, genre, quantity);

        if(book == null) {
            List<Book> books = bookService.findByNameOrAuthorOrGenre(name, author, genre);
            model.addAttribute("book", books);
            model.addAttribute("message", "Could not complete selling.");
            return "resultsPage";
        } else {
            model.addAttribute(book);
            model.addAttribute("message", "Selling performed successfully.");
            return "resultsPage";
        }
    }
}
