package bookStore.controller;

import bookStore.dto.BookDto;
import bookStore.dto.UserDto;
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
@RequestMapping(value = "/adminPage")
public class AdminController {

    @GetMapping()
    public String adminPage(Model model) {
        return "adminPage";
    }

    @PostMapping(params = "adminBook")
    public String bookPage(@ModelAttribute @Valid BookDto bookDto) {
        return "bookAdmin";
    }

    @PostMapping(params = "adminUser")
    public String userPage(@ModelAttribute @Valid UserDto userDto) {
        return "userAdmin";
    }

//    @PostMapping(params = "adminReport")
//    public String reportPage(Model model) {
//        return "reportAdmin";
//    }
}
