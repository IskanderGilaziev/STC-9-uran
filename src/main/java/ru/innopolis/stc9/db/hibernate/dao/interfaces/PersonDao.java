package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Person;

import java.util.List;

public interface PersonDao {
    Person getById(long id);

    List<Person> getAllPersons();

    void addPerson(Person person);

    void updatePerson(Person person);

    void deleteByPersonId(long id);

    List<Person> getPersonByRole(int role);

    Person getByName(String name);
}
