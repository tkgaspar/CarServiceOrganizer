package com.ownproject.ServiceOrganizer.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/scheduler")
public class ScheduleController {

    @GetMapping()
    public String scheduleView() {
        return "scheduler";
    }


}
