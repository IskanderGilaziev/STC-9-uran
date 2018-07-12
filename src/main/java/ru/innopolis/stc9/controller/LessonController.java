package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.hibernate.entities.Lesson;
import ru.innopolis.stc9.service.hibernate.interfaces.LessonService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class LessonController {
    private static final Logger logger = Logger.getLogger(LessonController.class);
    @Autowired
    private LessonService service;

    @RequestMapping(value = "/lessonAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<Lesson> lessonList = service.getAll();
        if (lessonList != null) {
            model.addAttribute("lessonList", lessonList);
            return "/lessonList";
        }
        else {
            return "index";
        }
    }
}
