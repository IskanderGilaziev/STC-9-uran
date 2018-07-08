package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.innopolis.stc9.service.hibernate.interfaces.SpecialityService;

import javax.servlet.http.HttpServlet;

@Controller
public class SpecialityController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(SpecialityController.class);

    @Autowired
    private SpecialityService specialityService;

}
