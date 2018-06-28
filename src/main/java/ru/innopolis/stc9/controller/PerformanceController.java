package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.realisationJDBC.Performance;
import ru.innopolis.stc9.service.PerformanceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PerformanceController {
    private static final Logger logger = Logger.getLogger(PerformanceController.class);
    @Autowired
    private PerformanceService service;

    @RequestMapping(value = "/addPerformance", method = RequestMethod.GET)
    public String addPerformance(HttpServletRequest request, Model model) {
        return "/addPerformance";
    }

    @RequestMapping(value = "/addPerformance", method = RequestMethod.POST)
    public String addPerformance2(HttpServletRequest request,
                               @RequestAttribute Integer student,
                               @RequestAttribute Integer lesson,
                               @RequestAttribute boolean on_lesson,
                               @RequestAttribute Integer mark,
                                                             Model model) {
        Performance performance = new Performance(student,lesson,on_lesson, mark );
        service.add(performance);
        model.addAttribute("performance", performance);
        return "/getPerformance";
    }

    @RequestMapping(value = "/deletePerformance", method = RequestMethod.GET)
    public String deletePerformance(HttpServletRequest request,
                                 @RequestAttribute Performance performance, Model model) {
        service.deleteById(performance.getId());
        return "/performanceList";
    }

    @RequestMapping(value = "/performanceAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<Performance> performanceList = service.getAll();
        if (performanceList != null) {
            model.addAttribute("performanceList", performanceList);
            return "/performanceList";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/updatePerformance", method = RequestMethod.GET)
    public String updatePerformance(HttpServletRequest request,
                                 @RequestAttribute Performance performance, Model model) {
        model.addAttribute("performance", performance);
        return "/updatePerformance";
    }

    @RequestMapping(value = "/updatePerformance", method = RequestMethod.POST)
    public String updatePerformance2(HttpServletRequest request,
                                  @RequestAttribute String id,
                                     @RequestAttribute Integer student,
                                     @RequestAttribute Integer lesson,
                                     @RequestAttribute boolean on_lesson,
                                     @RequestAttribute Integer mark, Model model) {
        Performance performance = new Performance(Long.parseLong(id)
                                                    , student
                                                    , lesson
                                                    , on_lesson
                                                    , mark);
        service.updateById(performance);
        model.addAttribute("performance", performance);
        return "/getPerformance";
    }

    @RequestMapping(value = "/performance", method = RequestMethod.GET)
    public String getPerformance(HttpServletRequest request,
                              @RequestAttribute String id, Model model) {
        Performance performance = service.getById(Long.parseLong(id));
        model.addAttribute("performance", performance);
        return "/getPerformance";
    }
}
