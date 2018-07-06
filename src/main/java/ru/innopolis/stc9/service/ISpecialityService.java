package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.Speciality;

import java.util.List;

public interface ISpecialityService {
    boolean updateById(Speciality speciality);

    Speciality getById(long id);

    boolean deleteById(long id);

    boolean add(Speciality speciality);

    List<Speciality> getAll();
}
