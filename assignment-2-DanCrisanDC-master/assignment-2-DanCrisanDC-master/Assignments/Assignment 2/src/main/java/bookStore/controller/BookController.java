package bookStore.controller;

import bookStore.dto.BookDto;
import bookStore.model.Book;
import bookStore.service.report.ReportService;
import bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;

@Controller
@RequestMapping(value = "/bookAdmin")
public class BookController {

    private static final String APPLICATION_PDF = "application/pdf";
    private static final String APPLICATION_CSV = "application/txt";

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
        model.addAttribute("message", "Updated book successfully.");

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

    @RequestMapping(params = "csvReport", method = RequestMethod.POST)
    public @ResponseBody void downloadCSV(HttpServletResponse response) throws IOException {

        reportService.generateReport("csv");
        String filename = "D:\\Documents\\facultate\\anul 3\\SEM 2\\SD\\assignment-2-DanCrisanDC-master\\assignment-2-DanCrisanDC-master\\Assignments\\Assignment 2\\OutOfStockBooks.csv";

        File file = getFile(filename);
        InputStream in = new FileInputStream(file);
        response.setContentType(APPLICATION_CSV);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
    }

    @RequestMapping(params = "pdfReport", method = RequestMethod.POST)
    public @ResponseBody void downloadPDF(HttpServletResponse response) throws IOException {

        reportService.generateReport("pdf");
        String filename = "D:\\Documents\\facultate\\anul 3\\SEM 2\\SD\\assignment-2-DanCrisanDC-master\\assignment-2-DanCrisanDC-master\\Assignments\\Assignment 2\\OutOfStockBooks.pdf";

        File file = getFile(filename);
        InputStream in = new FileInputStream(file);
        response.setContentType(APPLICATION_PDF);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
    }

    private File getFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        if (!file.exists()){
            throw new FileNotFoundException("file was not found.");
        }
        return file;
    }

}
