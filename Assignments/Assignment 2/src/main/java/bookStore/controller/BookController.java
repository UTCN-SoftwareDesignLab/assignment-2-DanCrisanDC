package bookStore.controller;

import bookStore.dto.BookDto;
import bookStore.service.AuthenticationService;
import bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/bookAdmin")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public String getPage(Model model) {
        model.addAttribute("bookDto", new BookDto());
        return "bookAdmin";
    }

    @PostMapping(params = "createBook")
    public String showPage(@ModelAttribute @Valid BookDto bookDto) {
        bookService.create(bookDto);
        return "bookAdmin";
    }
}
