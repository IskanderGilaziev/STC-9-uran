package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;

import java.util.List;

public interface PersonDao {
    Person getById(long id);

    List<Person> getAllPersons();

    void addOrUpdatePerson(Person person);

    void deleteByPersonId(long id);

//    List<Person> getPersonByRole(Status status);

    /**
     * Выбрать всех персон в системе по статусу
     *
     * @param status
     * @return
     */
    List<Person> getPersonsByRole(Status status);

    /**
     * Поиск из таблицы Person строк со статусом студент и null а колонке "группа"
     *
     * @return
     */
    List<Person> getAllSuitStudentsForTeam();

    /**
     * Удалить студента из группы
     *
     * @param personId
     */
    void deletePersonFromGroup(long personId);

    Person getByName(String name);

    void toDetached(Person person);

    /**
     * Найти человека по его логину
     *
     * @param login
     * @return
     */
    Person getByLogin(String login);
}
