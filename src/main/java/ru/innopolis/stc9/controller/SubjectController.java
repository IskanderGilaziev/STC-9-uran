package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.service.ISubjectService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SubjectController extends HttpServlet{
    private static final Logger logger = Logger.getLogger(SubjectController.class);

    @Autowired
    private ISubjectService service;

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
                                    @RequestAttribute String id,
                                     @RequestAttribute String action,
                                    @RequestAttribute String name, Model model) {

        if (action.equals("add")) {
            Subject subject = new Subject(name);
            service.add(subject);
        } else {
            if (action.equals("update")) {
                Subject subject = new Subject(Long.parseLong(id), name);
                service.update(subject);
            }
        }
        return "redirect:subjectAll";
    }

    @RequestMapping(value = "/deleteSubject", method = RequestMethod.GET)
    public String delete(HttpServletRequest request,
                               @RequestAttribute String id, Model model) {
        service.deleteById(Long.parseLong(id));
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
                               @RequestAttribute String id, Model model) {
        model.addAttribute("subject", service.getById(Long.parseLong(id)));
        model.addAttribute("action", "update");
        return ("/addOrUpdateSubject");
    }

    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public String get(HttpServletRequest request,
                            @RequestAttribute String id, Model model) {
        Subject subject = service.getById(Long.parseLong(id));
        model.addAttribute("subject", subject);
        return "/getSubject";
    }
}
