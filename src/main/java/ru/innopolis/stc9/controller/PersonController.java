package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;
import ru.innopolis.stc9.service.hibernate.interfaces.PersonService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;


@Controller
public class PersonController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(PersonController.class);
    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }


    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.GET)
    public String addOrUpdate(HttpServletRequest request, Model model) {
        model.addAttribute("statusList", Status.values());
        if (model.containsAttribute("person")) {
            model.addAttribute("action", "update");
            model.addAttribute("id", request.getParameter("id"));
        } else {
            model.addAttribute("action", "add");
            model.addAttribute("person", new Person());
        }
        return "/addOrUpdate";
    }

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public String addOrUpdatePerson(@RequestAttribute String id,
                                    @RequestAttribute String action,
                                    @RequestAttribute String name,
                                    @RequestAttribute String birthday,
                                    @RequestAttribute String email,
                                    @RequestAttribute String status, Model model) {

        if (action.equals("add")) {
            Status s = Status.values()[Integer.parseInt(status)];
            Person person = new Person(name, Date.valueOf(birthday), email, s);
            service.addOrUpdate(person);
        } else {
            if (action.equals("update")) {
                Person person = service.getById(Long.valueOf(id));
                person.setName(name);
                person.setBirthday(Date.valueOf(birthday));
                person.setEmail(email);
                person.setStatus(Status.values()[Integer.parseInt(status)]);
                service.addOrUpdate(person);
            }
        }
        return "redirect:personAll";
    }

    @RequestMapping(value = "/deletePerson", method = RequestMethod.GET)
    public String deletePerson(@RequestAttribute long id, Model model) {
        service.deleteById(id);
        return ("redirect:personAll");
    }

    @RequestMapping(value = "/personAll", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Person> personList = service.getAll();
        if (personList != null) {
            model.addAttribute("personList", personList);
            model.addAttribute("unknownStatus", Status.unknown);
            return "/personList";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/updatePerson", method = RequestMethod.GET)
    public String updatePerson(@RequestAttribute String id, Model model) {
        model.addAttribute("statusList", Status.values());
        model.addAttribute("person", service.getById(Long.parseLong(id)));
        model.addAttribute("action", "update");
        return ("/addOrUpdate");
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String getPerson(HttpServletRequest request,
                            @RequestAttribute String id, Model model) {
        Person person = service.getById(Long.parseLong(id));
        model.addAttribute("person", person);
        return "/getPerson";
    }

    @RequestMapping(value = "/moderation", method = RequestMethod.GET)
    public String moderateNewUsers(@RequestParam long id, Model model) {
        logger.debug("Here");
        // TODO: 29.06.2018 moderation page
        return "moderationPage";
    }
}