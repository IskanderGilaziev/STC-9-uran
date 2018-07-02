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
import ru.innopolis.stc9.pojo.hibernate.entities.Teacher;
import ru.innopolis.stc9.service.hibernate.interfaces.PersonService;
import ru.innopolis.stc9.service.hibernate.interfaces.SubjectService;

import java.util.List;

@Controller
public class SubjectController {
    private static final Logger logger = Logger.getLogger(SubjectController.class);
    private static final String ATTRIBUTE_SUBJECT = "subject";
    private static final String ATTRIBUTE_ACTION = "action";
    private static final String ATTRIBUTE_UPDATE = "update";
    private final SubjectService subjectService;
    private final PersonService personService;

    @Autowired
    public SubjectController(SubjectService subjectService, PersonService personService) {
        this.subjectService = subjectService;
        this.personService = personService;
    }


    @RequestMapping(value = "/subjectAll", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Subject> subjectList = subjectService.getAll();
        if (subjectList != null) {
            model.addAttribute("subjectList", subjectList);
            return "/subjectList";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/addSubject", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(ATTRIBUTE_ACTION, "add");
        List<Person> personList = personService.getTeachers();
        model.addAttribute("prospectiveTeachers", personList);
        return "/addOrUpdateSubject";
    }

    @RequestMapping(value = "/updateSubject", method = RequestMethod.GET)
    public String update(@RequestAttribute long id, Model model) {
        model.addAttribute(ATTRIBUTE_ACTION, "update");
        Subject subject = subjectService.getById(id);
        model.addAttribute(ATTRIBUTE_SUBJECT, subject);
        List<Teacher> existingTeachers = subjectService.getAllTechersWithTheSubject(subject);
        List<Person> personList = personService.getTeachers();
        model.addAttribute("prospectiveTeachers", personList);
        return "/addOrUpdateSubject";
    }

    @RequestMapping(value = "/addOrUpdateSubject", method = RequestMethod.POST)
    public String addNewSubjectOrupdate(@RequestAttribute long id,
                                        @RequestAttribute String name,
                                        @RequestAttribute long teacherItem,
                                        Model model) {
        if (id >= 0 && name != null) {
            Subject subject = new Subject(name);
            if (id > 0) {
                subject.setId(id);
            }
            subjectService.addOrUpdate(subject);
        } else {
            logger.warn("NPE");
        }
        return "redirect:subjectAll";
    }

    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public String get(@RequestAttribute long id, Model model) {
        if (id > 0) {
            Subject subject = subjectService.getById(id);
            model.addAttribute("subject", subject);
        }
        return "/getSubject";
    }

    @RequestMapping(value = "/addTeacher", method = RequestMethod.GET)
    public String addTeacher(@RequestAttribute long id, Model model) {
        Subject subject = subjectService.getById(id);

        List<Person> prospectiveTeachers = personService.getTeachers();
        model.addAttribute("subject", subject);
        model.addAttribute(prospectiveTeachers);
        return "redirect:subjectAll";
    }

}
