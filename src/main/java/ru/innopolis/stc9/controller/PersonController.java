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
import ru.innopolis.stc9.pojo.hibernate.entities.User;
import ru.innopolis.stc9.service.hibernate.interfaces.PersonService;
import ru.innopolis.stc9.service.hibernate.interfaces.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;


@Controller
public class PersonController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(PersonController.class);
    private final PersonService personService;
    private final UserService userService;

    @Autowired
    public PersonController(PersonService personService, UserService userService) {
        this.personService = personService;
        this.userService = userService;
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
            personService.addOrUpdate(person);
        } else {
            if (action.equals("update")) {
                Person person = personService.getById(Long.valueOf(id));
                person.setName(name);
                person.setBirthday(Date.valueOf(birthday));
                person.setEmail(email);
                person.setStatus(Status.values()[Integer.parseInt(status)]);
                personService.addOrUpdate(person);
            }
        }
        return "redirect:personAll";
    }

    @RequestMapping(value = "/deletePerson", method = RequestMethod.GET)
    public String deletePerson(@RequestAttribute long id, Model model) {
        personService.deleteById(id);
        return ("redirect:personAll");
    }

    @RequestMapping(value = "/personAll", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Person> personList = personService.getAll();
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
        model.addAttribute("person", personService.getById(Long.parseLong(id)));
        model.addAttribute("action", "update");
        return ("/addOrUpdate");
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String getPerson(@RequestAttribute String id, Model model) {
        Person person = personService.getById(Long.parseLong(id));
        model.addAttribute("person", person);
        return "/getPerson";
    }

    @RequestMapping(value = "/moderation", method = RequestMethod.GET)
    public String moderateNewUsers(@RequestParam long id, Model model) {
        logger.debug("moderation procedure of user");
        Person unmoderatedPerson = personService.getById(id);
        User user = unmoderatedPerson.getUser();
        if (user != null) {
            model.addAttribute("unmoderatedPerson", unmoderatedPerson);
        }
        List<Person> allegedPerson = personService.getAllegedPersonForModeration();
        model.addAttribute("allegedPerson", allegedPerson);
        return "/moderationPage";
    }

    @RequestMapping(value = "/moderation", method = RequestMethod.POST)
    public String mergeUsers(@RequestAttribute long oldPerson,
                             @RequestAttribute long newPerson,
                             Model model) {
        logger.debug("moderation procedure of user");
//        Person persOld = personService.getById(oldPerson);
//        Person persNew = personService.getById(newPerson);
//        personService.refreshPersonsDataOnModeration(persOld, persNew);
//        User user = new User(persNew.getUser().getLogin(),persNew.getUser().getPassword());
//        userService.deleteById(persNew.getUser());
//        persOld.setUser(user);
//        personService.addOrUpdate(persOld);

//        Person unmoderatedPerson = personService.getById(id);
//        User user = unmoderatedPerson.getUser();
//        if (user!=null) {
//            model.addAttribute("unmoderatedPerson", unmoderatedPerson);
//        }
//        List<Person> allegedPerson = personService.getAllegedPersonForModeration();
//        model.addAttribute("allegedPerson", allegedPerson);
        logger.debug("after");
        return "redirect:personAll";
    }
}