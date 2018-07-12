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
import java.time.LocalDate;
import java.util.List;


@Controller
public class PersonController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(PersonController.class);
    private static final String ATTRIBUTE_PERSON = "person";
    private static final String ATTRIBUTE_ACTION = "action";
    private static final String ATTRIBUTE_UPDATE = "update";
    private static final String REDIRECT_PERSON_ALL = "redirect:personAll";
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
        if (model.containsAttribute(ATTRIBUTE_PERSON)) {
            model.addAttribute(ATTRIBUTE_ACTION, ATTRIBUTE_UPDATE);
            model.addAttribute("id", request.getParameter("id"));
        } else {
            model.addAttribute(ATTRIBUTE_ACTION, "add");
            model.addAttribute(ATTRIBUTE_PERSON, new Person());
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
            if (action.equals(ATTRIBUTE_UPDATE)) {

                Person person = personService.getById(Long.valueOf(id));
                person.setName(name);
                person.setBirthday(Date.valueOf(birthday));
                person.setEmail(email);
                person.setStatus(Status.values()[Integer.parseInt(status)]);
                personService.addOrUpdate(person);
            }
        }
        return REDIRECT_PERSON_ALL;
    }

    @RequestMapping(value = "/deletePerson", method = RequestMethod.GET)
    public String deletePerson(@RequestAttribute long id, Model model) {
        personService.deleteById(id);
        return (REDIRECT_PERSON_ALL);
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
    public String updatePerson(@RequestAttribute long id, Model model) {
        model.addAttribute("statusList", Status.values());
        model.addAttribute(ATTRIBUTE_PERSON, personService.getById(id));

        model.addAttribute(ATTRIBUTE_ACTION, ATTRIBUTE_UPDATE);
        return ("/addOrUpdate");
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String getPerson(@RequestAttribute long id, Model model) {
        Person person = personService.getById(id);
        model.addAttribute(ATTRIBUTE_PERSON, person);
        int yCurrent = LocalDate.now().getYear();
        model.addAttribute("yCurrent", yCurrent);
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
        Person persOld = personService.getById(oldPerson);
        Person persNew = personService.getById(newPerson);
        personService.refreshPersonsDataOnModeration(persOld, persNew);
        User user = new User(persNew.getUser().getLogin(), persNew.getUser().getPassword());
        user.setPerson(persOld);
        userService.deleteById(persNew.getUser());
        persOld.setUser(user);
        userService.setSecurityRole(persOld);
        userService.changeEnable(persOld);
        logger.debug("after moderation");
        return REDIRECT_PERSON_ALL;
    }

    @RequestMapping(value = "/ban", method = RequestMethod.GET)
    public String managerEnabled(@RequestParam long id, Model model) {
        Person person = personService.getById(id);
        userService.changeEnable(person);
        model.addAttribute("id", id);
        return "redirect:person";
    }
}