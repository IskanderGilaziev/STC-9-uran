package ru.innopolis.stc9.db.dao.users;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsersDaoImpl implements UsersDao {
    private static final Logger logger = Logger.getLogger(UsersDaoImpl.class);
    public  final String ClassName= this.getClass().getName();
    
    @Override
    public User getById(long id) {
        logger.info("Class "+ClassName+" method getById started, id = " + id);
        User user = null;
    
        int iid = (int)id;

        try {
            try (Connection connection = new ConnectionManagerImpl().getConnection()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM user WHERE id= ?")) {
                    preparedStatement.setInt(1, iid);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            user = new User(
                                    resultSet.getLong("id")
                                    , resultSet.getString("login")
                                    , resultSet.getString("password")
                                    , resultSet.getLong("person_id"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException " + e.getMessage());
        }

        logger.info("Class "+ClassName+" method getById finished, id = " + id);
        return user;
    }

    @Override
    public User getByName(String name) {
        User result = null;

        try {
            try (Connection connection = new ConnectionManagerImpl().getConnection()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM user WHERE name= ?")) {
                    preparedStatement.setString(1, name);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {

                        while (resultSet.next()) {
                            User user = new User(
                                    resultSet.getLong("id")
                                    , resultSet.getString("login")
                                    , resultSet.getString("password")
                                    , resultSet.getLong("person_id"));
                            result = user;
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
    public List<User> getAll() {
        ArrayList<User> result = new ArrayList<>();

        try {
            try (Connection connection = new ConnectionManagerImpl().getConnection()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM users")) {

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            User user = new User(
                                    resultSet.getLong("id")
                                    , resultSet.getString("login")
                                    , resultSet.getString("password")
                                    , resultSet.getLong("person_id"));
                            result.add(user);
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
    public void add(User user) {
        logger.info("Class "+ClassName+" method add started");

        String sql = "INSERT INTO user (user_item, subject_item) VALUES (?,?)";

        execureStatement(user, sql);
        logger.info("Class "+ClassName+" method add finished");
    }

    private void execureStatement(User user, String sql) {
        try {
            try (Connection connection = new ConnectionManagerImpl().getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, user.getLogin());
                    statement.setString(2, user.getPassword());
                    statement.setLong(3, user.getPersonId());
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException " + e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        logger.info("Class "+ClassName+" method update started, id = " + user.getId());

        String sql = "UPDATE user SET login = ?, password = ?, person_id= ? WHERE id = "+user.getId()+"";

        execureStatement(user, sql);
        logger.info("Class "+ClassName+" method update finished, id = " + user.getId());
    }

    @Override
    public void deleteById(long id) {
        logger.info("Class "+ClassName+" method deleteById started, id = " + id);
        String sql = "DELETE FROM users WHERE id=?";
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
    }

    @Override
    public void addUsers(String login, String password, String role) {
        logger.info("add users start ");

        Connection connection = new ConnectionManagerImpl().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO users(login,password,role) VALUES (?,?,?)");

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        logger.info("add users finish");
    }
}
