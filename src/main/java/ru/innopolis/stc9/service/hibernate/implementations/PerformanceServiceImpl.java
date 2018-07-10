package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.PerformanceDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Performance;
import ru.innopolis.stc9.service.hibernate.interfaces.PerformanceService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class PerformanceServiceImpl implements PerformanceService {
    private static final Logger logger = Logger.getLogger(SubjectService.class);

    private final PerformanceDao performanceDao;

    @Autowired
    public PerformanceServiceImpl(PerformanceDao performanceDao) {
        this.performanceDao = performanceDao;
    }

    @Override
    public void addOrUpdateById(Performance performance) {
        logger.info(this.getClass().getName() + " method add started");
        performanceDao.addOrUpdateById(performance);
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public Performance getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Performance subject = null;
        subject = performanceDao.getById(id);
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return subject;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        performanceDao.deleteById(id);
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public List<Performance> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Performance> performanceList;
        performanceList = performanceDao.getAll();
        logger.info(this.getClass().getName() + " method getAll finished");
        return performanceList;
    }

    @Override
    public List<Performance> getPerfomanceListByPerson(long personId) {
        logger.info(this.getClass().getName() + " method getLessonListBySubjId started");
        return performanceDao.getPerfomanceListByPerson(personId);
    }
}
