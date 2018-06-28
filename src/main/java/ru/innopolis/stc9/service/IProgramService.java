package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.realisationJDBC.Program;

import java.util.List;

public interface IProgramService {
    void update(Program program);

    Program getById(long id);

    void deleteById(long id);

    void add(Program program);

    List<Program> getAll();
}
