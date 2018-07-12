package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.pojo.hibernate.entities.Team;
import ru.innopolis.stc9.pojo.realisationJDBC.Group;
import ru.innopolis.stc9.service.hibernate.interfaces.GroupService;
import ru.innopolis.stc9.service.hibernate.interfaces.SpecialityService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GroupController {
    private static final Logger logger = Logger.getLogger(GroupController.class);
    private final GroupService groupService;
    private final SpecialityService specialityService;

    @Autowired
    public GroupController(GroupService groupService, SpecialityService specialityService) {
        this.groupService = groupService;
        this.specialityService = specialityService;
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.GET)
    public String addGroup(Model model) {
        int u = 0;
        List<Speciality> specialityList = specialityService.getAll();
        /*List<Program> listProgram = programService.getAll();
        model.addAttribute("listProgram", listProgram);*/
        return "/addGroup";
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public String addGroupPost(@RequestAttribute int cur_semester_education,
                               @RequestAttribute long program,
                               Model model) {
        int u = 0;
        /*int cur_semestr = Integer.parseInt(cur_semester_education);
        int prog = Integer.parseInt(program);
        Program p = programService.getById(prog);

        Group group = new Group(cur_semestr, p);
        groupService.add(group);
        model.addAttribute("group", group);*/
        return "redirect:groupAll";
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.GET)
    public String deleteGroupGet(@RequestAttribute Group group, Model model) {
        /*groupService.deleteById(group.getId());*/
        return "/redirect:personAll";
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
    public String deleteGroupPost(@RequestAttribute String id,
                                  Model model) {
        /*groupService.deleteById(Long.parseLong(id));
        logger.info("Group deleted");*/
        return "/deleteGroup";
    }


    @RequestMapping(value = "/groupAll", method = RequestMethod.GET)
    public String getAll(Model model) {
        String resultPage = "groupList";
        List<Team> groupList = groupService.getAllTeams();
        if (groupList != null) {
            model.addAttribute("groupList", groupList);
            model.addAttribute("yCurrent", LocalDate.now().getYear());
        } else {
            model.addAttribute("msg", "Мне кажется, у нас проблемы...");
            resultPage = "error";
        }
        return resultPage;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateGroupGet(@RequestAttribute Group group,
                                 Model model) {
        /*model.addAttribute("group", group);*/
        return "/updateGroup";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateGroupPost(@RequestAttribute String id,
                                  @RequestAttribute String cur_semester_education,
                                  @RequestAttribute String program,
                                  Model model) {
        /*int cur_semestr = Integer.parseInt(cur_semester_education);
        long prog = Long.parseLong(program);
        Program p = programService.getById(prog);
        Group group = new Group(cur_semestr, p);
        groupService.update(group);
        model.addAttribute("group", group);*/
        return "/addGroup";
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public String getGroup(@RequestAttribute long id, Model model) {
        Team group = groupService.getById(id);
        model.addAttribute("group", group);
        List<Person> students = groupService.getAllSuitPerson(group);
        model.addAttribute("students", students);
        return "/getGroup";
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public String addStudent(@RequestAttribute long groupId,
                             @RequestAttribute long selectedStudent,
                             Model model) {
        groupService.addStudentToTeam(groupId, selectedStudent);
        model.addAttribute("id", groupId);
        return "redirect:group";
    }

    @RequestMapping(value = "/delStudent", method = RequestMethod.GET)
    public String delStudent(@RequestAttribute long groupId,
                             @RequestAttribute long selectedStudent,
                             Model model) {
        groupService.delStudentFromTeam(selectedStudent);
        model.addAttribute("id", groupId);
        return "redirect:group";
    }


}
