package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;

import java.util.List;

public interface PersonDao {
    Person getById(long id);

    List<Person> getAllPersons();

    void addOrUpdatePerson(Person person);

    void deleteByPersonId(long id);

    List<Person> getPersonByRole(Status status);

    Person getByName(String name);

    void toDetached(Person person);

    /**
     * Person with status Student and without Group
     */
    List<Person> getAllPersonsInAllTeams();
}
