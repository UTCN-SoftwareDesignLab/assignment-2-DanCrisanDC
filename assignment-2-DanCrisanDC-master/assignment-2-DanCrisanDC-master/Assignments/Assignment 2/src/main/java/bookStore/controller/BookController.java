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
import org.springframework.validation.BindingResult;
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
    public String createBook(@ModelAttribute @Valid BookDto bookDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "bookAdmin";
        }
        bookService.create(bookDto);
        model.addAttribute("message", "Added book successfully.");

        return "bookAdmin";
    }

    @PostMapping(params = "updateBook")
    public String updateBook(@ModelAttribute @Valid BookDto bookDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "bookAdmin";
        }
        bookService.update(bookDto);
        model.addAttribute("message", "Added book successfully.");

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

    @PostMapping(params = "getBook")
    public String getBook(@ModelAttribute @Valid BookDto bookDto, @RequestParam("name") String name, Model model) {

        Book book = bookService.findByName(name);
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setGenre(book.getGenre());
        bookDto.setPrice(book.getPrice());
        bookDto.setQuantity(book.getQuantity());
        bookDto.setId(book.getId());

        model.addAttribute("book", bookDto);
        return "bookAdmin";
    }

    @PostMapping(params = "pdfReport")
    public String generatePDFReport(@ModelAttribute BookDto bookDto) {

        reportService.generateReport("pdf");
        return "bookAdmin";
    }

    @PostMapping(params = "csvReport")
    public String generateCSVReport(@ModelAttribute BookDto bookDto) {

        reportService.generateReport("csv");
        return "bookAdmin";
    }
}
