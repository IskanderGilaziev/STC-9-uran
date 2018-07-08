package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.service.hibernate.implementations.SubjectService;

import java.util.List;

@Controller
public class SubjectController {
    private static final Logger logger = Logger.getLogger(SubjectController.class);
    private static final String DEBUG_BEFORE = "first line";
    private static final String DEBUG_AFTER = "exit with result: ";
    private static final String WARN = "error in request";
    private static final String ERROR_PAGE = "error";
    private static final String REDIRECT_MAIN = "redirect:main";
    @Autowired
    private SubjectService subjectService;

    /**
     * стартовая страница для работы с предметами из меню
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String subjectMain(Model model) {
        logger.debug(DEBUG_BEFORE);
        String resultPage = null;
        List<Subject> subjectList = subjectService.getAllSubjects();
        if (subjectList != null) {
            model.addAttribute("subjectList", subjectList);
            resultPage = "subjectMain";
        } else {
            model.addAttribute("msg", "something went wrong");
            resultPage = ERROR_PAGE;
            logger.warn(WARN);
        }
        logger.info(DEBUG_AFTER + resultPage);
        return resultPage;
    }

    @RequestMapping(value = "/removeSubject", method = RequestMethod.GET)
    public String removeSubject(@RequestAttribute long subjectId,
                                Model model) {
        logger.debug(DEBUG_BEFORE);
        String resultPage = REDIRECT_MAIN;
        boolean isSuccess = subjectService.delete(subjectId);
        if (!isSuccess) {
            model.addAttribute("err", "Error while delete operation");
            resultPage = ERROR_PAGE;
            logger.warn(WARN);
        }
        logger.info(DEBUG_AFTER + resultPage);
        return resultPage;
    }

    @RequestMapping(value = "/addSubject", method = RequestMethod.GET)
    public String addNew(@RequestAttribute String name,
                         Model model) {
        logger.debug(DEBUG_BEFORE);
        String resultPage = REDIRECT_MAIN;
        boolean isSuccess = subjectService.addNew(name);
        if (!isSuccess) {
            model.addAttribute("err", "Error while insert operation");
            resultPage = ERROR_PAGE;
            logger.warn(WARN);
        }
        logger.info(DEBUG_AFTER + resultPage);
        return resultPage;
    }

    /**
     * Сохраняет введенное имя дисциплины в БД (#2)
     *
     * @param subjectId
     * @param newName
     * @param model
     * @return
     */
    @RequestMapping(value = "/editSubject", method = RequestMethod.GET)
    public String editExiting(@RequestAttribute long subjectId,
                              @RequestAttribute String newName,
                              Model model) {
        logger.debug(DEBUG_BEFORE);
        String resultPage = REDIRECT_MAIN;
        boolean isSuccess = subjectService.update(subjectId, newName);
        if (!isSuccess) {
            model.addAttribute("err", "Error while update operation");
            resultPage = ERROR_PAGE;
            logger.warn(WARN);
        }
        logger.info(DEBUG_AFTER + resultPage);
        return resultPage;
    }

    /**
     * Показывает форму с текстовым полем для изменения названия учебной дисциплины (#1)
     *
     * @param subjectId
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateSubject", method = RequestMethod.GET)
    public String update(@RequestAttribute long subjectId,
                         Model model) {
        logger.debug(DEBUG_BEFORE);
        String resultPage = null;
        Subject subject = subjectService.getById(subjectId);
        List<Subject> subjectList = subjectService.getAllSubjects();
        if (subject != null && subjectList != null) {
            model.addAttribute("theSubject", subject);
            model.addAttribute("subjectList", subjectList);
            resultPage = "subjectMain";
        } else {
            model.addAttribute("err", "Error while select operation");
            resultPage = ERROR_PAGE;
            logger.warn(WARN);
        }
        logger.info(DEBUG_AFTER + resultPage);
        return resultPage;
    }

    /**
     * Показывает всю возможную информацию по предмету
     *
     * @param subjectId
     * @param model
     * @return
     */
    @RequestMapping(value = "/infoSubject", method = RequestMethod.GET)
    public String info(@RequestAttribute long subjectId,
                       Model model) {
        logger.debug(DEBUG_BEFORE);
        String resultPage = "getSubject";
        Subject subject = subjectService.getById(subjectId);
        if (subject != null) {
            model.addAttribute("subject", subject);
        } else {
            model.addAttribute("err", "Error while select operation");
            resultPage = ERROR_PAGE;
            logger.warn(WARN);
        }
        logger.info(DEBUG_AFTER + resultPage);
        return resultPage;
    }
}
