package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.innopolis.stc9.db.dao.groups.GroupsDao;
import ru.innopolis.stc9.pojo.realisationJDBC.Group;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupService implements IGroupService {
    private static final Logger logger = Logger.getLogger(GroupService.class);
    private static final Logger loggerError = Logger.getLogger(GroupService.class);

    @Autowired
    private GroupsDao groupDao;

    @Override
    public void update(Group group) {
        logger.info(this.getClass().getName() + " method update started, id = " + group.getId());
        try {
            groupDao.update(group);
        } catch (SQLException e) {
            loggerError.error("Error at method update, id = " + group.getId(), e);
        }
        logger.info(this.getClass().getName() + " method update finished, id = " + group.getId());
    }

    @Override
    public Group getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Group group = null;
        try {
            group = groupDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return group;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            groupDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void add(Group group) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            groupDao.add(group);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<Group> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Group> groupList = new ArrayList<>();
        try {
            groupList = groupDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return groupList;
    }
}
