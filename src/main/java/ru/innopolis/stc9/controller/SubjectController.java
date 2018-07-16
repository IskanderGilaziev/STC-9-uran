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

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Controller
public class SubjectController {
    private static final Logger logger = Logger.getLogger(SubjectController.class);
    private static final String ATTR_SUBJECT = "subject";
    private static final String ATTR_ACTION = "action";
    private static final String ATTR_UPDATE = "update";
    private static final String ATTR_LESSON = "lesson";
    private static final String REDIR_SUBJECT = "redirect:subject";

    @Autowired
    private SubjectServiceHibernate service;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/addOrUpdateSubject", method = RequestMethod.GET)
    public String addOrUpdate(HttpServletRequest request, Model model) {
        if (model.containsAttribute(ATTR_SUBJECT)) {
            model.addAttribute(ATTR_ACTION, ATTR_UPDATE);
            model.addAttribute("id", request.getParameter("id"));
        } else {
            model.addAttribute(ATTR_ACTION, "add");
        }
        return "/addOrUpdateSubject";
    }

    @RequestMapping(value = "/addOrUpdateSubject", method = RequestMethod.POST)
    public String addOrUpdate(@RequestAttribute long id,
                              @RequestAttribute String action,
                              @RequestAttribute String name, Model model) {

        if (action.equals("add")) {
            Subject subject = new Subject(name);
            service.addOrUpdate(subject);
        } else {
            if (action.equals(ATTR_UPDATE)) {
                service.updateExitingSubject(id, name);
            }
        }
        return "redirect:subjectAll";
    }

    @RequestMapping(value = "/deleteSubject", method = RequestMethod.GET)
    public String delete(@RequestAttribute long id, Model model) {
        service.deleteById(id);
        return ("redirect:subjectAll");
    }

    @RequestMapping(value = "/subjectAll", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Subject> subjectList = service.getAll();
        if (subjectList != null) {
            model.addAttribute("subjectList", subjectList);
            return "/subjectList";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/updateSubject", method = RequestMethod.GET)
    public String update(@RequestAttribute long id, Model model) {
        model.addAttribute(ATTR_SUBJECT, service.getById(id));
        model.addAttribute(ATTR_ACTION, ATTR_UPDATE);
        return ("/addOrUpdateSubject");
    }

    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public String get(@RequestAttribute long id, Model model) {
        Subject subject = service.getById(id);
        List<Lesson> lessonList = lessonService.getLessonListBySubjId(id);
        model.addAttribute(ATTR_SUBJECT, subject);
        model.addAttribute("lessonList", lessonList);
        return "/getSubject";
    }

    @RequestMapping(value = "/addLesson", method = RequestMethod.GET)
    public String addLesson(@RequestAttribute long subjId, Model model) {
        Subject subject = service.getById(subjId);
        List<Person> teacherList = personService.getPersonsByRole(Status.teacher);
        model.addAttribute(ATTR_SUBJECT, subject);
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
                teacherItem, date, theme, homework);
        model.addAttribute(ATTR_LESSON, lesson);
        model.addAttribute("id", subjectId);
        return REDIR_SUBJECT;
    }

    @RequestMapping(value = "/deleteLesson", method = RequestMethod.GET)
    public String deleteLesson(@RequestAttribute Lesson lesson,
                               @RequestAttribute String subjId, Model model) {
        lessonService.deleteById(lesson.getId());
        model.addAttribute("id", subjId);
        return REDIR_SUBJECT;
    }

    @RequestMapping(value = "/updateLesson", method = RequestMethod.GET)
    public String updateLesson(@RequestAttribute long id, Model model) {
        model.addAttribute(ATTR_LESSON, lessonService.getById(id));
        List<Person> teacherList = personService.getPersonsByRole(Status.teacher);
        model.addAttribute("teacherList", teacherList);
        return "/updateLesson";
    }

    @RequestMapping(value = "/updateLesson", method = RequestMethod.POST)
    public String updateLesson2(@RequestAttribute long id,
                                @RequestAttribute long teacherItem,
                                @RequestAttribute String date,
                                @RequestAttribute String theme,
                                @RequestAttribute String homework,
                                Model model) {
        Lesson lesson = lessonService.getById(id);
        Person teacher = personService.getById(teacherItem);
        if (teacher != null) {
            lesson.setTeacherItem(teacher.getId());
        }
        lesson.setDate(Date.valueOf(date));
        lesson.setTheme(theme);
        lesson.setHomework(homework);
        lessonService.addOrUpdateById(lesson);
        model.addAttribute(ATTR_LESSON, lesson);
        model.addAttribute("id", lesson.getSubject().getId());
        return REDIR_SUBJECT;
    }

    @RequestMapping(value = "/lesson", method = RequestMethod.GET)
    public String getLesson(@RequestAttribute String id, Model model) {
        Lesson lesson = lessonService.getById(Long.parseLong(id));
        model.addAttribute(ATTR_LESSON, lesson);
        return "/getLesson";
    }
}
