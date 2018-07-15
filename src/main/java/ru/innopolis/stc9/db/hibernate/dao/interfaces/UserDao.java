package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.pojo.hibernate.entities.User;

import java.util.List;
import java.util.Set;

public interface UserDao {

    User getById(long id);

    User getByLogin(String name);

    List<User> getAllUsers();

    void addUser(User user);

    void addUserEvict(User user);

    void updateUserData(User user);

    void deleteByUserId(User user);

    /**
     * Return number of rows where users have a required security role.
     *
     * @param securityRole
     * @return
     */
    long userCountWithRole(String securityRole);

    Set<Subject> allSubjectsForStudent(String login);

}
