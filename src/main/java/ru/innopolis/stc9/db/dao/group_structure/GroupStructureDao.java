package ru.innopolis.stc9.db.dao.group_structure;

import ru.innopolis.stc9.pojo.realisationJDBC.GroupStructure;

import java.sql.SQLException;
import java.util.List;

public interface GroupStructureDao {
    GroupStructure getById(long id) throws SQLException;

    GroupStructure getByGroup(Long group) throws SQLException;

    List<GroupStructure> getAll() throws SQLException;

    void add(GroupStructure groupStructure) throws SQLException;

    void update(GroupStructure groupStructure) throws SQLException;

    void deleteById(long id) throws SQLException;
}
