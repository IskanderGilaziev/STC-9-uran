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
import ru.innopolis.stc9.pojo.hibernate.entities.Team;
import ru.innopolis.stc9.service.hibernate.interfaces.SpecialityService;

import java.util.List;

@Controller
public class SpecialityController {
    private static final Logger logger = Logger.getLogger(SpecialityController.class);
    private static final String LOG_BEFORE = "First line. The argument(-s): ";
    private static final String ATTR_SPECIALITY = "speciality";
    private static final String ATTR_ACTION = "action";
    private static final String MSG_SPECIALITY_ID = "specialityId";
    private static final String MSG_ID = "id = ";
    private static final String MSG_NO_ARGS = "-";
    private static final String PARAM_UPDATE = "update";
    private static final String JSP_ADD_UPDATE = "addOrUpdateSpeciality";
    private static final String PAGE_SPECIALITY = "redirect:speciality";

    @Autowired
    private SpecialityService specialityService;

    @RequestMapping(value = "/addOrUpdateSpeciality", method = RequestMethod.GET)
    public String addOrUpdate(@RequestAttribute long id, Model model) {
        logger.debug(LOG_BEFORE + "id=" + id);
        if (model.containsAttribute(ATTR_SPECIALITY)) {
            model.addAttribute(ATTR_ACTION, PARAM_UPDATE);
            model.addAttribute("id", id);
        } else {
            model.addAttribute(ATTR_ACTION, "add");
        }
        return "JSP_ADD_UPDATE";
    }

    @RequestMapping(value = "/addSpeciality", method = RequestMethod.GET)
    public String addOrUpdate(Model model) {
        logger.debug(LOG_BEFORE + MSG_NO_ARGS);
        model.addAttribute(ATTR_ACTION, "add");
        return JSP_ADD_UPDATE;
    }

    @RequestMapping(value = "/addOrUpdateSpeciality", method = RequestMethod.POST)
    public String addOrUpdate(@RequestAttribute long id,
                              @RequestAttribute String action,
                              @RequestAttribute String name,
                              @RequestAttribute int semesterCount, Model model) {
        logger.debug(LOG_BEFORE + MSG_ID + id + ", action = " + action + ", name = " + name + ", semesterCount = " + semesterCount);
        if (action.equals("add")) {
            Speciality speciality = new Speciality(name, semesterCount);
            specialityService.addOrUpdate(speciality);
        } else {
            if (action.equals(PARAM_UPDATE)) {
                Speciality speciality = new Speciality(id, name, semesterCount);
                specialityService.addOrUpdate(speciality);
            }
        }
        return "redirect:specialityAll";
    }

    @RequestMapping(value = "/deleteSpeciality", method = RequestMethod.GET)
    public String delete(@RequestAttribute long id, Model model) {
        logger.debug(LOG_BEFORE + MSG_ID + id);
        specialityService.deleteById(id);
        return ("redirect:specialityAll");
    }

    @RequestMapping(value = "/specialityAll", method = RequestMethod.GET)
    public String getAll(Model model) {
        logger.debug(LOG_BEFORE + MSG_NO_ARGS);
        List<Speciality> specialityList = specialityService.getAll();
        if (specialityList != null) {
            model.addAttribute("specialityList", specialityList);
            return "specialityList";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/updateSpeciality", method = RequestMethod.GET)
    public String update(@RequestAttribute long id, Model model) {
        logger.debug(LOG_BEFORE + MSG_ID + id);
        model.addAttribute(ATTR_SPECIALITY, specialityService.getById(id));
        model.addAttribute(ATTR_ACTION, PARAM_UPDATE);
        return JSP_ADD_UPDATE;
    }

    @RequestMapping(value = "/speciality", method = RequestMethod.GET)
    public String get(@RequestAttribute long id, Model model) {
        logger.debug(LOG_BEFORE + MSG_ID + id);
        Speciality speciality = specialityService.getById(id);
        model.addAttribute(ATTR_SPECIALITY, speciality);
        List<Subject> freeSubject = specialityService.getSuitSubjects(speciality);
        model.addAttribute("subjectList", freeSubject);
        List<Team> freeTeam = specialityService.getSuitGroups(speciality);
        model.addAttribute("teamList", freeTeam);
        return "getSpeciality";
    }

    @RequestMapping(value = "/changeActivity", method = RequestMethod.GET)
    public String changeStatus(@RequestAttribute long specialityId,
                               Model model) {
        logger.debug(LOG_BEFORE + MSG_SPECIALITY_ID + specialityId);
        specialityService.changeStatus(specialityId);
        model.addAttribute("id", specialityId);
        return PAGE_SPECIALITY;
    }

    @RequestMapping(value = "/addSubject", method = RequestMethod.GET)
    public String addSubjectToSpeciality(@RequestAttribute long specialityId,
                                         @RequestAttribute long selectedSubject,
                                         Model model) {
        logger.debug(LOG_BEFORE + MSG_SPECIALITY_ID + specialityId + ", selectedSubject" + selectedSubject);
        specialityService.addSubject(specialityId, selectedSubject);
        model.addAttribute("id", specialityId);
        return PAGE_SPECIALITY;
    }

    @RequestMapping(value = "/addTeam", method = RequestMethod.GET)
    public String addTeamToSpeciality(@RequestAttribute long specialityId,
                                      @RequestAttribute long selectedTeam,
                                      Model model) {
        logger.debug(LOG_BEFORE + MSG_SPECIALITY_ID + specialityId + ", selectedTeam" + selectedTeam);
        specialityService.addTeam(specialityId, selectedTeam);
        model.addAttribute("id", specialityId);
        return PAGE_SPECIALITY;
    }

    @RequestMapping(value = "/delSubject", method = RequestMethod.GET)
    public String delSubjectFromSpeciality(@RequestAttribute long specialityId,
                                           @RequestAttribute long selectedSubject,
                                           Model model) {
        logger.debug(LOG_BEFORE + MSG_SPECIALITY_ID + specialityId + ", selectedSubject" + selectedSubject);
        specialityService.delSubjectFromSpeciality(specialityId, selectedSubject);
        model.addAttribute("id", specialityId);
        return PAGE_SPECIALITY;
    }

    @RequestMapping(value = "/delTeam", method = RequestMethod.GET)
    public String delTeamFromSpeciality(@RequestAttribute long specialityId,
                                        @RequestAttribute long selectedTeam,
                                        Model model) {
        logger.debug(LOG_BEFORE + MSG_SPECIALITY_ID + specialityId + ", selectedTeam" + selectedTeam);
        specialityService.delTeamFromSpecialty(selectedTeam);
        model.addAttribute("id", specialityId);
        return PAGE_SPECIALITY;
    }
}
