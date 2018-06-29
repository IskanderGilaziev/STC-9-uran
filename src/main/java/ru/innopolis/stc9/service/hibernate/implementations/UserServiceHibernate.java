package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.PersonDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.UserDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;
import ru.innopolis.stc9.pojo.hibernate.entities.User;
import ru.innopolis.stc9.service.hibernate.interfaces.UserService;

@Service
public class UserServiceHibernate implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceHibernate.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : ";
    private static final String DEBUC_AFTER = "Before exit.";
    private static final String[] securityRoles = new String[]{"ROLE_ADMIN", "ROLE_USER"};

    private final BCryptPasswordEncoder bcryptEncoder;

    private final UserDao userDao;

    private final PersonDao personDao;

    @Autowired
    public UserServiceHibernate(BCryptPasswordEncoder bcryptEncoder, UserDao userDao, PersonDao personDao) {
        this.bcryptEncoder = bcryptEncoder;
        this.userDao = userDao;
        this.personDao = personDao;
    }

    /**
     * Ban/unban the user
     *
     * @param user
     */
    @Override
    public void changeEnable(User user) {
        logger.debug(DEBUG_BEFORE);
        if (user != null) {
            user.setEnabled(Math.abs(user.getEnabled() - 1));
            userDao.updateUserData(user);
            logger.info("Enabled was changed");
        } else {
            logger.warn(WARN_NPE + "User.");
        }
        logger.debug(DEBUC_AFTER);
    }

    @Override
    public boolean checkPasswords(String password, String passwordConfirm) {
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
    @Override
    public boolean signUpUser(String personName, String email, String login, String password, String passwordConfirm) {
        boolean result;
        checkPasswords(password, passwordConfirm);
        boolean isFirstAdmin = userDao.userCountWithRole(securityRoles[0]) == 0;
        Person person = new Person(personName, email);
        person.setStatus(Status.unknown);
        User user = new User(login, bcryptEncoder.encode(password));
        user.setEnabled(0);
        user.setRole(securityRoles[1]);
        if (isFirstAdmin) {
            setRoot(person, user);
        }
        personDao.addOrUpdatePerson(person);
        user.setPerson(person);
        userDao.addUser(user);
        result = true;
        logger.info(person);
        return result;
    }

    private void setRoot(Person person, User user) {
        person.setStatus(Status.admin);
        user.setEnabled(1);
        user.setRole(securityRoles[0]);
    }

}
