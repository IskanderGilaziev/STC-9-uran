package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.innopolis.stc9.db.dao.group_structure.GroupStructureDao;
import ru.innopolis.stc9.pojo.realisationJDBC.GroupStructure;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupStructureService implements IGroupStructureService {
    private static final Logger logger = Logger.getLogger(GroupStructureService.class);
    private static final Logger loggerError = Logger.getLogger(GroupStructureService.class);

    @Autowired
    private GroupStructureDao groupStructureDao;

    @Override
    public void update(GroupStructure groupStructure) {
        logger.info(this.getClass().getName() + " method update started, id = " + groupStructure.getId());
        try {
            groupStructureDao.update(groupStructure);
        } catch (SQLException e) {
            loggerError.error("Error at method update, id = " + groupStructure.getId(), e);
        }
        logger.info(this.getClass().getName() + " method update finished, id = " + groupStructure.getId());
    }

    @Override
    public GroupStructure getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        GroupStructure groupStructure = null;
        try {
            groupStructure = groupStructureDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return groupStructure;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            groupStructureDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void add(GroupStructure groupStructure) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            groupStructureDao.add(groupStructure);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<GroupStructure> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<GroupStructure> groupStructureList = new ArrayList<>();
        try {
            groupStructureList = groupStructureDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return groupStructureList;
    }
}
