package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.pojo.realisationJDBC.TeacherSubject;
import ru.innopolis.stc9.service.hibernate.interfaces.ISubjectService;
import ru.innopolis.stc9.service.ITeacherSubjectService;
import ru.innopolis.stc9.service.hibernate.interfaces.PersonService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TeacherSubjectController {
    private static final Logger logger = Logger.getLogger(TeacherSubjectController.class);

    @Autowired
    private ITeacherSubjectService service;

    @Autowired
    private PersonService personService;

    @Autowired
    private ISubjectService subjectService;

    @RequestMapping(value = "/deleteTeacherSubject", method = RequestMethod.GET)
    public String deleteTeacherSubject(HttpServletRequest request,
                                      @RequestAttribute String id, Model model) {
        service.deleteById(Long.parseLong(id));
        return "redirect:tlAll";
    }

    @RequestMapping(value = "/tlAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<TeacherSubject> teacherSubjectList = service.getAll();
        if (teacherSubjectList != null) {
            model.addAttribute("teacherSubjectList", teacherSubjectList);
            return "/teacherSubjectList";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/addOrUpdateTL", method = RequestMethod.GET)
    public String addOrUpdateTLG(HttpServletRequest request, @RequestAttribute String id, Model model) {
        if (model.containsAttribute("id")) {
            model.addAttribute("action", "update");
            model.addAttribute("id", request.getParameter("id"));
        } else {
            List<Person> teacherList = personService.getTeachers();
            List<Subject> subjectList = subjectService.getAll();
            model.addAttribute("teacherList", teacherList);
            model.addAttribute("subjectList", subjectList);
            model.addAttribute("action", "add");
        }
        return "/addOrUpdateTL";
    }

    @RequestMapping(value = "/addOrUpdateTL", method = RequestMethod.POST)
    public String addOrUpdateTLP(HttpServletRequest request,
                                 @RequestAttribute String action,
                                 @RequestAttribute String id,
                                 @RequestAttribute String teacher_item,
                                 @RequestAttribute String subject_item, Model model) {

        if (action.equals("add")) {
            TeacherSubject teacherSubject = new TeacherSubject(Long.parseLong(teacher_item), Long.parseLong(subject_item));
            service.add(teacherSubject);
            model.addAttribute("teacher", teacherSubject);
        } else {
            if (action.equals("update")) {
                TeacherSubject teacherSubject = new TeacherSubject(Long.parseLong(id), Long.parseLong(teacher_item), Long.parseLong(subject_item));
                service.update(teacherSubject);
                model.addAttribute("teacherSubject", teacherSubject);
            }
        }
        return "redirect:tlAll";
    }

    @RequestMapping(value = "/updateTeacherSubject", method = RequestMethod.GET)
    public String updatePerson(HttpServletRequest request,
                               @RequestAttribute String id, Model model) {
        List<Person> teacherList = personService.getTeachers();
        List<Subject> subjectList = subjectService.getAll();
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("teacherSubject", service.getById(Long.parseLong(id)));
        model.addAttribute("action", "update");
        return ("/addOrUpdateTL");
    }
}
