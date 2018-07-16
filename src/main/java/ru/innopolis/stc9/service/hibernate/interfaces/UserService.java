package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.User;

import java.security.Principal;

public interface UserService {

    void deleteById(User user);

    void saveOrUpdate(User user);

    /**
     * Set security role based on user status
     *
     * @param person
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

    Person getByUserName(Principal principal);
}
