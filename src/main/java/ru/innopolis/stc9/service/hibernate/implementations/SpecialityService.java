package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SpecialityDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.service.hibernate.interfaces.ISpecialityService;

import java.util.List;

@Transactional
@Service
public class SpecialityService implements ISpecialityService {
    private static final Logger logger = Logger.getLogger(SpecialityService.class);

    private final ru.innopolis.stc9.db.hibernate.dao.interfaces.SpecialityDao specialityDao;

    @Autowired
    public SpecialityService(SpecialityDao specialityDao) {
        this.specialityDao = specialityDao;
    }

    @Override
    public Speciality getById(long id) {
        //logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Speciality speciality = specialityDao.getById(id);

        //logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return speciality;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        specialityDao.deleteBySpecialityId(id);
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void addOrUpdate(Speciality speciality) {
        logger.info(this.getClass().getName() + " method add started");
        specialityDao.addOrUpdateSpeciality(speciality);
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<Speciality> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Speciality> specialityList;
        specialityList = specialityDao.getAllSpecialitys();
        logger.info(this.getClass().getName() + " method getAll finished");
        return specialityList;
    }




}
