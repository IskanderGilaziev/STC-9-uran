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
import ru.innopolis.stc9.pojo.hibernate.entities.Status;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.service.hibernate.interfaces.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PerformanceController {
    private static final Logger logger = Logger.getLogger(PerformanceController.class);
    @Autowired
    private PerformanceService service;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/addOrUpdatePerform", method = RequestMethod.GET)
    public String addPerformance(HttpServletRequest request, Model model) {
        model.addAttribute("action", "add");
        long lessonId = Long.parseLong(request.getParameter("lessonId"));
        model.addAttribute("lesson", lessonService.getById(lessonId));
        List<Person> studentList = personService.getPersonByRoleAndNullUser(Status.student);
        model.addAttribute("studentList", studentList);
        return "/addOrUpdatePerfom";
    }

    @RequestMapping(value = "/addOrUpdatePerform", method = RequestMethod.POST)
    public String addPerformance2(HttpServletRequest request,
                                  @RequestAttribute Integer lessonId,
                                  @RequestAttribute int[] studentsId,
                                  @RequestAttribute int[] marks,
                                  @RequestAttribute boolean[] attendances,
                                  Model model) {
        for (int i = 0; i < studentsId.length; i++) {
            Performance performance = new Performance(personService.getById(studentsId[i]),
                    lessonService.getById(lessonId), marks[i], attendances[i]);
            service.addOrUpdateById(performance);
        }
        model.addAttribute("lessonId", lessonId);
        return "redirect:getPerformance";
    }

    @RequestMapping(value = "/deletePerformance", method = RequestMethod.GET)
    public String deletePerformance(HttpServletRequest request,
                                    @RequestAttribute Performance performance, Model model) {
        service.deleteById(performance.getId());
        return "/performanceList";
    }

    @RequestMapping(value = "/performanceAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<Performance> performanceList = service.getAll();
        if (performanceList != null) {
            model.addAttribute("performanceList", performanceList);
            return "/performanceList";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/updatePerformance", method = RequestMethod.GET)
    public String updatePerformance(HttpServletRequest request,
                                    @RequestAttribute Performance performance, Model model) {
        model.addAttribute("performance", performance);
        return "/updatePerformance";
    }

    @RequestMapping(value = "/updatePerformance", method = RequestMethod.POST)
    public String updatePerformance2(HttpServletRequest request,
                                     @RequestAttribute long id,
                                     @RequestAttribute Integer studentId,
                                     @RequestAttribute Integer lessonId,
                                     @RequestAttribute Integer mark,
                                     @RequestAttribute boolean attendance, Model model) {
        Performance performance = new Performance(id
                , personService.getById(studentId)
                , lessonService.getById(lessonId)
                , mark
                , attendance);
        service.addOrUpdateById(performance);
        model.addAttribute("performance", performance);
        return "/getPerformance";
    }

    @RequestMapping(value = "/getPerformance", method = RequestMethod.GET)
    public String getPerformance(HttpServletRequest request,
                                 @RequestAttribute long lessonId, Model model) {
        model.addAttribute("lesson", lessonService.getById(lessonId));
        List<Performance> performanceList = service.getByLessonId(lessonId);
        if (performanceList != null) {
            model.addAttribute("performanceList", performanceList);
            return "/getPerformance";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/mySubjects", method = RequestMethod.GET)
    public String studentSubjects(
//            Authentication authentication,
            HttpServletRequest request,
            Model model) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        Person person = userService.getByUserName(userDetails.getUsername());
        Person person = userService.getByUserName(request.getUserPrincipal().getName());
        List<Subject> subjectList = service.getListOfSubjectsWithLessonForStudent(person);
        model.addAttribute("subjectList", subjectList);
        return "/mySubjects";
    }

    @RequestMapping(value = "/myMarks", method = RequestMethod.GET)
    public String studentSubjects(HttpServletRequest request,
                                  @RequestAttribute long subject,
                                  Model model) {
        Person person = userService.getByUserName(request.getUserPrincipal().getName());
        Subject subj = subjectService.getById(subject);
        List<Performance> performanceList = service.getListOfPerformanceForStudentBySubject(person, subj);
        model.addAttribute("subject", subj);
        model.addAttribute("performanceList", performanceList);
        return "/myMarks";
    }
}
