package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Person;

import java.util.List;

public interface PersonService {
    void updateById(Person person);

    Person getById(long id);

    void deleteById(long id);

    void add(Person person);

    List<Person> getAll();

    List<Person> getTeachers();
}
