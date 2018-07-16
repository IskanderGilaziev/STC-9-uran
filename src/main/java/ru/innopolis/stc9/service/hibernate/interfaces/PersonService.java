package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;

import java.sql.Date;
import java.util.List;

public interface PersonService {

    Person getById(long id);

    void deleteById(long id);

    void addOrUpdate(Person person);

    /**
     * Перед внесением изменений по статусу происходит проверка содержимого БД.
     * Если есть группа - статус не изменить, Проводил уроки - статус не изменить и т.д.
     *
     * @param id
     * @param name
     * @param birthday
     * @param email
     * @param status
     */
    void updateExitingPerson(long id, String name, Date birthday, String email, Status status);

    List<Person> getAll();

    /**
     * selects a list of persons from the database whose user field is null and status is not unknown
     *
     * @return
     */
    List<Person> getAllegedPersonForModeration();

    void refreshPersonsDataOnModeration(Person oldId, Person newId);

    List<Person> getStudentById(long id);

    List<Person> getPersonsByRole(Status status);
}
