package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.realisationJDBC.Group;
import ru.innopolis.stc9.pojo.realisationJDBC.Status;
import ru.innopolis.stc9.service.IScheduleService;

import java.util.List;

@Controller
public class ScheduleController {
    private static final Logger logger = Logger.getLogger(ScheduleController.class);
    @Autowired
    private IScheduleService scheduleService;

    /**
     * Manage a main page showing select form for groups with different education status
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/seeScheduleGroupsInProgress", method = RequestMethod.GET)
    public String selectGroupStatus(Model model) {
        return "/selectStatusOfGroups";
    }

    @RequestMapping(value = "/showListOfGroups", method = RequestMethod.POST)
    public String showGroupsByStatus(@RequestAttribute Status status,
                                     Model model) {
        model.addAttribute("status", status);
        model.addAttribute("links", scheduleService.collectLinksOnPage(status));
        List<Group> groups = scheduleService.getGroupsByStatus(status);
        model.addAttribute("groups", groups);
        model.addAttribute("desktop", scheduleService.getMainSchedule(groups));
        return "/seeStatus";
    }
}
