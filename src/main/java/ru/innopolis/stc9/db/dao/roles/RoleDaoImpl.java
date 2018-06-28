package ru.innopolis.stc9.db.dao.roles;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.pojo.realisationJDBC.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao {
    private static final Logger logger = Logger.getLogger(RoleDaoImpl.class);
    public  final String ClassName= this.getClass().getName();

    @Override
    public Role getById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method getById started, id = " + id);
        Role role = null;
    
        int iid = (int)id;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM roles WHERE id= ?")) {
                preparedStatement.setInt(1, iid);
                try (ResultSet  resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        role = new Role(
                                resultSet.getLong("id")
                                , resultSet.getString("name"));
                    }
                }                
            }
        }

        logger.info("Class "+ClassName+" method getById finished, id = " + id);
        return role;
    }

    @Override
    public Role getByName(String name) throws SQLException {
        Role result = null;        

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM roles WHERE name= ?")) {
                preparedStatement.setString(1, name);
                try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                   
                    while (resultSet.next()) {
                        Role role = new Role(
                                resultSet.getLong("id")
                                , resultSet.getString("name"));
                        result = role;
                    }
                } 
            }
        }
        return result;
    }

    @Override
    public List<Role> getAll() throws SQLException {
        ArrayList<Role> result = new ArrayList<>();
      
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM roles")) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Role role = new Role(
                                resultSet.getLong("id")
                                , resultSet.getString("name"));
                        result.add(role);
                    } 
                }                   
            }
        }

        return result;
    }

    @Override
    public void add(Role role) throws SQLException {
        logger.info("Class SheduleDaoImpl method add started");

        String sql = "INSERT INTO roles (name) VALUES (?)";

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, role.getName());

                statement.executeUpdate();
            }
        }
        logger.info("Class "+ClassName+" method add finished");
    }


    @Override
    public void update(Role role) throws SQLException {
        logger.info("Class "+ClassName+" method update started, id = " + role.getId());

        String sql = "UPDATE roles SET name = ? WHERE id = ?";

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, role.getName());
                statement.setLong(2, role.getId());
                statement.executeUpdate();
            }
        }
        logger.info("Class "+ClassName+" method update finished, id = " + role.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method deleteById started, id = " + id);
        String sql = "DELETE FROM roles WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
        logger.info("Class "+ClassName+" method deleteById finished, id = " + id);
    }
}
