package ru.innopolis.stc9.db.dao.lesson;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.pojo.realisationJDBC.Lesson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LessonDaoImpl implements LessonDao {
    private static final Logger logger = Logger.getLogger(LessonDaoImpl.class);

    @Override
    public Lesson getById(long id) throws SQLException {
        logger.info("Class SheduleDaoImpl method getById started, id = " + id);
        Lesson lesson = null;
        ResultSet resultSet= null;
        int iid = (int)id;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM lessons WHERE id= ?")) {
                preparedStatement.setInt(1, iid);
                resultSet = preparedStatement.executeQuery();
            }
        }
        finally {if(resultSet!=null)
            resultSet.close();
        }

        if (resultSet.next()) {
            lesson = new Lesson(
                                  resultSet.getLong("id")
                                , resultSet.getLong("schedule_Item")
                                , resultSet.getDate("date")
                                , resultSet.getString("theme")
                                , resultSet.getString( "homework"));
        }
        logger.info("Class LessonDaoImpl method getById finished, id = " + id);
        return lesson;
    }

    @Override
    public Lesson getBySheduleId(long id) throws SQLException {
        Lesson result = null;
        ResultSet resultSet = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM lessons WHERE shedule_item= ?")) {
                preparedStatement.setLong(1, id);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Lesson lesson = new Lesson(
                            resultSet.getLong("id")
                            , resultSet.getLong("scheduleItem")
                            , resultSet.getDate("date")
                            , resultSet.getString("theme")
                            , resultSet.getString( "homework"));
                    result = lesson;
                }
            }
        }
        finally {if(resultSet!=null)
            resultSet.close();
        }

        return result;
    }

    @Override
    public Lesson getByTeacherId(long id) throws SQLException {
        Lesson result = null;
        ResultSet resultSet =null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM lessons WHERE teacher_item= ?")) {
                preparedStatement.setLong(1, id);
                resultSet = preparedStatement.executeQuery();
            }
        }
        finally {if(resultSet!=null)
                        resultSet.close();
        }

        while (resultSet.next()) {
            Lesson lesson = new Lesson(
                      resultSet.getLong("id")
                    , resultSet.getLong("scheduleItem")
                    , resultSet.getDate("date")
                    , resultSet.getString("theme")
                    , resultSet.getString( "homework"));

            result = lesson;
        }
        return result;
    }

    @Override
    public List<Lesson> getAll() throws SQLException {
        ArrayList<Lesson> result = new ArrayList<>();

        ResultSet resultSet =null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM lessons")) {

                resultSet = preparedStatement.executeQuery();
            }
        }
        finally {
            if(resultSet!=null)
            resultSet.close();
        }

        while (resultSet.next()) {
            Lesson lesson = new Lesson(
                      resultSet.getLong("id")
                    , resultSet.getLong("scheduleItem")
                    , resultSet.getDate("date")
                    , resultSet.getString("theme")
                    , resultSet.getString( "homework"));
            result.add(lesson);
        }
        return result;
    }

    @Override
    public void add(Lesson lesson) throws SQLException {
        logger.info("Class LessonDaoImpl method add started");

        String sql = "INSERT INTO lessons (  schedule_item" +
                "                           ,teacher_item" +
                "                           ,date" +
                "                           ,theme" +
                "                           ,homework) " +
                "     VALUES (?,?,?,?,?)";

        sqlStatementExecute(lesson, sql);
        logger.info("Class LessonDaoImpl method add finished");
    }

    @Override
    public void update(Lesson lesson) throws SQLException {
        logger.info("Class LessonDaoImpl method update started, id = " + lesson.getId());

        String sql = "UPDATE lessons SET  schedule_item = ?" +
                "                       , teacher_item = ?" +
                "                       , date = ?" +
                "                       , theme = ?" +
                "                       , homework= ?" +
                "     WHERE id = ?";

        sqlStatementExecute(lesson, sql);
        logger.info("Class LessonDaoImpl method update finished, id = " + lesson.getId());
    }

    private void sqlStatementExecute(Lesson lesson, String sql) throws SQLException {
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, lesson.getScheduleItem());
                statement.setLong(2, lesson.getTeacherItem());
                statement.setDate(3, lesson.getDate());
                statement.setString(4, lesson.getTheme());
                statement.setString(5, lesson.getHomework());
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class LessonDaoImpl method deleteById started, id = " + id);
        String sql = "DELETE FROM lessons WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
        logger.info("Class LessonDaoImpl method deleteById finished, id = " + id);
    }
}
