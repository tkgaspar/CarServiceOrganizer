package com.ownproject.ServiceOrganizer.Controller;

import com.ownproject.ServiceOrganizer.Model.ScheduleForm;
import com.ownproject.ServiceOrganizer.Services.RepReqService;
import com.ownproject.ServiceOrganizer.Services.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScheduleController {

    private RepReqService repReqService;
    private ScheduleService scheduleService;

    public ScheduleController(RepReqService repReqService, ScheduleService scheduleService) {
        this.repReqService = repReqService;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedule")
    public String scheduleView(@ModelAttribute("scheduleForm")ScheduleForm scheduleForm, Model model) {
        model.addAttribute("UnscheduledRequests",repReqService.getRepReqList(01));
        model.addAttribute("ScheduledRepairs",scheduleService.getAllSchedules());
        model.addAttribute("AvailableMechanics",scheduleService.allMechanics());
        return "schedule";
    }

    @PostMapping("/schedule")
    public ModelAndView scheduleRepair(ScheduleForm scheduleForm, ModelMap attributes){
        if(scheduleForm.getScheduleId()==null){
            if(this.scheduleService.addSchedule(scheduleForm)==1){
                attributes.addAttribute("ScheduledRepairs",scheduleService.getAllSchedules());
                attributes.addAttribute("AvailableMechanics",scheduleService.allMechanics());
               attributes.addAttribute("UnscheduledRequests",repReqService.getRepReqList(01));
          /*  }else{
                attributes.addAttribute("noteUploadErrorBool", true);
                attributes.addAttribute("noteUploadError", "Something went wrong, please try again!");
            */
            }
        }else{
            this.scheduleService.updateSchedule(scheduleForm);
            attributes.addAttribute("ScheduledRepairs",scheduleService.getAllSchedules());
            attributes.addAttribute("AvailableMechanics",scheduleService.allMechanics());
           attributes.addAttribute("UnscheduledRequests",repReqService.getRepReqList(1));
        }
        return new ModelAndView("schedule",attributes);
    }


}
