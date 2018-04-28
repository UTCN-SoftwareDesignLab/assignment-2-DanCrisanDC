package bookStore.controller;

import bookStore.dto.BookDto;
import bookStore.dto.UserDto;
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
    public String adminPage() {
        return "adminPage";
    }

    @PostMapping(params = "adminBook")
    public String bookPage(@ModelAttribute @Valid BookDto bookDto) {
        return "redirect:/bookAdmin";
    }

    @PostMapping(params = "adminUser")
    public String userPage(@ModelAttribute @Valid UserDto userDto) {
        return "redirect:/userAdmin";
    }
}
