package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.Group;
import ru.innopolis.stc9.pojo.Schedule;
import ru.innopolis.stc9.pojo.ScheduleItem;
import ru.innopolis.stc9.pojo.Status;

import java.util.List;
import java.util.Map;

public interface IScheduleService {

    boolean updateById(ScheduleItem scheduleItem);

    ScheduleItem getById(long id);

    boolean deleteById(long id);

    boolean add(ScheduleItem scheduleItem);

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
