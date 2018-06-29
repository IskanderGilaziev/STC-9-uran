package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.groups.GroupsDao;
import ru.innopolis.stc9.db.dao.schedule.ScheduleDao;
import ru.innopolis.stc9.pojo.realisationJDBC.Group;
import ru.innopolis.stc9.pojo.realisationJDBC.Schedule;
import ru.innopolis.stc9.pojo.realisationJDBC.ScheduleItem;
import ru.innopolis.stc9.pojo.realisationJDBC.Status;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
public class ScheduleService implements IScheduleService {
    private static final Logger logger = Logger.getLogger(ScheduleService.class);
    private static final String BEFORE = "First  line of method.";
    private static final String AFTER = "Before exit.";
    private static final String LINK_SEE = "посмотреть расписание";
    private static final String LINK_EDIT = "редактировать расписание";
    private static final String LINK_CREATE = "создать расписание";


    @Autowired
    private ScheduleDao scheduleDao;
    @Autowired
    private GroupsDao groupsDao;

    /**
     * Select list of groups depended on required status.
     *
     * @param status
     * @return
     */
    @Override
    public List<Group> getGroupsByStatus(Status status) {
        logger.debug(BEFORE);
        List<Group> result = new ArrayList<>();
        try {
            switch (status) {
                case IN_PLAN:
                    result = groupsDao.getListOfNewGroups();
                    break;
                case IN_PROGRESS:
                    result = groupsDao.getListOfCurrentGroups();
                    break;
                case ARCHIVE:
                    result = groupsDao.getListOfOldGroups();
                    break;
                default:
                    logger.warn("No such status of required group: " + status);
            }
        } catch (SQLException e) {
            logger.error(status + ": " + e.getMessage());
        }
        logger.debug(AFTER);
        return result;
    }

    @Override
    public void updateById(ScheduleItem scheduleItem) {
        logger.info(this.getClass().getName() + " method updateById started, id = " + scheduleItem.getId());
        try {
            scheduleDao.update(scheduleItem);
        } catch (SQLException e) {
            logger.error("Error at method updateById, id = " + scheduleItem.getId(), e);
        }
        logger.info(this.getClass().getName() + " method updateById finished, id = " + scheduleItem.getId());
    }

    @Override
    public ScheduleItem getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        ScheduleItem scheduleItem = null;
        try {
            scheduleItem = scheduleDao.getById(id);
        } catch (SQLException e) {
            logger.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return scheduleItem;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            scheduleDao.deleteById(id);
        } catch (SQLException e) {
            logger.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void add(ScheduleItem scheduleItem) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            scheduleDao.add(scheduleItem);
        } catch (SQLException e) {
            logger.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<ScheduleItem> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<ScheduleItem> scheduleItemList = new ArrayList<>();
        try {
            scheduleItemList = scheduleDao.getAll();
        } catch (SQLException e) {
            logger.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return scheduleItemList;
    }

    /**
     * Collects links for possible actions (depending on the status of the groups)
     *
     * @param status
     * @return
     */
    @Override
    public Map<String, String> collectLinksOnPage(Status status) {
        Map<String, String> links = new LinkedHashMap<>();
        if (status.equals(Status.IN_PLAN)) {
            links.put("/see", LINK_SEE);
            links.put("/edit", LINK_EDIT);
            links.put("/create", LINK_CREATE);
        } else {
            if (status.equals(Status.IN_PROGRESS)) {
                links.put("/see", LINK_SEE);
                links.put("/edit", LINK_EDIT);
            } else {
                if (status.equals(Status.ARCHIVE)) {
                    links.put("/see", LINK_SEE);
                }
            }
        }
        return links;
    }

    /**
     * Get general schedule for several groups.
     *
     * @return
     */
    @Override
    public Schedule getMainSchedule(List<Group> groups) {
        logger.debug(BEFORE);
        Schedule desktop = new Schedule();
        for (Group g : groups) {
            try {
                List<ScheduleItem> list = scheduleDao.getByGroupId(g.getId());
                desktop.add(list);
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        logger.debug(AFTER);
        return desktop;
    }
}
