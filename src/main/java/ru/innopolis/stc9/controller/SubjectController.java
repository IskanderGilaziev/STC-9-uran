package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.hibernate.entities.Lesson;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.service.hibernate.implementations.SubjectServiceHibernate;
import ru.innopolis.stc9.service.hibernate.interfaces.LessonService;
import ru.innopolis.stc9.service.hibernate.interfaces.PersonService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Controller
public class SubjectController extends HttpServlet{
    private static final Logger logger = Logger.getLogger(SubjectController.class);

    @Autowired
    private SubjectServiceHibernate service;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/addOrUpdateSubject", method = RequestMethod.GET)
    public String addOrUpdate(HttpServletRequest request, Model model) {
        if (model.containsAttribute("subject")) {
            model.addAttribute("action", "update");
            model.addAttribute("id", request.getParameter("id"));
        } else {
            model.addAttribute("action", "add");
        }
        return "/addOrUpdateSubject";
    }

    @RequestMapping(value = "/addOrUpdateSubject", method = RequestMethod.POST)
    public String addOrUpdate(HttpServletRequest request,
                                    @RequestAttribute long id,
                                     @RequestAttribute String action,
                                    @RequestAttribute String name, Model model) {

        if (action.equals("add")) {
            Subject subject = new Subject(name);
            service.addOrUpdate(subject);
        } else {
            if (action.equals("update")) {
                Subject subject = new Subject(id, name);
                service.addOrUpdate(subject);
            }
        }
        return "redirect:subjectAll";
    }

    @RequestMapping(value = "/deleteSubject", method = RequestMethod.GET)
    public String delete(HttpServletRequest request,
                               @RequestAttribute long id, Model model) {
        service.deleteById(id);
        return ("redirect:subjectAll");
    }

    @RequestMapping(value = "/subjectAll", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Subject> subjectList = service.getAll();
        if (subjectList != null) {
            model.addAttribute("subjectList", subjectList);
            return "/subjectList";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/updateSubject", method = RequestMethod.GET)
    public String update(HttpServletRequest request,
                               @RequestAttribute long id, Model model) {
        model.addAttribute("subject", service.getById(id));
        model.addAttribute("action", "update");
        return ("/addOrUpdateSubject");
    }

    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public String get(HttpServletRequest request,
                            @RequestAttribute long id, Model model) {
        Subject subject = service.getById(id);
        List<Lesson> lessonList = lessonService.getLessonListBySubjId(id);
        model.addAttribute("subject", subject);
        model.addAttribute("lessonList", lessonList);
        return "/getSubject";
    }

    @RequestMapping(value = "/addLesson", method = RequestMethod.GET)
    public String addLesson(HttpServletRequest request, @RequestAttribute long subjId, Model model) {
        Subject subject = service.getById(subjId);
        List<Person> teacherList = personService.getPersonByRoleAndNullUser(Status.teacher);
        model.addAttribute("subject", subject);
        model.addAttribute("teacherList", teacherList);
        return "/addLesson";
    }


    @RequestMapping(value = "/addLesson", method = RequestMethod.POST)
    public String addLesson2(@RequestAttribute long subjectId,
                             @RequestAttribute long teacherItem,
                             @RequestAttribute String date,
                             @RequestAttribute String theme,
                             @RequestAttribute String homework,
                             Model model) {
        Lesson lesson = lessonService.add(service.getById(subjectId),
                teacherItem,date,theme,homework);
        model.addAttribute("lesson", lesson);
        model.addAttribute("id", subjectId);
        return "redirect:subject";
    }

    @RequestMapping(value = "/deleteLesson", method = RequestMethod.GET)
    public String deleteLesson(HttpServletRequest request,
                               @RequestAttribute Lesson lesson,
                               @RequestAttribute String subjId, Model model) {
        lessonService.deleteById(lesson.getId());
        model.addAttribute("id", subjId);
        return "redirect:subject";
    }

    @RequestMapping(value = "/updateLesson", method = RequestMethod.GET)
    public String updateLesson(HttpServletRequest request,
                               @RequestAttribute long id, Model model) {
        model.addAttribute("lesson", lessonService.getById(id));
        List<Person> teacherList = personService.getPersonByRoleAndNullUser(Status.teacher);
        model.addAttribute("teacherList",teacherList);
        return "/updateLesson";
    }

    @RequestMapping(value = "/updateLesson", method = RequestMethod.POST)
    public String updateLesson2(HttpServletRequest request,
                                @RequestAttribute long id,
                                @RequestAttribute long subjectId,
                                @RequestAttribute long teacher_item,
                                @RequestAttribute Date date,
                                @RequestAttribute String theme,
                                @RequestAttribute String homework, Model model) {
        Person teacher = personService.getById(teacher_item);
        Lesson lesson = new Lesson(id,
                service.getById(subjectId),
                teacher_item, date, theme, homework);
        lessonService.addOrUpdateById(lesson);
        model.addAttribute("lesson", lesson);
        model.addAttribute("id", subjectId);
        return "redirect:subject";
    }

    @RequestMapping(value = "/lesson", method = RequestMethod.GET)
    public String getLesson(HttpServletRequest request,
                            @RequestAttribute String id, Model model) {
        Lesson lesson = lessonService.getById(Long.parseLong(id));
        model.addAttribute("lesson", lesson);
        return "/getLesson";
    }
}
