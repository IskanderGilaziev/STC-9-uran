package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.realisationJDBC.Program;
import ru.innopolis.stc9.service.IProgramService;
import ru.innopolis.stc9.service.ISpecialityService;
import ru.innopolis.stc9.service.ISubjectService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProgramController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ProgramController.class);

    @Autowired
    private IProgramService progService;

    @Autowired
    private ISpecialityService specService;

    @Autowired
    private ISubjectService subjService;

    @RequestMapping(value = "/addOrUpdateProgram", method = RequestMethod.GET)
    public String addOrUpdate(HttpServletRequest request, Model model) {

        if (model.containsAttribute("program")) {
            model.addAttribute("action", "update");
            model.addAttribute("id", request.getParameter("id"));
        } else {
            model.addAttribute("action", "add");
        }

        List<Speciality> specList = specService.getAll();
        model.addAttribute("specList", specList);

        List<Subject> subjList = subjService.getAll();
        model.addAttribute("subjList", subjList);
        return "/addOrUpdateProgram";
    }

    @RequestMapping(value = "/addOrUpdateProgram", method = RequestMethod.POST)
    public String addOrUpdate(HttpServletRequest request,
                                    @RequestAttribute String id,
                                    @RequestAttribute String action,
                                    @RequestAttribute String specialty,
                                    @RequestAttribute String semester,
                                    @RequestAttribute String subject,
                                    @RequestAttribute String hours, Model model) {

        if (action.equals("add")) {
            Program program = new Program(specService.getById(Integer.parseInt(specialty))
                                        , Integer.parseInt(semester)
                                        , subjService.getById(Integer.parseInt(subject))
                                        , Integer.parseInt(hours));
            progService.add(program);
        }
        else {
            if (action.equals("update")) {

                Program program = new Program( Integer.parseInt(id)
                                             , specService.getById(Integer.parseInt(specialty))
                                             , Integer.parseInt(semester)
                                             , subjService.getById(Integer.parseInt(subject))
                                             , Integer.parseInt(hours));
                progService.update(program);
            }
        }
        return "redirect:programAll";
    }

    @RequestMapping(value = "/deleteProgram", method = RequestMethod.GET)
    public String deleteProgram(HttpServletRequest request,
                               @RequestAttribute String id, Model model) {
        progService.deleteById(Long.parseLong(id));
        return ("redirect:programAll");
    }

    @RequestMapping(value = "/programAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {

        List<Program> programList = progService.getAll();

        if (programList != null) {
            model.addAttribute("programList", programList);
            return "/programList";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/updateProgram", method = RequestMethod.GET)
    public String updateProgram(HttpServletRequest request,
                               @RequestAttribute String id, Model model) {
        List<Speciality> specList = specService.getAll();
        List<Subject> subjList = subjService.getAll();

        model.addAttribute("specList", specList);
        model.addAttribute("subjList", subjList);

        model.addAttribute("program", progService.getById(Long.parseLong(id)));
        model.addAttribute("action", "update");
        return ("/addOrUpdateProgram");
    }

    @RequestMapping(value = "/program", method = RequestMethod.GET)
    public String getProgram(HttpServletRequest request,
                            @RequestAttribute String id, Model model) {
        Program program = progService.getById(Long.parseLong(id));

        String specName = program.getSpecialty().getName();
        String subjName = program.getSubject().getName();
        String semCount = String.valueOf(program.getSemester());
        String hourCount = String.valueOf(program.getHours());

        model.addAttribute("program", program);
        model.addAttribute("specialty", specName);
        model.addAttribute("semester", semCount);
        model.addAttribute("subject", subjName);
        model.addAttribute("hours", hourCount);

        return "/getProgram";
    }
    
   
}
