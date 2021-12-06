package com.ownproject.ServiceOrganizer.Controller;

import com.ownproject.ServiceOrganizer.Model.Role;
import com.ownproject.ServiceOrganizer.Model.User;
import com.ownproject.ServiceOrganizer.Model.UsersListForm;
import com.ownproject.ServiceOrganizer.Services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @ModelAttribute
    public UsersListForm usersListForm() {
        return new UsersListForm();
    }

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAdminPage(Authentication auth, Model model) {
        User user = userService.getUser(auth.getName());
        model.addAttribute("allUsers", userService.getAllUsersWithRoles());
        model.addAttribute("allRoles", userService.allRoles());
        System.out.println("Get request reaches controller :admin");
        userService.allRoles().forEach(i->System.out.println("roles and id.s: "+i.getId()+", and it's name: "+i.getName()));
        return "admin";
    }

    @GetMapping("/userslist/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserWithRolesById(id);
        Set<Role> allRoles = userService.allRoles();
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


