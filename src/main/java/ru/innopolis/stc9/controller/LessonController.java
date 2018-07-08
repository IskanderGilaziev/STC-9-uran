package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.service.hibernate.implementations.SubjectServiceHibernate;
import ru.innopolis.stc9.service.hibernate.interfaces.LessonService;
import ru.innopolis.stc9.service.hibernate.interfaces.PersonService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LessonController {
    private static final Logger logger = Logger.getLogger(LessonController.class);
    @Autowired
    private LessonService service;
    @Autowired
    private SubjectServiceHibernate subjectService;
    @Autowired
    private PersonService personService;




    @RequestMapping(value = "/lessonAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        /*List<Lesson> lessonList = service.getAll();
        if (lessonList != null) {
            model.addAttribute("lessonList", lessonList);
            return "/lessonList";
        }
        else {
            return "index";
        }*/
        return null;
    }



    @RequestMapping(value = "/lesson", method = RequestMethod.GET)
    public String getLesson(HttpServletRequest request,
                            @RequestAttribute String id, Model model) {
        /*Lesson lesson = service.getById(Long.parseLong(id));
        model.addAttribute("lesson", lesson);*/
        return "/getLesson";
    }
}
