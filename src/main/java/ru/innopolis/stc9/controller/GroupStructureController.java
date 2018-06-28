package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.realisationJDBC.GroupStructure;
import ru.innopolis.stc9.service.IGroupStructureService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GroupStructureController {
    private static final Logger logger = Logger.getLogger(GroupController.class);
    @Autowired
    private IGroupStructureService service;

    @RequestMapping(value = "/addGroupStructure", method = RequestMethod.GET)
    public String addGroup(HttpServletRequest request, Model model) {
        return "/addGroupStructure";
    }

    @RequestMapping(value = "/addGroupStructure", method = RequestMethod.POST)
    public String addGroupPost(HttpServletRequest request,
                               @RequestAttribute String student_item,
                               @RequestAttribute String group_item, Model model) {

        int studentItem = Integer.parseInt(student_item);
        int groupItem = Integer.parseInt(group_item);
        GroupStructure group = new GroupStructure(studentItem, groupItem);
        service.add(group);
        model.addAttribute("group", group);
        return "/getGroupStructure";
    }

    @RequestMapping(value = "/deleteGroupStructure", method = RequestMethod.GET)
    public String deleteGroup(HttpServletRequest request,
                              @RequestAttribute GroupStructure group, Model model) {
        service.deleteById(group.getId());
        return "/groupStructureList";
    }

    @RequestMapping(value = "/deleteGroupStructure", method = RequestMethod.POST)
    public String deleteGroupPost(HttpServletRequest request,
                                  @RequestAttribute String id,
                                  Model model) {
//        service.deleteById(group.getId());
        service.deleteById(Long.parseLong(id));
        logger.info("GroupStructure deleted");
        return "/deleteGroupStructure";
    }

    @RequestMapping(value = "/groupAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<GroupStructure> groupList = service.getAll();
        if (groupList != null) {
            model.addAttribute("groupStructureList", groupList);
            return "/groupListStructure";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/updateGroupStructure", method = RequestMethod.GET)
    public String updateGroup(HttpServletRequest request,
                              @RequestAttribute GroupStructure group, Model model) {
        model.addAttribute("group", group);
        return "/updateGroupStructure";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateGroup2(HttpServletRequest request,
                               @RequestAttribute String id,
                               @RequestAttribute String student_item,
                               @RequestAttribute String group_item, Model model) {

        int studentID = Integer.parseInt(id);
        int studentItem = Integer.parseInt(student_item);
        int groupItem = Integer.parseInt(group_item);
        GroupStructure group = new GroupStructure(studentID, studentItem, groupItem);
        service.update(group);
        model.addAttribute("group", group);
        return "/getGroupStructure";
    }

    @RequestMapping(value = "/groupStructure", method = RequestMethod.GET)
    public String getGroup(HttpServletRequest request,
                           @RequestAttribute String id, Model model) {
        GroupStructure group = service.getById(Long.parseLong(id));
        model.addAttribute("group", group);
        return "/getGroupStructure";
    }
}
