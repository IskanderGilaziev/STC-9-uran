package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;

import java.util.List;

public interface PersonService {

    Person getById(long id);

    /**
     * Определяет, можно ли сменить статус человека?
     * Если у человека определена учебная группа, оценки и т.д., то он остается студентом.
     * Если человек вел предметы - преподаватель.
     * В таких случаях статус изменять нельзя.
     *
     * @param person
     * @return
     */
    boolean isAvailableForStatusChange(Person person);

    /**
     * Перед обновлением данных человека проверяет, можно ли изменить его статус.
     * Статус не
     *
     * @param id
     * @return
     */
//    boolean smartUpdate(long id);

    void deleteById(long id);

    void addOrUpdate(Person person);

    List<Person> getAll();

    /**
     * selects a list of persons from the database whose user field is null and status is not unknown
     *
     * @return
     */
    List<Person> getAllegedPersonForModeration();
    void refreshPersonsDataOnModeration(Person oldId, Person newId);
    List<Person> getStudentById(long id);
    List<Person> getPersonByRoleAndNullUser(Status status);
}
