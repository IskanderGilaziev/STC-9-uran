package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;

import java.util.List;

public interface ISpecialityService {

    Speciality getById(long id);

    void deleteById(long id);

    void addOrUpdate(Speciality speciality);

    List<Speciality> getAll();
}
