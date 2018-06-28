package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.pojo.hibernate.entities.User;
import ru.innopolis.stc9.service.hibernate.interfaces.PersonService;
import ru.innopolis.stc9.service.hibernate.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    private static final String BEFORE = "First line of method";
    private static final String AFTER = "Before exit";
    @Autowired
    PersonService personService;
    @Autowired
    private UserService service;

//    @Autowired
//    public UserController(UserService service) {
//        this.service = service;
//    }

    @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.GET)
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
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(@RequestAttribute String id, Model model) {
        logger.debug(BEFORE);
        User user = service.getById(Long.parseLong(id));
        service.deleteById(user);
        logger.debug(AFTER);
        return ("redirect:userAll");
    }

    @RequestMapping(value = "/userAll", method = RequestMethod.GET)
    public String getAll(Model model) {
        logger.debug(BEFORE);
        String result;
        List<User> userList = service.getAll();
        if (userList != null) {
            model.addAttribute("userList", userList);
            result = "/userList";
        } else {
            result = "index";
        }
        logger.debug(AFTER);
        return result;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public String updateUser(@RequestAttribute String id, Model model) {
        logger.debug(BEFORE);
        model.addAttribute("user", service.getById(Long.parseLong(id)));
        model.addAttribute("action", "update");
        return ("/addOrUpdate");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser(@RequestAttribute String id, Model model) {
        logger.debug(BEFORE);
        User user = service.getById(Long.parseLong(id));
        model.addAttribute("user", user);
        return "/getUser";
    }


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
        boolean b = service.signUpUser(personName, email, login, password, passwordConfirm);
        if (!b) {
            model.addAttribute("err", "�� ������� ���������������� ��� � �������");
            result = "registration";
        }
        return result;
    }
}
