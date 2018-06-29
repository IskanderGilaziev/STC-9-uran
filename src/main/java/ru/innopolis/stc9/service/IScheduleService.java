package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.realisationJDBC.Group;
import ru.innopolis.stc9.pojo.realisationJDBC.Schedule;
import ru.innopolis.stc9.pojo.realisationJDBC.ScheduleItem;
import ru.innopolis.stc9.pojo.realisationJDBC.Status;

import java.util.List;
import java.util.Map;

public interface IScheduleService {

    void updateById(ScheduleItem scheduleItem);

    ScheduleItem getById(long id);

    void deleteById(long id);

    void add(ScheduleItem scheduleItem);

    List<ScheduleItem> getAll();

    /**
     * Collects links for possible actions (depending on the status of the groups)
     *
     * @param status
     * @return
     */
    Map<String, String> collectLinksOnPage(Status status);

    /**
     * Select list of groups with education in progress
     *
     * @param status
     * @return
     */
    List<Group> getGroupsByStatus(Status status);

    /**
     * Get general schedule for several groups.
     *
     * @return
     */
    Schedule getMainSchedule(List<Group> groups);
}
