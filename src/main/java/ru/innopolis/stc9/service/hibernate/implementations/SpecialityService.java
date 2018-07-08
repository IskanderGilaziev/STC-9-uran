package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SpecialityDao;

@Service
public class SpecialityService implements ru.innopolis.stc9.service.hibernate.interfaces.SpecialityService {
    private static final Logger logger = Logger.getLogger(SpecialityService.class);

    private final ru.innopolis.stc9.db.hibernate.dao.interfaces.SpecialityDao specialityDao;

    @Autowired
    public SpecialityService(SpecialityDao specialityDao) {
        this.specialityDao = specialityDao;
    }






}
