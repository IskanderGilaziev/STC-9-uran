package ru.innopolis.stc9.db.dao.schedule;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.db.dao.groups.GroupsDao;
import ru.innopolis.stc9.db.dao.teacherSubject.TeacherSubjectDao;
import ru.innopolis.stc9.pojo.Group;
import ru.innopolis.stc9.pojo.Schedule;
import ru.innopolis.stc9.pojo.ScheduleItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleDaoImpl implements ScheduleDao {
    private static final Logger logger = Logger.getLogger(ScheduleDaoImpl.class);
    @Autowired
    private GroupsDao groupsDao;
    @Autowired
    private TeacherSubjectDao teachSubjectDao;

    @Override
    public ScheduleItem getById(long id) throws SQLException {
        logger.info("Class SheduleDaoImpl method getById started, id = " + id);
        ScheduleItem scheduleItem = null;
        ResultSet resultSet= null;
        int iid = (int)id;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM schedules WHERE id= ?")) {
                preparedStatement.setInt(1, iid);
                resultSet = preparedStatement.executeQuery();
            }
        }
        finally {if(resultSet!=null)
            resultSet.close();
        }

        if (resultSet.next()) {
            scheduleItem = parseOneScheduleItem(resultSet);
        }
        logger.info("Class PerformanceDaoImpl method getById finished, id = " + id);
        return scheduleItem;
    }

    @Override
    public ScheduleItem getByLessonId(long id) throws SQLException {
        ScheduleItem result = null;
        ResultSet resultSet = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM schedules WHERE lesson_nummber= ?")) {
                preparedStatement.setLong(1, id);
                resultSet = preparedStatement.executeQuery();
            }
        }
        finally {if(resultSet!=null)
            resultSet.close();
        }

        while (resultSet.next()) {
            ScheduleItem scheduleItem = parseOneScheduleItem(resultSet);
            result = scheduleItem;
        }
        return result;
    }

    private ScheduleItem parseOneScheduleItem(ResultSet resultSet) throws SQLException {
        return new ScheduleItem(
                resultSet.getLong("id")
                , resultSet.getLong("day_of_week")
                , resultSet.getLong("lesson_nummber")
                , groupsDao.getById(resultSet.getLong("group_item"))
                , teachSubjectDao.getById(resultSet.getLong("subject"))
                , resultSet.getLong("room"));
    }

    @Override
    public List<ScheduleItem> getByGroupId(long id) throws SQLException {
        List<ScheduleItem> result = new ArrayList<>();

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM schedules WHERE group_item= ?")) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ScheduleItem scheduleItem = parseOneScheduleItem(resultSet);
                        result.add(scheduleItem);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<ScheduleItem> getAll() throws SQLException {
        ArrayList<ScheduleItem> result = new ArrayList<>();

        ResultSet resultSet =null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM schedules")) {

                resultSet = preparedStatement.executeQuery();
            }
        }
        finally {
            if(resultSet!=null)
                resultSet.close();
        }

        while (resultSet.next()) {
            ScheduleItem scheduleItem = parseOneScheduleItem(resultSet);
            result.add(scheduleItem);
        }
        return result;
    }

    @Override
    public boolean add(ScheduleItem scheduleItem) throws SQLException {
        logger.info("Class PerformanceDaoImpl method add started");

        String sql = "INSERT INTO schedules (  day_of_week" +
                "                           ,lesson_nummber" +
                "                           ,group_item" +
                "                           ,subject" +
                "                           ,room) " +
                "     VALUES (?,?,?,?,?)";

        sqlStatementExecute(scheduleItem, sql);
        logger.info("Class PerformanceDaoImpl method add finished");
        return true;
    }

    @Override
    public boolean update(ScheduleItem scheduleItem) throws SQLException {
        logger.info("Class PerformanceDaoImpl method update started, id = " + scheduleItem.getId());

        String sql = "UPDATE schedules SET  day_of_week = ?" +
                "                       , lesson_nummber = ?" +
                "                       , group_item = ?" +
                "                       , subject = ?" +
                "                       , room= ?" +
                "     WHERE id = ?";

        sqlStatementExecute(scheduleItem, sql);
        logger.info("Class PerformanceDaoImpl method update finished, id = " + scheduleItem.getId());
        return true;
    }

    private void sqlStatementExecute(ScheduleItem scheduleItem, String sql) throws SQLException {
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, scheduleItem.getDayOfWeek());
                statement.setLong(2, scheduleItem.getLessonNumber());
                statement.setLong(3, scheduleItem.getGroupItem().getId());
                statement.setLong(4, scheduleItem.getSubjectWithTeacher().getId());
                statement.setLong(5, scheduleItem.getRoom());
                statement.executeUpdate();
            }
        }
    }

    @Override
    public boolean deleteById(long id) throws SQLException {
        logger.info("Class PerformanceDaoImpl method deleteById started, id = " + id);
        String sql = "DELETE FROM schedules WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }

        logger.info("Class PerformanceDaoImpl method deleteById finished, id = " + id);
        return true;
    }

    public Schedule getByGroup(Group group) throws SQLException {
        // TODO: 17.06.2018 ����������� ��������� select 
        return null;
    }

}
