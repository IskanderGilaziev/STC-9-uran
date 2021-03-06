package ru.innopolis.stc9.db.dao.schedule;

import ru.innopolis.stc9.pojo.Group;
import ru.innopolis.stc9.pojo.Schedule;
import ru.innopolis.stc9.pojo.ScheduleItem;

import java.sql.SQLException;
import java.util.List;

public interface ScheduleDao {
    ScheduleItem getById(long id) throws SQLException;

    ScheduleItem getByLessonId(long id) throws SQLException;

    List<ScheduleItem> getByGroupId(long id) throws SQLException;

    List<ScheduleItem> getAll() throws SQLException;

    void add(ScheduleItem shedule) throws SQLException;

    void update(ScheduleItem shedule) throws SQLException;

    void deleteById(long id) throws SQLException;

    Schedule getByGroup(Group group) throws SQLException;


}
