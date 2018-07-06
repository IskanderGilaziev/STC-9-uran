package ru.innopolis.stc9.db.dao.teacherSubject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.db.dao.person.PersonDao;
import ru.innopolis.stc9.db.dao.subjects.SubjectDao;
import ru.innopolis.stc9.pojo.TeacherSubject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherSubjectDaoImpl implements TeacherSubjectDao {
    private static final Logger logger = Logger.getLogger(TeacherSubjectDaoImpl.class);
    public  final String ClassName= this.getClass().getName();
    @Autowired
    private PersonDao personDao;
    @Autowired
    private SubjectDao subjectDao;

    @Override
    public TeacherSubject getById(long id) {
        logger.info("Class "+ClassName+" method getById started, id = " + id);
        TeacherSubject teacherSubject = null;

        try {
            try (Connection connection = new ConnectionManagerImpl().getConnection()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT tl.id, tl.teacher_item, p.name AS teacher, tl.subject_item, s2.name AS subject FROM teacher_subject tl   INNER JOIN persons p ON tl.teacher_item = p.id INNER JOIN subjects s2 ON tl.subject_item = s2.id WHERE tl.id = ?;")) {
                    preparedStatement.setLong(1, id);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            teacherSubject = new TeacherSubject(
                                    resultSet.getLong("id")
                                    , resultSet.getLong("teacher_item")
                                    , resultSet.getString("teacher")
                                    , resultSet.getLong("subject_item")
                                    , resultSet.getString("subject")
                            );
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException " + e.getMessage());
        }

        logger.info("Class "+ClassName+" method getById finished, id = " + id);
        return teacherSubject;
    }

    @Override
    public TeacherSubject getByName(String name) {
        TeacherSubject result = null;

        try {
            try (Connection connection = new ConnectionManagerImpl().getConnection()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM teacher_subject WHERE name= ?")) {
                    preparedStatement.setString(1, name);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {

                        while (resultSet.next()) {
                            TeacherSubject teacherSubject = new TeacherSubject(
                                    resultSet.getLong("id")
                                    , resultSet.getLong("teacher_item")
                                    , resultSet.getLong("subject_item"));
                            result = teacherSubject;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException " + e.getMessage());
        }
        return result;
    }

    @Override
    public List<TeacherSubject> getAll() {
        ArrayList<TeacherSubject> result = new ArrayList<>();

        try {
            try (Connection connection = new ConnectionManagerImpl().getConnection()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT teacher_subject.id, persons.name as persName, subjects.name as subjName \n" +
                                "FROM teacher_subject\n" +
                                "  LEFT JOIN persons\n" +
                                "    on teacher_subject.teacher_item = persons.id\n" +
                                "  LEFT JOIN subjects\n" +
                                "    on teacher_subject.subject_item = subjects.id ORDER BY subjects.name")) {

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            TeacherSubject teacherSubject = new TeacherSubject(
                                    resultSet.getLong("id")
                                    , resultSet.getString("persName")
                                    , resultSet.getString("subjName"));
                            result.add(teacherSubject);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean add(TeacherSubject teacherSubject) {
        logger.info("Class "+ClassName+" method add started");

        String sql = "INSERT INTO teacher_subject (teacher_item, subject_item) VALUES (?,?)";

        executeStatement(teacherSubject, sql);

        logger.info("Class " + ClassName + " method add finished");
        return true;
    }

    private void executeStatement(TeacherSubject teacherSubject, String sql) {
        try {
            try (Connection connection = new ConnectionManagerImpl().getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setLong(1, teacherSubject.getTeacherItem());
                    statement.setLong(2, teacherSubject.getSubjectItem());

                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException " + e.getMessage());

        }
    }

    @Override
    public boolean update(TeacherSubject teacherSubject) {
        logger.info("Class "+ClassName+" method update started, id = " + teacherSubject.getId());

        String sql = "UPDATE teacher_subject SET teacher_item = ?, subject_item = ? WHERE id = "+ teacherSubject.getId()+"";

        executeStatement(teacherSubject, sql);
        logger.info("Class "+ClassName+" method update finished, id = " + teacherSubject.getId());
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        logger.info("Class "+ClassName+" method deleteById started, id = " + id);
        String sql = "DELETE FROM teacher_subject WHERE id=?";
        try {
            try (Connection connection = new ConnectionManagerImpl().getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setLong(1, id);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException " + e.getMessage());
        }
        logger.info("Class "+ClassName+" method deleteById finished, id = " + id);
        return true;
    }
}
