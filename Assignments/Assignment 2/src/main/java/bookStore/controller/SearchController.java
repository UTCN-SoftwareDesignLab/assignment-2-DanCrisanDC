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
    public String getPage() {//Model model) {
        //model.addAttribute("book", new Book());
        return "bookTest";
    }

    @PostMapping(params = "search")
    public String showPage(@RequestParam("name") String name, @RequestParam("author") String author, @RequestParam("genre") String genre, Model model) {
        Book book = bookService.findByNameAndAuthorAndGenre(name, author, genre);
        model.addAttribute(book);
        return "resultsPage";
    }
}
