package bookStore.controller;

import bookStore.dto.UserDto;
import bookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping (value = "/userAdmin")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "userAdmin";
    }

    @PostMapping(params = "createUser")
    public String createUser(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "userAdmin";
        }
        userService.create(userDto);
        model.addAttribute("message", "User created successfully.");
        return "userAdmin";
    }

    @PostMapping(params = "updateUser")
    public String updateUser(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "userAdmin";
        }
        userService.update(userDto);
        model.addAttribute("message", "User updated successfully.");
        return "userAdmin";
    }

    @PostMapping(params = "deleteUser")
    public String deleteUser(@RequestParam("id") int id, @ModelAttribute UserDto userDto, Model model) {

        if(userService.delete(id)) {
            model.addAttribute("message", "Deleted user successfully.");
        } else {
            model.addAttribute("message", "Failed to delete user.");
        }
        return "userAdmin";
    }
}
