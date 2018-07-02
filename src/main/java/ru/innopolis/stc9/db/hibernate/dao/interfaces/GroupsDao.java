package ru.innopolis.stc9.db.hibernate.dao.interfaces;


import ru.innopolis.stc9.pojo.hibernate.entities.Group;

import java.sql.SQLException;
import java.util.List;

public interface GroupsDao {
    Group getById(long id);

    Group getByProgram(String name);

    List<Group> getAllGroup();

    void addGroup(Group group);

    void updateGroup(Group group);

    void deleteGroupById(long id);

    /**
     * Select all groups from db planned to start education
     *
     * @return
     * @throws SQLException
     */
    List<Group> getListOfNewGroups();

    /**
     * Select archive groups from db
     *
     * @return
     * @throws SQLException
     */
    List<Group> getListOfOldGroups();

    /**
     * Select groups in education.
     *
     * @return
     * @throws SQLException
     */
    List<Group> getListOfCurrentGroups();
}
