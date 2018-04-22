package bookStore.controller;

import bookStore.dto.BookDto;
import bookStore.model.Book;
import bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        Book book = bookService.findByNameAndAuthorAndGenre(name, author, genre);
        model.addAttribute(book);
        return "resultsPage";
    }

    @PostMapping(params = "sell")
    public String sellBooks(@RequestParam("name") String name, @RequestParam("author") String author, @RequestParam("genre") String genre, @RequestParam("quantity") int quantity, Model model) {
        Book book = bookService.sell(name, author, genre, quantity);
        model.addAttribute(book);
        return "resultsPage";
    }
}
