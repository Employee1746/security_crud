package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.services.RoleService;
import web.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> userList = userService.getAllUser();
        model.addAttribute("users", userList);
        return "all-users";
    }

    @GetMapping("/create")
    public String createForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getRolesList());
        return "createUser-form";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam("chosenRoles") String[] chosenRoles) {
        user.setRoles(roleService.getRolesFromArray(chosenRoles));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("details/{id}")
    public String adminDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "detailsForAdmin";
    }

    @GetMapping("/user/details/{id}")
    public String userDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "detailsAll";
    }

    @GetMapping()
    public String homePage(Principal principal, Model model) {
        Long id = userService.getUserByName(principal.getName()).getId();
        model.addAttribute("id", id);
        return "profileAdmin";
    }

    @GetMapping("/user/update/{id}")
    public String updateUserForm(@PathVariable("id") Long id,
                                 Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRolesList());
        return "/user-update";
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("updatedRoles") String[] updatedRoles) {
        user.setRoles(roleService.getRolesFromArray(updatedRoles));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
