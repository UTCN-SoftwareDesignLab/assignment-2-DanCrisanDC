package bookStore.controller;

import bookStore.dto.BookDto;
import bookStore.model.Book;
import bookStore.service.report.Delegator;
import bookStore.service.report.ReportService;
import bookStore.service.report.ReportWriter;
import bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/bookAdmin")
public class BookController {

    private BookService bookService;
    private ReportService reportService;

    @Autowired
    public BookController(BookService bookService, ReportService reportService) {
        this.bookService = bookService;
        this.reportService = reportService;
    }

    @GetMapping()
    public String getPage(Model model) {
        model.addAttribute("bookDto", new BookDto());
        return "bookAdmin";
    }

    @PostMapping(params = "createBook")
    public String createBook(@ModelAttribute @Valid BookDto bookDto, Model model) {

        if(bookService.create(bookDto)) {
            model.addAttribute("message", "Added book successfully.");
        } else {
            model.addAttribute("message", "Failed to add book.");
        }
        return "bookAdmin";
    }

    @PostMapping(params = "updateBook")
    public String updateBook(@ModelAttribute @Valid BookDto bookDto, Model model) {

        if(bookService.update(bookDto)) {
            model.addAttribute("message", "Updated book successfully.");
        } else {
            model.addAttribute("message", "Failed to update book.");
        }
        return "bookAdmin";
    }

    @PostMapping(params = "deleteBook")
    public String deleteBook(@RequestParam("id") int id, @ModelAttribute BookDto bookDto, Model model) {

        if(bookService.delete(id)) {
            model.addAttribute("message", "Deleted book successfully.");
        } else {
            model.addAttribute("message", "Failed to delete book.");
        }
        return "bookAdmin";
    }

    @PostMapping(params = "bookReport")
    public String generateReport(@ModelAttribute BookDto bookDto) {

        reportService.generateReport("pdf");
        reportService.generateReport("csv");
        return "bookAdmin";
    }
}
