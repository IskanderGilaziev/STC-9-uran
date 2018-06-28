package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.User;

import java.util.List;

public interface UserService {

    void deleteById(User user);

    List<User> getAll();

    User getById(Long id);

    void addUser(User user);

    /**
     * Ban/unban the user
     *
     * @param user
     */
    void changeEnable(User user);

    boolean checkData(String password, String passwordConfirm);

    boolean signUpUser(String personName, String email, String login, String password, String passwordConfirm);

}
