package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.User;

import java.util.List;

public interface UserDao {

    User getById(long id);

    User getByLogin(String name);

    List<User> getAllUsers();

    void addUser(User user);

    void addUserEvict(User user);

    void updateUserData(User user);

    void deleteByUserId(User user);

    // TODO: 26.06.2018 Отказаться от этого метода
//    long addUsers(String login, String password, String role, int enabled, long personId);

}
