package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Program;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;

import java.util.List;

public interface ProgramDao {
    
    Program getById(long id);

    List<Program> getAllPrograms();

    void addOrUpdateProgram(Program program);

    void deleteByProgramId(long id);

    List<Program> getProgramByRole(int role);

    List<Program> getProgramByRoleAndNullUser(Status status);

    Program getByName(String name);

    void toDetached(Program program);
}
