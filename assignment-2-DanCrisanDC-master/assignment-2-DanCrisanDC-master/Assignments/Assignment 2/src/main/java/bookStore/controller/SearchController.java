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
    public String getPage(Model model) {

        model.addAttribute("bookDto", new BookDto());
        return "bookTest";
    }

    @PostMapping(params = "search")
    public String searchBooks(@RequestParam("name") String name, Model model) {

        List<Book> books = bookService.searchByField(name);
        model.addAttribute("book", books);
        return "resultsPage";
    }

    @PostMapping(params = "sell")
    public String sellBooks(@RequestParam("name") String name, @RequestParam("quantity") int quantity, Model model) {
        Book book = bookService.sell(name, quantity);

        if(book == null) {
            model.addAttribute("messageResult", "Could not complete selling.");
            return "resultsPage";
        } else {
            model.addAttribute(book);
            model.addAttribute("messageResult", "Selling performed successfully.");
            return "resultsPage";
        }
    }
}
