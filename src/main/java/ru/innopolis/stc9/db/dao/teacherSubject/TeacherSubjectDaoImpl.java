package ru.innopolis.stc9.db.dao.teacherSubject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.pojo.realisationJDBC.TeacherSubject;

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
//    @Autowired
//    private PersonDao personDaopersonDao;
//    @Autowired
//    private SubjectDao subjectDao;

    @Override
    public TeacherSubject getById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method getById started, id = " + id);
        TeacherSubject teacherSubject = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "select tl.id, tl.teacher_item, p.name as teacher, tl.subject_item, s2.name as subject from teacher_subject tl   inner join persons p on tl.teacher_item = p.id inner join subjects s2 on tl.subject_item = s2.id where tl.id = ?;")) {
                preparedStatement.setLong(1, id);
                try (ResultSet  resultSet = preparedStatement.executeQuery()) {
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

        logger.info("Class "+ClassName+" method getById finished, id = " + id);
        return teacherSubject;
    }

    @Override
    public TeacherSubject getByName(String name) throws SQLException {
        TeacherSubject result = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM teacher_subject WHERE name= ?")) {
                preparedStatement.setString(1, name);
                try ( ResultSet resultSet = preparedStatement.executeQuery()) {

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
        return result;
    }

    @Override
    public List<TeacherSubject> getAll() throws SQLException {
        ArrayList<TeacherSubject> result = new ArrayList<>();

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
        return result;
    }

    @Override
    public void add(TeacherSubject teacherSubject) throws SQLException {
        logger.info("Class "+ClassName+" method add started");

        String sql = "INSERT INTO teacher_subject (teacher_item, subject_item) VALUES (?,?)";

        executeStatement(teacherSubject, sql);
        logger.info("Class " + ClassName + " method add finished");
    }

    private void executeStatement(TeacherSubject teacherSubject, String sql) throws SQLException {
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, teacherSubject.getTeacherItem());
                statement.setLong(2, teacherSubject.getSubjectItem());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public void update(TeacherSubject teacherSubject) throws SQLException {
        logger.info("Class "+ClassName+" method update started, id = " + teacherSubject.getId());

        String sql = "UPDATE teacher_subject SET teacher_item = ?, subject_item = ? WHERE id = "+ teacherSubject.getId()+"";

        executeStatement(teacherSubject, sql);
        logger.info("Class "+ClassName+" method update finished, id = " + teacherSubject.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method deleteById started, id = " + id);
        String sql = "DELETE FROM teacher_subject WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
        logger.info("Class "+ClassName+" method deleteById finished, id = " + id);
    }
}
