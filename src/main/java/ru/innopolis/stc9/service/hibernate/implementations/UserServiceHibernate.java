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

import java.security.Principal;

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

    @Override
    public void deleteById(User user) {
        if (user != null && user.getId() > 0) {
            userDao.deleteByUserId(user);
        }
    }

    @Override
    public void saveOrUpdate(User user) {
        if (user != null && user.getPerson() != null) {
            userDao.addUser(user);
        }
    }

    /**
     * Set security role based on user status
     *
     * @param person
     */
    @Override
    public void setSecurityRole(Person person) {
        String security = securityRole(person.getStatus());
        User user = person.getUser();
        user.setRole(security);
        userDao.updateUserData(user);
        personDao.addOrUpdatePerson(person);
    }

    /**
     * Return security role by person status
     *
     * @param status
     * @return
     */
    private String securityRole(Status status) {
        int index = 0;
        switch (status) {
            case unknown:
            case student:
                index = 1;
        }
        String result = securityRoles[index];
        return result;
    }

    /**
     * Ban/unban the user
     *
     * @param person
     */
    @Override
    public void changeEnable(Person person) {
        logger.debug(DEBUG_BEFORE);
        if (person != null && person.getUser() != null) {
            User user = person.getUser();
            user.setEnabled(Math.abs(person.getUser().getEnabled() - 1));
            userDao.updateUserData(user);
            person.setUser(user);
            personDao.addOrUpdatePerson(person);
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
        if (userDao.getByLogin(login) == null) {
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
        } else {
            result = true;
        }
        return result;
    }

    private void setRoot(Person person, User user) {
        person.setStatus(Status.admin);
        user.setEnabled(1);
        user.setRole(securityRoles[0]);
    }

    @Override
    public Person getByUserName(Principal principal) {
        Person person = null;
        String login = principal.getName();
        if (login != null && !login.isEmpty()) {
            User user = userDao.getByLogin(login);
            person = user.getPerson();
        }
        return person;
    }
}
