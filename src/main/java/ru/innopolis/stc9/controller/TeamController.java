package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TeamController {
    private static final Logger logger = Logger.getLogger(TeamController.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : ";
    private static final String DEBUC_AFTER = "Before exit.";
    private static final String ATTRIBUTE_ERROR = "errorMessage";

    @RequestMapping(value = "/teamAll", method = RequestMethod.GET)
    public String getAll(Model model) {
//        List<Person> personList = personService.getAll();
//        if (personList != null) {
//            model.addAttribute("personList", personList);
//            model.addAttribute("unknownStatus", Status.unknown);
//        } else {
//            model.addAttribute(ATTRIBUTE_ERROR, "Извините. Похоже, что что-то пошло не так...");
//        }
        return "teamListAll";
    }

}
