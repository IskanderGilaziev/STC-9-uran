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
import ru.innopolis.stc9.service.hibernate.interfaces.GroupService;
import ru.innopolis.stc9.service.hibernate.interfaces.SpecialityService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
public class GroupController {
    private static final Logger logger = Logger.getLogger(GroupController.class);
    private static final String BEFORE = "First line. Input argument(-s):";
    private static final String ARG_GROUP_ID = "groupId = ";
    private static final String ARG_ID = "id = ";
    private final GroupService groupService;
    private final SpecialityService specialityService;

    @Autowired
    public GroupController(GroupService groupService, SpecialityService specialityService) {
        this.groupService = groupService;
        this.specialityService = specialityService;
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.GET)
    public String addGroup(Model model) {
        logger.debug(BEFORE + "-");
        List<Speciality> specialityList = specialityService.getAllActive();
        model.addAttribute("yCurrent", LocalDate.now().getYear());
        model.addAttribute("specialityList", specialityList);
        return "/addGroup";
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public String addGroupPost(@RequestAttribute String nameGroup,
                               @RequestAttribute int yStart,
                               @RequestAttribute long specialityId,
                               Model model) {
        logger.debug(new StringBuffer(BEFORE).append(" nameGroup = ").append(nameGroup).append(", yStart = ").append(yStart).append(", specialityId = ").append(specialityId));
        groupService.addNewGroup(nameGroup, yStart, specialityId);
        return "redirect:groupAll";
    }

    @RequestMapping(value = "/updateGroup", method = RequestMethod.POST)
    public String updateGroupPost(@RequestAttribute long groupId,
                                  @RequestAttribute String nameGroup,
                                  @RequestAttribute int yStart,
                                  @RequestAttribute long specialityId,
                                  Model model) {
        logger.debug(new StringBuffer(BEFORE).append(ARG_GROUP_ID).append(groupId).append(", nameGroup = ").append(nameGroup).append(", yStart = ").append(yStart).append(", specialityId = ").append(specialityId));
        groupService.updateExitingGroup(groupId, nameGroup, yStart, specialityId);
        return "redirect:groupAll";
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.GET)
    public String deleteGroupGet(@RequestAttribute long id, Model model) {
        logger.debug(BEFORE + ARG_ID + id);
        groupService.deleteById(id);
        return "redirect:groupAll";
    }

    @RequestMapping(value = "/groupAll", method = RequestMethod.GET)
    public String getAll(Model model) {
        logger.debug(BEFORE + "-");
        List<Team> groupList = groupService.getAllTeams();
        if (groupList != null) {
            model.addAttribute("groupList", groupList);
            model.addAttribute("yCurrent", LocalDate.now().getYear());
            return "groupList";
        } else {
            return showErrorPage("Мне кажется, у нас проблемы...", model);
        }
    }

    @RequestMapping(value = "/updateGroup", method = RequestMethod.GET)
    public String updateGroupGet(@RequestAttribute long id,
                                 Model model) {
        logger.debug(BEFORE + ARG_ID + id);
        Team group = groupService.getById(id);
        if (group != null) {
            model.addAttribute("group", group);
            List<Speciality> specialityList = specialityService.getSuitSpeciality(group);
            model.addAttribute("specialityList", specialityList);
            return "addGroup";
        } else {
            return showErrorPage("Запрашиваемые данные не найдены.", model);
        }
    }

    /**
     * Страница с ошибкой
     *
     * @param s
     * @param model
     * @return
     */
    private String showErrorPage(String s, Model model) {
        logger.debug(BEFORE + "s = " + s);
        model.addAttribute("msg", s);
        return "error";
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public String getGroup(@RequestAttribute long id, Model model) {
        logger.debug(BEFORE + ARG_ID + id);
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
        logger.debug(BEFORE + ARG_GROUP_ID + groupId + ", selectedStudent = " + selectedStudent);
        groupService.addStudentToTeam(groupId, selectedStudent);
        model.addAttribute("id", groupId);
        return "redirect:group";
    }

    @RequestMapping(value = "/delStudent", method = RequestMethod.GET)
    public String delStudent(@RequestAttribute long groupId,
                             @RequestAttribute long selectedStudent,
                             Model model) {
        logger.debug(BEFORE + ARG_GROUP_ID + groupId + ", selectedStudent = " + selectedStudent);
        groupService.delStudentFromTeam(selectedStudent);
        model.addAttribute("id", groupId);
        return "redirect:group";
    }

    @RequestMapping(value = "/myGroup", method = RequestMethod.GET)
    public String myGroup(HttpServletRequest request,
                          Model model) {
        Team group = groupService.getTeamByUser(request.getUserPrincipal());
        model.addAttribute("group", group);
        return "myGroup";
    }

}
