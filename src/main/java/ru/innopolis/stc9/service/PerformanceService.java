package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.performance.PerformanceDao;
import ru.innopolis.stc9.pojo.realisationJDBC.Performance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PerformanceService implements IPerformanceService {
    private static final Logger logger = Logger.getLogger(PerformanceService.class);
    private static final Logger loggerError = Logger.getLogger(PerformanceService.class);

    @Autowired
    private PerformanceDao performanceDao;

    @Override
    public void updateById(Performance performance) {
        logger.info(this.getClass().getName() + " method updateById started, id = " + performance.getId());
        try {
            performanceDao.update(performance);
        } catch (SQLException e) {
            loggerError.error("Error at method updateById, id = " + performance.getId(), e);
        }
        logger.info(this.getClass().getName() + " method updateById finished, id = " + performance.getId());
    }

    @Override
    public Performance getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Performance performance = null;
        try {
            performance = performanceDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return performance;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            performanceDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void add(Performance performance) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            performanceDao.add(performance);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<Performance> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Performance> performanceList = new ArrayList<>();
        try {
            performanceList = performanceDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return performanceList;
    }
}
