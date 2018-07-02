package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.hibernate.entities.Lesson;
import ru.innopolis.stc9.service.hibernate.interfaces.LessonService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Controller
public class LessonController {
    private static final Logger logger = Logger.getLogger(PersonController.class);
    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @RequestMapping(value = "/addLesson", method = RequestMethod.GET)
    public String addLesson(HttpServletRequest request, Model model) {
        return "/addLesson";
    }

    @RequestMapping(value = "/addLesson", method = RequestMethod.POST)
    public String addLesson2(HttpServletRequest request,
                             @RequestAttribute Integer schedule_item,
                             @RequestAttribute Integer teacher_item,
                             @RequestAttribute Date date,
                             @RequestAttribute String theme,
                             @RequestAttribute String homework,

                             Model model) {
        Lesson lesson = new Lesson(schedule_item,schedule_item,teacher_item, date, theme,homework );
        lessonService.addOrUpdateById(lesson);
        model.addAttribute("lesson", lesson);
        return "/getLesson";
    }

    @RequestMapping(value = "/deleteLesson", method = RequestMethod.GET)
    public String deleteLesson(HttpServletRequest request,
                               @RequestAttribute Lesson lesson, Model model) {
        lessonService.deleteById(lesson.getId());
        return "/lessonList";
    }

    @RequestMapping(value = "/lessonAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<Lesson> lessonList = lessonService.getAll();
        if (lessonList != null) {
            model.addAttribute("lessonList", lessonList);
            return "/lessonList";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/updateLesson", method = RequestMethod.GET)
    public String updateLesson(HttpServletRequest request,
                               @RequestAttribute Lesson lesson, Model model) {
        model.addAttribute("lesson", lesson);
        return "/updateLesson";
    }

    @RequestMapping(value = "/updateLesson", method = RequestMethod.POST)
    public String updateLesson2(HttpServletRequest request,
                                @RequestAttribute String id,
                                @RequestAttribute Integer schedule_item,
                                @RequestAttribute Integer teacher_item,
                                @RequestAttribute Date date,
                                @RequestAttribute String theme,
                                @RequestAttribute String homework, Model model) {
        Lesson lesson = new Lesson(Long.parseLong(id), schedule_item, teacher_item, date , theme, homework);
        lessonService.addOrUpdateById(lesson);
        model.addAttribute("lesson", lesson);
        return "/getLesson";
    }

    @RequestMapping(value = "/lesson", method = RequestMethod.GET)
    public String getLesson(HttpServletRequest request,
                            @RequestAttribute String id, Model model) {
        Lesson lesson = lessonService.getById(Long.parseLong(id));
        model.addAttribute("lesson", lesson);
        return "/getLesson";
    }
}
