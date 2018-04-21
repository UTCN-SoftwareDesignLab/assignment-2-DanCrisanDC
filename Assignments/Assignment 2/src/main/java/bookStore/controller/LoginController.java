package bookStore.controller;

import bookStore.dto.BookDto;
import bookStore.dto.UserDto;
import bookStore.service.AuthenticationService;
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


    private AuthenticationService authenticationService;

    @Autowired
    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping() // ce pagina caut
    public String loginForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("bookDto", new BookDto());
        return "authentication"; // ce pagina primesc
    }

    @PostMapping(params = "register") // la ce adresa
    public String registerUser(@ModelAttribute @Valid UserDto userDto, Model model, BindingResult bindingResult) {
        //if(authenticationService.create(userDto)) {
            //model.addAttribute("message", "User created succesfully!");
        authenticationService.create(userDto);
        return "authentication"; // ce pagina returnez
    }

    @PostMapping(params = "login") // la ce adresa
    public String loginUser(@ModelAttribute @Valid UserDto userDto, @ModelAttribute @Valid BookDto bookDto) {

        if(userDto.getUsername().equals("admin123")) {
            return "adminPage";
        }
        else
            return "bookTest";
    }
}
