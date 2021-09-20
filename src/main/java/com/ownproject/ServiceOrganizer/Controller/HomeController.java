package com.ownproject.ServiceOrganizer.Controller;


import com.ownproject.ServiceOrganizer.Model.RepRequestForm;
//import com.udacity.jwdnd.course1.cloudstorage.Model.NoteForm;
//import com.udacity.jwdnd.course1.cloudstorage.Model.StorageForm;
import com.ownproject.ServiceOrganizer.Model.User;
//import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
//import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.ownproject.ServiceOrganizer.Services.RepReqService;
import com.ownproject.ServiceOrganizer.Services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/home")
public class HomeController {


    private RepReqService repReqService;
    private UserService userService;

    public HomeController(RepReqService repReqService, UserService userService) {

        this.repReqService = repReqService;
        this.userService = userService;

    }

 /*   @ModelAttribute
    public StorageForm getStorageForm() {
        return new StorageForm();
    }*/
    @ModelAttribute
    public RepRequestForm noteForm(){
        return new RepRequestForm();
    }
  /*  @ModelAttribute
    public CredentialForm credentialForm(){return new CredentialForm();}*/

    @GetMapping()
    public String getHomePage(Authentication auth, Model model) {
        User user = userService.getUser(auth.getName());
        model.addAttribute("SavedRepairRequests", repReqService.getRepReqList(user.getUserId()));
        System.out.println("app enters @GetMapping in HomeController");
        return "home";
    }

    @PostMapping
    public String postHomePage(Authentication auth, Model model) {
        User user = userService.getUser(auth.getName());
        model.addAttribute("SavedRepairRequests", repReqService.getRepReqList(user.getUserId()));
        return "home";
    }

    @PostMapping("/logout")
    public String logoutView(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}