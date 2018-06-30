package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Person;

import java.util.List;

public interface PersonService {
//    void updateById(Person person);

    Person getById(long id);

    void deleteById(long id);

    void addOrUpdate(Person person);

    List<Person> getAll();

    /**
     * selects a list of persons from the database whose user field is null and status is not unknown
     *
     * @return
     */
    List<Person> getAllegedPersonForModeration();

    void refreshPersonsDataOnModeration(long oldId, long newId);

    List<Person> getTeachers();
}
