package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.service.hibernate.interfaces.ISpecialityService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SpecialityController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(SpecialityController.class);

    @Autowired
    private ISpecialityService service;

    @RequestMapping(value = "/addOrUpdateSpeciality", method = RequestMethod.GET)
    public String addOrUpdate(HttpServletRequest request, Model model) {

        if (model.containsAttribute("speciality")) {
            model.addAttribute("action", "update");
            model.addAttribute("id", request.getParameter("id"));
        }
        else {
            model.addAttribute("action", "add");
        }
        return "/addOrUpdateSpeciality";
    }

    @RequestMapping(value = "/addOrUpdateSpeciality", method = RequestMethod.POST)
    public String addOrUpdate(HttpServletRequest request,
                                    @RequestAttribute String id,
                                    @RequestAttribute String action,
                                    @RequestAttribute String name,
                                    @RequestAttribute String semesterCount, Model model) {

        if (action.equals("add")) {

              Speciality speciality = new Speciality();
              speciality.setName(name);
              speciality.setSemesterCount(Integer.parseInt(semesterCount));
            service.addOrUpdate(speciality);
        } else {
            if (action.equals("update")) {

                Speciality speciality = service.getById(Long.parseLong(id));
                speciality.setName(name);
                speciality.setSemesterCount(Integer.parseInt(semesterCount));
                service.addOrUpdate(speciality);
            }
        }
        return "redirect:specialityAll";
    }

    @RequestMapping(value = "/deleteSpeciality", method = RequestMethod.GET)
    public String delete(HttpServletRequest request,
                               @RequestAttribute String id, Model model) {
        service.deleteById(Long.parseLong(id));
        return ("redirect:specialityAll");
    }

    @RequestMapping(value = "/specialityAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<Speciality> specialityList = service.getAll();
        if (specialityList != null) {
            model.addAttribute("specialityList", specialityList);
            return "/specialityList";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/updateSpeciality", method = RequestMethod.GET)
    public String update(HttpServletRequest request,
                               @RequestAttribute String id, Model model) {
        model.addAttribute("speciality", service.getById(Long.parseLong(id)));
        model.addAttribute("action", "update");
        return ("/addOrUpdateSpeciality");
    }

    @RequestMapping(value = "/speciality", method = RequestMethod.GET)
    public String get(HttpServletRequest request,
                            @RequestAttribute String id, Model model) {
        Speciality speciality = service.getById(Long.parseLong(id));
        model.addAttribute("speciality", speciality);
        return "/getSpeciality";
    }
}
