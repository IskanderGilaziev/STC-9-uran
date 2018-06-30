package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.User;

public interface UserService {

    void deleteById(User user);

    /**
     * Ban/unban the user
     *
     * @param user
     */
    void changeEnable(User user);

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


}
