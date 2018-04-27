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

    @GetMapping() // ce pagina caut
    public String authenticationPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("bookDto", new BookDto());
        return "authentication"; // ce pagina primesc
    }

    @PostMapping(params = "register") // la ce adresa
    public String registerUser(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "authentication";
        }
        userService.create(userDto);
        return "authentication"; // ce pagina returnez
    }

    @PostMapping(params = "login")
    public String loginUser(@ModelAttribute @Valid UserDto userDto, @ModelAttribute @Valid BookDto bookDto, Model model) {

        User user = userService.findByUsernameAndPassword(userDto.getUsername(),userDto.getPassword());
        if(user != null) {
            if (user.getRole().equals(Role.ADMIN)) {
                return "adminPage";
            } else {
                return "bookTest";
            }
        } else {
            model.addAttribute("message", "User not found");
            return "authentication";
        }
    }
}
