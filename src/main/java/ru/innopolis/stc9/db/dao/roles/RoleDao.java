package ru.innopolis.stc9.db.dao.roles;

import ru.innopolis.stc9.pojo.realisationJDBC.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDao {
    Role getById(long id) throws SQLException;

    Role getByName(String name) throws SQLException;

    List<Role> getAll() throws SQLException;

    void add(Role role) throws SQLException;

    void update(Role role) throws SQLException;

    void deleteById(long id) throws SQLException;
}
