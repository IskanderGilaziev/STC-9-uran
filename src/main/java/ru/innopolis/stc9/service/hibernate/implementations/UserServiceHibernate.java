package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.roles.RoleDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.PersonDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.UserDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.User;
import ru.innopolis.stc9.pojo.realisationJDBC.Role;
import ru.innopolis.stc9.service.hibernate.interfaces.UserService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceHibernate implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceHibernate.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : ";
    private static final String DEBUC_AFTER = "Before exit.";
    private static final String ROLE_FROM_ROLES_FOR_SECURITY_USERS_ROLE = "Студент";
    private static final String[] securityRoles = new String[]{"ROLE_USER", "ROLE_ADMIN"};

    @Autowired
    BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    UserDao userDao;

    @Autowired
    PersonDao personDao;

    @Autowired
    RoleDao roleDao;

    @Override
    public void deleteById(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    /**
     * Ban/unban the user
     *
     * @param user
     */
    @Override
    public void changeEnable(User user) {

    }

    @Override
    public boolean checkData(String password, String passwordConfirm) {
        logger.debug(DEBUG_BEFORE);
        boolean result = false;
        if (password != null && passwordConfirm != null && !password.isEmpty() && !passwordConfirm.isEmpty()) {
            result = password.equals(passwordConfirm);
        } else {
            logger.warn("Null or empty password");
        }
        logger.info("result = " + result);
        return result;
    }

    @Override
    public boolean signUpUser(String personName, String email, String login, String password, String passwordConfirm) {
        boolean result = false;
        checkData(password, passwordConfirm);
        User user = new User();
        user.setLogin(login);
        user.setPassword(bcryptEncoder.encode(password));

        List<User> listOfAllUser = null;
        try {
            listOfAllUser = userDao.getAllUsers();
//            personDao.getAllPersons();
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
        }
        if (listOfAllUser != null && listOfAllUser.isEmpty()) {
            // TODO: 28.06.2018 root
            Person tmpPerson = new Person();
            tmpPerson.setName(personName);
            tmpPerson.setEmail(email);
//            tmpPerson.setId(1);
            try {
                Role properRole = roleDao.getByName("Администратор");
                tmpPerson.setRole(properRole.getId());
                tmpPerson.setBirthday(new Date(1999, 12, 31));
                personDao.addPerson(tmpPerson);
                logger.info(tmpPerson);
                Person p = personDao.getByName(personName);

                user.setRole(getUserSecurityRole(tmpPerson));
                user.setEnabled(1);
//                tmpPerson.setUser(user);
//                tmpPerson.setUser(user);
//                personDao.addPerson(tmpPerson);
//                personDao.addPerson(tmpPerson);
//                Person p = personDao.getByName(personName);
//                tmpPerson.setUser(user);
//            personDao.addPerson(tmpPerson);
                user.setPerson(tmpPerson);

                userDao.addUser(user);
                logger.info(tmpPerson.toString());
                int u = 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {


            user.setEnabled(0);

            Person person = personDao.getByName(personName);
            person.setEmail(email);
            personDao.updatePerson(person);
            user.setRole(getUserSecurityRole(person));
            user.setEnabled(1);
            user.setPerson(person);
            userDao.addUser(user);
            result = true;
        }
//    }else
//
//    {
//
//    }

        //        } catch (SQLException e) {
//            logger.error(e.getMessage());
//        }
        int u = 0;
        return result;
    }

    private String getUserSecurityRole(Person person) {
        logger.debug("Person " + person.getName() + " wants to register.");
        String result = null;
        if (person != null) {
            try {
                Role properRole = roleDao.getById(person.getRole());
                List<Role> roles = roleDao.getAll();
                if (properRole != null && roles.contains(properRole)) {
                    result = properRole.equals(roleDao.getByName(ROLE_FROM_ROLES_FOR_SECURITY_USERS_ROLE)) ? "ROLE_USER" : "ROLE_ADMIN";
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        logger.info(result != null ? "The user with " + person.getName() + " is " + result : "The system does not assume the user with the specified data.");
        return result;
    }
}
