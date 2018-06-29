package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.realisationJDBC.Performance;

import java.util.List;

public interface IPerformanceService {
    void updateById(Performance performance);

    Performance getById(long id);

    void deleteById(long id);

    void add(Performance performance);

    List<Performance> getAll();
}
