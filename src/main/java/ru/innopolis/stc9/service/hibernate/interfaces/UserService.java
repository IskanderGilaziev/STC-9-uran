package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.pojo.hibernate.entities.User;

import java.util.Set;

public interface UserService {

    void deleteById(User user);

    void saveOrUpdate(User user);

    /**
     * Set security role based on user status
     *
     * @param person
     * @return
     */
    void setSecurityRole(Person person);

    /**
     * Ban/unban the user
     *
     * @param person
     */
    void changeEnable(Person person);

    boolean checkPasswords(String password, String passwordConfirm);

    /**
     * Service of data processing from the user registration form
     *
     * @param personName
     * @param email
     * @param login
     * @param password
     * @param passwordConfirm
     * @return
     */
    boolean signUpUser(String personName, String email, String login, String password, String passwordConfirm);

    Person getByUserName(String login);

    /**
     * Узнать по каким предметам у авторизованного студента уже есть оценки
     *
     * @param login
     * @return
     */
    Set<Subject> getSubjectsForStudentByUser(String login);

}
