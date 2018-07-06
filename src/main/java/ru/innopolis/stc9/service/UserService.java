package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.users.UsersDao;
import ru.innopolis.stc9.pojo.User;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService implements IUserService {
    private static final Logger logger = Logger.getLogger(UserService.class);
    private static final Logger loggerError = Logger.getLogger(UserService.class);


    private UsersDao userDao;

    @Autowired
    BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    public UserService(UsersDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void update(User user) {
        logger.info(this.getClass().getName() + " method updateById started, id = " + user.getId());
            userDao.update(user);
        logger.info(this.getClass().getName() + " method updateById finished, id = " + user.getId());
    }


    @Override
    public User getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        User user = null;
            user = userDao.getById(id);
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return user;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
            userDao.deleteById(id);
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void add(User user) {
        logger.info(this.getClass().getName() + " method add started");
            userDao.add(user);
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<User> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<User> userList = new ArrayList<>();
            userList = userDao.getAll();
        logger.info(this.getClass().getName() + " method getAll finished");
        return userList;
    }

    @Override
    public void addUsers(String login, String pass, String role) {
        String cryptPassword = bcryptEncoder.encode(pass);
        userDao.addUsers(login, cryptPassword, role);
        logger.info("add users");
    }
}
