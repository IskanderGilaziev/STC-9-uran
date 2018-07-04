package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.realisationJDBC.Group;
import ru.innopolis.stc9.pojo.hibernate.entities.Program;
import ru.innopolis.stc9.service.IGroupService;
import ru.innopolis.stc9.service.IProgramService;

import java.util.List;

public class GroupController {
    private static final Logger logger = Logger.getLogger(GroupController.class);
    @Autowired
    private IGroupService service;
    @Autowired
    private IProgramService programService;

    @RequestMapping(value = "/addGroup", method = RequestMethod.GET)
    public String addGroup(Model model) {
        List<Program> listProgram = programService.getAll();
        model.addAttribute("listProgram", listProgram);
        return "/addGroup";
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public String addGroupPost(@RequestAttribute String cur_semester_education,
                               @RequestAttribute String program,
                               Model model) {
        int cur_semestr = Integer.parseInt(cur_semester_education);
        int prog = Integer.parseInt(program);
        Program p = programService.getById(prog);

        Group group = new Group(cur_semestr, p);
        service.add(group);
        model.addAttribute("group", group);
        return "redirect:groupAll";
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.GET)
    public String deleteGroupGet(@RequestAttribute Group group, Model model) {
        service.deleteById(group.getId());
        return "/redirect:personAll";
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
    public String deleteGroupPost(@RequestAttribute String id,
                                  Model model) {
        service.deleteById(Long.parseLong(id));
        logger.info("Group deleted");
        return "/deleteGroup";
    }


    @RequestMapping(value = "/groupAll", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Group> groupList = service.getAll();
        if (groupList != null) {
            model.addAttribute("groupList", groupList);
            return "/groupList";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateGroupGet(@RequestAttribute Group group,
                                 Model model) {
        model.addAttribute("group", group);
        return "/updateGroup";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateGroupPost(@RequestAttribute String id,
                                  @RequestAttribute String cur_semester_education,
                                  @RequestAttribute String program,
                                  Model model) {
        int cur_semestr = Integer.parseInt(cur_semester_education);
        long prog = Long.parseLong(program);
        Program p = programService.getById(prog);
        Group group = new Group(cur_semestr, p);
        service.update(group);
        model.addAttribute("group", group);
        return "/addGroup";
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public String getGroup(@RequestAttribute String id, Model model) {
        Group group = service.getById(Integer.parseInt(id));
        model.addAttribute("group", group);
        return "/getGroup";
    }
}
