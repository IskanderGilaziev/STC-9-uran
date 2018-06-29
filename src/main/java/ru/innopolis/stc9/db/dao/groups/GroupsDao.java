package ru.innopolis.stc9.db.dao.groups;

import ru.innopolis.stc9.pojo.realisationJDBC.Group;

import java.sql.SQLException;
import java.util.List;

public interface GroupsDao {
    Group getById(long id) throws SQLException;

    Group getByProgram(String name) throws SQLException;

    List<Group> getAll() throws SQLException;

    void add(Group group) throws SQLException;

    void update(Group group) throws SQLException;

    void deleteById(long id) throws SQLException;

    /**
     * Select all groups from db planned to start education
     *
     * @return
     * @throws SQLException
     */
    List<Group> getListOfNewGroups() throws SQLException;

    /**
     * Select archive groups from db
     *
     * @return
     * @throws SQLException
     */
    List<Group> getListOfOldGroups() throws SQLException;

    /**
     * Select groups in education.
     *
     * @return
     * @throws SQLException
     */
    List<Group> getListOfCurrentGroups() throws SQLException;
}
