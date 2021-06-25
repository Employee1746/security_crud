package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.userService.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/details/{id}")
    public String userDetails(@PathVariable("id") Long id, Model model, Principal principal) {
        User user = userService.getUserById(id);
        if (user.getUsername().equals(principal.getName())) {
            model.addAttribute("user", userService.getUserById(id));
            return "detailsForUser";
        }
        return "redirect:/login";

    }

    @GetMapping("user/profile")
    public String homePage(Principal principal, Model model) {
        Long id = userService.getUserByName(principal.getName()).getId();

        model.addAttribute("id", id);
        return "profile";
    }

    @GetMapping
    public String index() {
        return "index";
    }
    @GetMapping("/logout")
    public String logout(){
        return "/logout";
    }
}
