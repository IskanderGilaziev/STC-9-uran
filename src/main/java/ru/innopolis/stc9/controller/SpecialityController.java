package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.service.hibernate.interfaces.SpecialityService;
import ru.innopolis.stc9.service.hibernate.interfaces.SubjectService;

import java.util.List;

@Controller
public class SpecialityController {
    private static final String DEBUG_BEFORE = "first line";
    private static final String DEBUG_AFTER = "exit with result: ";
    private static final String WARN = "error in request";
    private static final String ERROR_PAGE = "error";
    private static final String REDIRECT_MAIN = "redirect:mainSpeciality";
    private static final Logger logger = Logger.getLogger(SpecialityController.class);

    @Autowired
    private SpecialityService specialityService;
    @Autowired
    private SubjectService subjectService;

    /**
     * стартовая страница для работы со специальностями из меню.
     * Нельзя создать специальность, если в базе нет ни одного предмета
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/mainSpeciality", method = RequestMethod.GET)
    public String specialtyMain(Model model) {
        logger.debug(DEBUG_BEFORE);
        String resultPage = null;
        List<Speciality> specialityList = specialityService.getAllSpecialty();
        List<Subject> subjectList = subjectService.getAllSubjects();
        if (specialityList != null && subjectList != null && !subjectList.isEmpty()) {
            model.addAttribute("specialityList", specialityList);
            model.addAttribute("subjectList", subjectList);
            resultPage = "specialtyMain";
        } else {
            model.addAttribute("msg", "something went wrong");
            resultPage = ERROR_PAGE;
            logger.warn(WARN);
        }
        logger.info(DEBUG_AFTER + resultPage);
        return resultPage;
    }

    /**
     * Создание новой специальности.
     * Обязательное условие - наличие хотя бы одной специальности.
     * Все новые специальности автоматически создаются актуальными
     *
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(value = "/addSpeciality", method = RequestMethod.GET)
    public String addNew(@RequestAttribute String name,
                         @RequestAttribute int yTotal,
                         @RequestAttribute boolean isActive,
                         @RequestAttribute long selectedSubject,
                         Model model) {
        logger.debug(DEBUG_BEFORE);
        String resultPage = REDIRECT_MAIN;
        boolean isSuccess = specialityService.createNewSpecialty(name, yTotal, isActive, selectedSubject);
        if (!isSuccess) {
            model.addAttribute("err", "Error while insert operation");
            resultPage = ERROR_PAGE;
            logger.warn(WARN);
        }
        logger.info(DEBUG_AFTER + resultPage);
        return resultPage;
    }

    /**
     * Показывает форму с текстовыми полями для изменения названия специальности (#2)
     */
    @RequestMapping(value = "/editSpeciality", method = RequestMethod.GET)
    public String editExiting(@RequestAttribute long specialityId,
                              @RequestAttribute String newName,
                              @RequestAttribute int newYTotal,
                              Model model) {
        logger.debug(DEBUG_BEFORE);
        String resultPage = REDIRECT_MAIN;
        boolean isSuccess = specialityService.updateSpecialty(specialityId, newName, newYTotal);
        if (!isSuccess) {
            model.addAttribute("err", "Error while update operation");
            resultPage = ERROR_PAGE;
            logger.warn(WARN);
        }
        logger.info(DEBUG_AFTER + resultPage);
        return resultPage;
    }

    /**
     * Показывает форму с текстовыми полями для изменения названия специальности (#1)
     *
     * @param specialityId
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateSpeciality", method = RequestMethod.GET)
    public String update(@RequestAttribute long specialityId,
                         Model model) {
        logger.debug(DEBUG_BEFORE);
        String resultPage = null;
        Speciality speciality = specialityService.getById(specialityId);
        List<Speciality> specialityList = specialityService.getAllSpecialty();
        if (speciality != null && specialityList != null) {
            model.addAttribute("theSpeciality", speciality);
            model.addAttribute("specialityList", specialityList);
            resultPage = "specialtyMain";
        } else {
            model.addAttribute("err", "Error while select operation");
            resultPage = ERROR_PAGE;
            logger.warn(WARN);
        }
        logger.info(DEBUG_AFTER + resultPage);
        return resultPage;
    }

    @RequestMapping(value = "/removeSpeciality", method = RequestMethod.GET)
    public String removeSubject(@RequestAttribute long specialityId,
                                Model model) {
        logger.debug(DEBUG_BEFORE);
        String resultPage = REDIRECT_MAIN;
        boolean isSuccess = specialityService.delete(specialityId);
        if (!isSuccess) {
            model.addAttribute("err", "Error while delete operation");
            resultPage = ERROR_PAGE;
            logger.warn(WARN);
        }
        logger.info(DEBUG_AFTER + resultPage);
        return resultPage;
    }

    /**
     * Показывает всю возможную информацию по предмету
     *
     * @param specialityId
     * @param model
     * @return
     */
    @RequestMapping(value = "/infoSpeciality", method = RequestMethod.GET)
    public String info(@RequestAttribute long specialityId,
                       Model model) {
        logger.debug(DEBUG_BEFORE);
        String resultPage = "getSpecialty";
        Speciality speciality = specialityService.getById(specialityId);
        if (speciality != null) {
            model.addAttribute("speciality", speciality);
        } else {
            model.addAttribute("err", "Error while select operation");
            resultPage = ERROR_PAGE;
            logger.warn(WARN);
        }
        logger.info(DEBUG_AFTER + resultPage);
        return resultPage;
    }
}
