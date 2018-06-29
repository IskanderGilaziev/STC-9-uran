package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.service.hibernate.interfaces.PersonService;
import ru.innopolis.stc9.service.hibernate.interfaces.UserService;

@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    private static final String BEFORE = "First line of method";
    private final UserService userService;
    private final PersonService personService;

    @Autowired
    public UserController(UserService userService, PersonService personService) {
        this.userService = userService;
        this.personService = personService;
    }

    /*@RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.GET)
    public String addOrUpdate(HttpServletRequest request, Model model) {
        logger.debug(BEFORE);
        if (model.containsAttribute("user")) {
            model.addAttribute("action", "update");
            model.addAttribute("id", request.getParameter("id"));
        } else {
            model.addAttribute("action", "add");
        }
        logger.debug(AFTER);
        return "/addOrUpdateUser";
    }*/

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String setRegistrationUser(@RequestAttribute String err, Model model) {
        logger.debug(BEFORE);
        if (!model.containsAttribute("err")) {
            model.addAttribute("err", err);
        }
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String setRegistrationUser(@RequestParam String personName,
                                      @RequestParam String email,
                                      @RequestParam String login,
                                      @RequestParam String password,
                                      @RequestParam String passwordConfirm,
                                      Model model) {
        logger.debug(BEFORE);
        String result = "login";
        if (!userService.signUpUser(personName, email, login, password, passwordConfirm)) {
            model.addAttribute("err", "Error");
            result = "registration";
        }
        return result;
    }
}
