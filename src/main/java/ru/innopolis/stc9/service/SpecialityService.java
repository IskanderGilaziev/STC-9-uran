package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.speciality.SpecialityDao;
import ru.innopolis.stc9.pojo.realisationJDBC.Speciality;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
public class SpecialityService implements ISpecialityService {
    private static final Logger logger = Logger.getLogger(SpecialityService.class);
    private static final Logger loggerError = Logger.getLogger(SpecialityService.class);

    @Autowired
    private SpecialityDao specialityDao;

    @Override
    public void updateById(Speciality speciality) {
        logger.info(this.getClass().getName() + " method update started, id = " + speciality.getId());
        try {
            specialityDao.update(speciality);
        } catch (SQLException e) {
            loggerError.error("Error at method update, id = " + speciality.getId(), e);
        }
        logger.info(this.getClass().getName() + " method update finished, id = " + speciality.getId());
    }

    @Override
    public Speciality getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Speciality speciality = null;
        try {
            speciality = specialityDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return speciality;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            specialityDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void add(Speciality speciality) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            specialityDao.add(speciality);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<Speciality> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Speciality> specialityList = new ArrayList<>();
        try {
            specialityList = specialityDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return specialityList;
    }
}
