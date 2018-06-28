package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.realisationJDBC.Role;
import ru.innopolis.stc9.service.IRoleService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RoleController extends HttpServlet{
    private static final Logger logger = Logger.getLogger(RoleController.class);

    @Autowired
    private IRoleService service;

    @RequestMapping(value = "/addOrUpdateRole", method = RequestMethod.GET)
    public String addOrUpdate(HttpServletRequest request, Model model) {
        if (model.containsAttribute("role")) {
            model.addAttribute("action", "update");
            model.addAttribute("id", request.getParameter("id"));
        } else {
            model.addAttribute("action", "add");
        }
        return "/addOrUpdateRole";
    }

    @RequestMapping(value = "/addOrUpdateRole", method = RequestMethod.POST)
    public String addOrUpdate(HttpServletRequest request,
                              @RequestAttribute String id,
                              @RequestAttribute String action,
                              @RequestAttribute String name, Model model) {

        if (action.equals("add")) {
            Role role = new Role(name);
            service.add(role);
        } else {
            if (action.equals("update")) {
                Role role = new Role(Integer.parseInt(id), name);
                service.update(role);
            }
        }
        return "redirect:roleAll";
    }

    @RequestMapping(value = "/deleteRole", method = RequestMethod.GET)
    public String delete(HttpServletRequest request,
                         @RequestAttribute String id, Model model) {
        service.deleteById(Integer.parseInt(id));
        return ("redirect:roleAll");
    }

    @RequestMapping(value = "/roleAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {

        List<Role> roleList = service.getAll();

        if (roleList != null) {
            model.addAttribute("roleList", roleList);
            return "/roleList";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.GET)
    public String update(HttpServletRequest request,
                         @RequestAttribute String id, Model model) {
        model.addAttribute("role", service.getById(Long.parseLong(id)));
        model.addAttribute("action", "update");
        return ("/addOrUpdateRole");
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public String get(HttpServletRequest request,
                      @RequestAttribute String id, Model model) {
        Role role = service.getById(Long.parseLong(id));
        model.addAttribute("role", role);
        return "/getRole";
    }
}
