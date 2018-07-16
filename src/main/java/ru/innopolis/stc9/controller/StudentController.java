package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.hibernate.entities.Performance;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.pojo.hibernate.entities.Team;
import ru.innopolis.stc9.service.hibernate.interfaces.GroupService;
import ru.innopolis.stc9.service.hibernate.interfaces.PerformanceService;
import ru.innopolis.stc9.service.hibernate.interfaces.SubjectService;
import ru.innopolis.stc9.service.hibernate.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StudentController {
    private static final Logger logger = Logger.getLogger(StudentController.class);
    private static final String ATTR_GROUP = "group";
    private static final String ATTR_PERF_LIST = "performanceList";
    private final GroupService groupService;
    private final UserService userService;
    private final SubjectService subjectService;
    private final PerformanceService service;

    @Autowired
    public StudentController(GroupService groupService, UserService userService, SubjectService subjectService, PerformanceService service) {
        this.groupService = groupService;
        this.userService = userService;
        this.subjectService = subjectService;
        this.service = service;
    }

    @RequestMapping(value = "/myGroup", method = RequestMethod.GET)
    public String myGroup(HttpServletRequest request,
                          Model model) {
        Team group = groupService.getTeamByUser(request.getUserPrincipal());
        model.addAttribute(ATTR_GROUP, group);
        return "myGroup";
    }

    @RequestMapping(value = "/mySubjects", method = RequestMethod.GET)
    public String studentSubjects(
            HttpServletRequest request,
            Model model) {
        Person person = userService.getByUserName(request.getUserPrincipal());
        List<Subject> subjectList = service.getListOfSubjectsWithLessonForStudent(person);
        model.addAttribute("subjectList", subjectList);
        return "mySubjects";
    }

    @RequestMapping(value = "/myMarks", method = RequestMethod.GET)
    public String studentSubjects(HttpServletRequest request,
                                  @RequestAttribute long subject,
                                  Model model) {
        Person person = userService.getByUserName(request.getUserPrincipal());
        Subject subj = subjectService.getById(subject);
        List<Performance> performanceList = service.getListOfPerformanceForStudentBySubject(person, subj);
        model.addAttribute("subject", subj);
        model.addAttribute(ATTR_PERF_LIST, performanceList);
        return "myMarks";
    }
}
