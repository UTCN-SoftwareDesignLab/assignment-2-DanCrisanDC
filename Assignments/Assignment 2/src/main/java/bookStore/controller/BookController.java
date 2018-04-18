package bookStore.controller;

import bookStore.model.Book;
import bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/bookTest")
    String test(Model model) {
        //final List<Book> books = bookService.getAll();
        //model.addAttribute("bookCount", books.size());
        return "bookTest";
    }
}
