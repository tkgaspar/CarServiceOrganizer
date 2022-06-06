package com.ownproject.ServiceOrganizer.Controller;

import com.ownproject.ServiceOrganizer.Model.Role;
import com.ownproject.ServiceOrganizer.Model.User;
import com.ownproject.ServiceOrganizer.Model.UsersListForm;
import com.ownproject.ServiceOrganizer.Services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

	
    private final UserService userService;

    @ModelAttribute
    public UsersListForm usersListForm() {
        return new UsersListForm();
    }

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAdminPage(Authentication auth, Model model) {
        model.addAttribute("allUsers", userService.getAllUsersWithRoles());
        model.addAttribute("allRoles", userService.allRoles());
        return "admin";
    }

    @GetMapping("/userslist/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserWithRolesById(id);
        Set<Role> allRoles = new HashSet<>(userService.allRoles());
        model.addAttribute("user", user);
        model.addAttribute("allRoles", allRoles);
        return "user_form";
    }


    @PostMapping("/userslist/save")
    public String saveUser(User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

}


