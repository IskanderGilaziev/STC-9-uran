package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.speciality.SpecialityDao;
import ru.innopolis.stc9.db.dao.speciality.SpecialityDaoImpl;
import ru.innopolis.stc9.pojo.Speciality;

import java.util.ArrayList;
import java.util.List;
@Service
public class SpecialityService implements ISpecialityService {
    private static final Logger logger = Logger.getLogger(SpecialityService.class);
    private static final Logger loggerError = Logger.getLogger(SpecialityService.class);

    @Autowired
    private SpecialityDao specialityDao;

    public SpecialityService(SpecialityDaoImpl mockDao) {
    }

    @Override
    public boolean updateById(Speciality speciality) {
        logger.info(this.getClass().getName() + " method update started, id = " + speciality.getId());

            specialityDao.update(speciality);

        logger.info(this.getClass().getName() + " method update finished, id = " + speciality.getId());
        return true;
    }

    @Override
    public Speciality getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Speciality speciality = null;
            speciality = specialityDao.getById(id);
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return speciality;
    }

    @Override
    public boolean deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
            specialityDao.deleteById(id);
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
        return true;
    }

    @Override
    public boolean add(Speciality speciality) {
        logger.info(this.getClass().getName() + " method add started");
            specialityDao.add(speciality);
        logger.info(this.getClass().getName() + " method add finished");
        return true;
    }

    @Override
    public List<Speciality> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Speciality> specialityList = new ArrayList<>();
            specialityList = specialityDao.getAll();
        logger.info(this.getClass().getName() + " method getAll finished");
        return specialityList;
    }
}
