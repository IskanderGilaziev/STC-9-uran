package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Program;

import java.util.List;

public interface ProgramService {
    Program getById(long id);

    void deleteById(long id);

    void addOrUpdate(Program program);

    List<Program> getAll();


}
