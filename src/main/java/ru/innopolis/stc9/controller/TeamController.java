package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Team;
import ru.innopolis.stc9.service.hibernate.interfaces.PersonService;
import ru.innopolis.stc9.service.hibernate.interfaces.SubjectService;
import ru.innopolis.stc9.service.hibernate.interfaces.TeamService;

import java.util.List;

@Controller
public class TeamController {
    private static final Logger logger = Logger.getLogger(TeamController.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : ";
    private static final String DEBUC_AFTER = "Before exit.";
    private static final String ATTRIBUTE_ERROR = "errorMessage";
    private final TeamService teamService;
    private final PersonService personService;
    private final SubjectService subjectService;

    @Autowired
    public TeamController(TeamService teamService, PersonService personService, SubjectService subjectService) {
        this.teamService = teamService;
        this.personService = personService;
        this.subjectService = subjectService;
    }


    @RequestMapping(value = "/teamAll", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Team> teamList = teamService.getAll();
        model.addAttribute("teams", teamList);
        return "teamListAll";
    }

    @RequestMapping(value = "/getNewTeam", method = RequestMethod.GET)
    public String createNewTeam(Model model) {
        return "addNewTeam";
    }

    @RequestMapping(value = "/getNewTeam", method = RequestMethod.POST)
    public String addNewTeam(@RequestAttribute String fullName,
                             @RequestAttribute String shortName,
                             @RequestAttribute int yStart,
                             @RequestAttribute int yCurrent,
                             @RequestAttribute int yLast,
                             Model model) {
        boolean b = teamService.createNewTeam(fullName, shortName, yStart, yCurrent, yLast);
//        String result = b ? "teamListAll" : "addNewTeam";
        return "redirect:teamAll";
    }

    @RequestMapping(value = "/editOnlyTeam", method = RequestMethod.GET)
    public String editOnlyTeam(@RequestAttribute long id, Model model) {
        // TODO: 02.07.2018 create forms and service to update only the team data
        return "editExitingTeam";
    }

    @RequestMapping(value = "/getTeam", method = RequestMethod.GET)
    public String showInfoOneTeam(@RequestAttribute long id, Model model) {
        boolean resultView = false;
        if (id > 0) {
            Team team = teamService.getByIdWithStudentsAndSubject(id);
            model.addAttribute("team", team);
//            List<Student> students =teamService.getStudentsByTeam(team);
            resultView = true;
        }
        return resultView ? "getTeam" : "teamListAll";
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public String addStudentToTeam(@RequestAttribute long id, Model model) {
        boolean resultView = false;
        if (id > 0) {
            Team team = teamService.getById(id);
            model.addAttribute("team", team);
            List<Person> personList = teamService.getPotentialStudents();
            model.addAttribute("potentialStudent", personList);
            resultView = true;
        }
        return resultView ? "addStudent" : "getTeam";
    }

    @RequestMapping(value = "/addTheStudent", method = RequestMethod.GET)
    public String addTheStudentToTeam(@RequestAttribute long id,
                                      @RequestAttribute long pers,
                                      Model model) {
        boolean resultView = false;
        if (id > 0 && pers > 0) {
            teamService.addStudentToTeam(pers, id);
            model.addAttribute("id", id);
            resultView = true;
        }
        return "redirect:" + (resultView ? "getTeam" : "teamListAll");
    }

    @RequestMapping(value = "/remStFromTeam", method = RequestMethod.GET)
    public String removeStudentFromTeam(@RequestAttribute long id,
                                        @RequestAttribute long pers,
                                        Model model) {
        boolean resultView = false;
        if (id > 0 && pers > 0) {
            teamService.removeStudentFromTeam(pers, id);
//            teamService.addStudentToTeam(pers, id);
            model.addAttribute("id", id);
            resultView = true;
        }
        return "redirect:" + (resultView ? "getTeam" : "teamListAll");
    }

}
