package ru.innopolis.stc9.db.dao.speciality;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SpecialityDaoImpl implements SpecialityDao {
    private static final Logger logger = Logger.getLogger(SpecialityDaoImpl.class);
    public  final String ClassName= this.getClass().getName();

    @Override
    public Speciality getById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method getById started, id = " + id);
        Speciality speciality = null;

        int iid = (int)id;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM specialty WHERE id= ?")) {
                preparedStatement.setInt(1, iid);
                try (ResultSet  resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        speciality = new Speciality(
                                resultSet.getLong("id")
                                , resultSet.getString("name")
                                , resultSet.getLong("semester_count"));
                    }}}}
        logger.info("Class "+ClassName+" method getById finished, id = " + id);
        return speciality;
    }

    @Override
    public Speciality getByName(String name) throws SQLException {
        Speciality result = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM specialty WHERE name= ?")) {
                preparedStatement.setString(1, name);
                try ( ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        Speciality speciality = new Speciality(
                                resultSet.getLong("id")
                                , resultSet.getString("name")
                                , resultSet.getLong("semester_count"));
                        result = speciality;
                    } } } }
        return result;
    }

    @Override
    public List<Speciality> getAll() throws SQLException {
        ArrayList<Speciality> result = new ArrayList<>();

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM specialty")) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Speciality speciality = new Speciality(
                                resultSet.getLong("id")
                                , resultSet.getString("name")
                                , resultSet.getLong("semester_count"));
                        result.add(speciality);
                    } } } }
        return result;
    }

    @Override
    public void add(Speciality speciality) throws SQLException {
        logger.info("Class "+ClassName+" method add started");

        String sql = "INSERT INTO specialty (name, semester_count) VALUES (?, ?)";

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, speciality.getName());
                statement.setLong(2, speciality.getSemesterCount());
                statement.executeUpdate();
        logger.info("Class "+ClassName+" method add finished");
    }}}


    @Override
    public void update(Speciality speciality) throws SQLException {
        logger.info("Class "+ClassName+" method update started, id = " + speciality.getId());

        String sql = "UPDATE specialty SET name = ?, semester_count = ? WHERE id = ?";

                try (Connection connection = new ConnectionManagerImpl().getConnection()) {
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, speciality.getName());
                        statement.setLong(2, speciality.getSemesterCount());
                        statement.setLong(3, speciality.getId());
                        statement.executeUpdate();
                    }}
        logger.info("Class "+ClassName+" method update finished, id = " + speciality.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method deleteById started, id = " + id);
        String sql = "DELETE FROM specialty WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
        logger.info("Class "+ClassName+" method deleteById finished, id = " + id);
    }
}
