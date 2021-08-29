package com.ownproject.ServiceOrganizer.Controller;

import com.ownproject.ServiceOrganizer.Model.RepRequest;
import com.ownproject.ServiceOrganizer.Model.RepRequestForm;
import com.ownproject.ServiceOrganizer.Model.User;
import com.ownproject.ServiceOrganizer.Services.RepReqService;
import com.ownproject.ServiceOrganizer.Services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RepRequestController {

    private RepReqService repReqService;
    private UserService userService;

    public RepRequestController(RepReqService repReqService, UserService userService) {
        this.repReqService = repReqService;
        this.userService = userService;
    }


    @GetMapping("/repRequest")
    public String getNoteList(@ModelAttribute("repRequestForm") RepRequestForm repRequestForm, Model model) {
        model.addAttribute("SavedRepairRequests", repReqService.getRepReqList(repRequestForm.getUserId()));
        return "home";
    }


    @PostMapping("/repRequest")
    public ModelAndView addRepRequest(RepRequestForm repRequestForm, Authentication auth, ModelMap attributes) {
        User user = this.userService.getUser(auth.getName());
        if (repRequestForm.getRepReqId() == null) {
            if(this.repReqService.addRepReq(repRequestForm, user.getUserId())==1) {
                attributes.addAttribute("noteUploadSuccessBool", true);
                attributes.addAttribute("noteUploadSuccess", "Your note has been saved successfully ! ");
                attributes.addAttribute("SavedRepairRequests", repReqService.getRepReqList(repRequestForm.getUserId()));
            }else{
                attributes.addAttribute("noteUploadErrorBool", true);
                attributes.addAttribute("noteUploadError", "Something went wrong, please try again!");
            }
        } else{
            this.repReqService.updateNote(repRequestForm);
            attributes.addAttribute("noteUploadSuccessBool", true);
            attributes.addAttribute("noteUploadSuccess", "Your note has been saved successfully ! ");
            attributes.addAttribute("SavedRepairRequests", repReqService.getRepReqList(repRequestForm.getUserId()));

        }
        return new ModelAndView("forward:/result", attributes);
    }

    @GetMapping("/repRequest-delete")
    public ModelAndView deleteNote(@ModelAttribute("repRequestForm") RepRequestForm repRequestForm, Authentication auth, ModelMap attributes) {
        User user = this.userService.getUser(auth.getName());
        for (RepRequest repRequest : this.repReqService.getRepReqList(user.getUserId())) {
            if (repRequest.getClientName().equals(repRequestForm.getClientName())) {
                if(this.repReqService.deleteRepReq(repRequest.getClientName(), user.getUserId())==1){
                    attributes.addAttribute("noteUploadSuccessBool",true);
                    attributes.addAttribute("noteUploadSuccess","Your note has been deleted! ");
                }else{
                    attributes.addAttribute("noteUploadsErrorBool",true);
                    attributes.addAttribute("noteUploadError","Something went wrong, please try again! ");
                }

            }
        }
        attributes.addAttribute("SavedRepairRequests", repReqService.getRepReqList(repRequestForm.getUserId()));
        return new ModelAndView("forward:/result", attributes);
    }

    @GetMapping("/result")
    public String getResultPage() {
        return "result";
    }

    @PostMapping("/result")
    public String postResultPage() {
        return "result";
    }

}
