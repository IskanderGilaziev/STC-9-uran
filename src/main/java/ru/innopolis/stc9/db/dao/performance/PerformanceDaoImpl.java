package ru.innopolis.stc9.db.dao.performance;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.pojo.realisationJDBC.Performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PerformanceDaoImpl implements PerformanceDao {
    private static final Logger logger = Logger.getLogger(PerformanceDaoImpl.class);

    @Override
    public Performance getById(long id) throws SQLException {
        logger.info("Class PerformanceDaoImpl method getById started, id = " + id);
        Performance performance = null;
        ResultSet resultSet= null;
        int iid = (int)id;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM academic_performance WHERE id= ?")) {
                preparedStatement.setInt(1, iid);
                 resultSet = preparedStatement.executeQuery();
            }
        }
        finally {if(resultSet!=null)
            resultSet.close();
        }

        if (resultSet.next()) {
            performance = new Performance(
                    resultSet.getLong("id")
                    , resultSet.getLong("student")
                    , resultSet.getLong("lesson")
                    , resultSet.getBoolean("on_lesson")
                    , resultSet.getLong( "mark")
                                             );
        }
        logger.info("Class PerformanceDaoImpl method getById finished, id = " + id);
        return performance;
    }

    @Override
    public Performance getByStudentId(long id) throws SQLException {
        Performance result = null;
        ResultSet resultSet = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM academic_performance WHERE student= ?")) {
                preparedStatement.setLong(1, id);
                resultSet = preparedStatement.executeQuery();
            }
        }
        finally {if(resultSet!=null)
            resultSet.close();
        }

        while (resultSet.next()) {
            Performance  performance = new Performance(
                    resultSet.getLong("id")
                    , resultSet.getLong("student")
                    , resultSet.getLong("lesson")
                    , resultSet.getBoolean("on_lesson")
                    , resultSet.getLong( "mark")
            );
            result = performance;
        }
        return result;
    }

    @Override
    public Performance getByLessonId(long id) throws SQLException {
        Performance result = null;
        ResultSet resultSet = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM academic_performance WHERE lesson= ?")) {
                preparedStatement.setLong(1, id);
                resultSet = preparedStatement.executeQuery();
            }
        }
        finally {if(resultSet!=null)
            resultSet.close();
        }

        while (resultSet.next()) {
            Performance  performance = new Performance(
                    resultSet.getLong("id")
                    , resultSet.getLong("student")
                    , resultSet.getLong("lesson")
                    , resultSet.getBoolean("on_lesson")
                    , resultSet.getLong( "mark")
            );

            result = performance;
        }
        return result;
    }

    @Override
    public List<Performance> getAll() throws SQLException {
        ArrayList<Performance> result = new ArrayList<>();

        ResultSet resultSet = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM academic_performance")) {

                resultSet = preparedStatement.executeQuery();
            }
        }
        finally {
            if(resultSet!= null)
                resultSet.close();
        }

        while (resultSet.next()) {
            Performance  performance = new Performance(
                    resultSet.getLong("id")
                    , resultSet.getLong("student")
                    , resultSet.getLong("lesson")
                    , resultSet.getBoolean("on_lesson")
                    , resultSet.getLong( "mark")
            );
            result.add(performance);
        }
        return result;
    }

    @Override
    public void add(Performance performance) throws SQLException {
        logger.info("Class PerformanceDaoImpl method add started");

        String sql = "INSERT INTO academic_performance (  student" +
                "                           ,lesson" +
                "                           ,on_lesson" +
                "                           ,mark) " +
                "     VALUES (?,?,?,?)";

        sqlStatementExecute(performance, sql);
        logger.info("Class PerformanceDaoImpl method add finished");
    }

    @Override
    public void update(Performance performance) throws SQLException {
        logger.info("Class PerformanceDaoImpl method update started, id = " + performance.getId());

        String sql = "UPDATE academic_performance SET  student = ?" +
                "                       , lesson = ?" +
                "                       , on_lesson = ?" +
                "                       , mark = ?" +
                "     WHERE id = ?";

        sqlStatementExecute(performance, sql);
        logger.info("Class PerformanceDaoImpl method update finished, id = " + performance.getId());
    }

    private void sqlStatementExecute(Performance performance, String sql) throws SQLException {
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, performance.getStudent());
                statement.setLong(2, performance.getLesson());
                statement.setBoolean(3, performance.getOnLesson());
                statement.setLong(4, performance.getMark());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class PerformanceDaoImpl method deleteById started, id = " + id);
        String sql = "DELETE FROM academic_performance WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
        logger.info("Class PerformanceDaoImpl method deleteById finished, id = " + id);
    }
}
