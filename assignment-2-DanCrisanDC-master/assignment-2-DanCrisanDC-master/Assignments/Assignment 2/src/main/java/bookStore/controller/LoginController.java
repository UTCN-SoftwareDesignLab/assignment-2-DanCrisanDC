package bookStore.controller;

import bookStore.dto.BookDto;
import bookStore.dto.UserDto;
import bookStore.model.Role;
import bookStore.model.User;
import bookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/authentication")
public class LoginController {


    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String authenticationPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("bookDto", new BookDto());
        return "authentication";
    }

    @PostMapping(params = "login")
    public String loginUser(@ModelAttribute @Valid UserDto userDto, @ModelAttribute @Valid BookDto bookDto, Model model) {

        User user = userService.findByUsernameAndPassword(userDto.getUsername(),userDto.getPassword());
        if(user != null) {
            if (user.getRole().equals(Role.ADMIN)) {
                return "redirect:/adminPage";
            } else {
                return "redirect:/bookTest";
            }
        } else {
            model.addAttribute("message", "User not found");
            return "authentication";
        }
    }
}
