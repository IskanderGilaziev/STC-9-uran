package ru.innopolis.stc9.service;

import java.util.List;

public interface ISpecialityService {
    void updateById(Speciality speciality);

    Speciality getById(long id);

    void deleteById(long id);

    void add(Speciality speciality);

    List<Speciality> getAll();
}
