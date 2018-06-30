package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.PersonDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.UserDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;
import ru.innopolis.stc9.pojo.hibernate.entities.User;
import ru.innopolis.stc9.service.hibernate.interfaces.PersonService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceHibernate implements PersonService {
    private static final Logger logger = Logger.getLogger(PersonServiceHibernate.class);

    private final PersonDao personDao;

    private final UserDao userDao;

    @Autowired
    public PersonServiceHibernate(PersonDao personDao, UserDao userDao) {
        this.personDao = personDao;
        this.userDao = userDao;
    }

    //    @Override
//    public void updateById(Person person) {
//        logger.info(this.getClass().getName() + " method updateById started, id = " + person.getId());
//        personDao.addOrUpdatePerson(person);
//        logger.info(this.getClass().getName() + " method updateById finished, id = " + person.getId());
//    }

    @Override
    public Person getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Person person = null;
        person = personDao.getById(id);
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return person;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        personDao.deleteByPersonId(id);
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void addOrUpdate(Person person) {
        logger.info(this.getClass().getName() + " method add started");
        personDao.addOrUpdatePerson(person);
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<Person> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Person> personList;
        personList = personDao.getAllPersons();
        logger.info(this.getClass().getName() + " method getAll finished");
        return personList;
    }

    /**
     * selects a list of persons from the database whose user field is null and status is not unknown
     *
     * @return
     */
    @Override
    public List<Person> getAllegedPersonForModeration() {
        List<Person> allegedPerson = new ArrayList<>();
        Status[] statuses = Status.values();
        for (Status s : statuses) {
            if (!s.equals(Status.unknown)) {
                List<Person> statusPerson = personDao.getPersonByRoleAndNullUser(s);
                removeFromListWithNullUser(statusPerson);
                allegedPerson.addAll(statusPerson);
            }
        }
        return allegedPerson;
    }

    private void removeFromListWithNullUser(List<Person> listOfPerson) {
        for (int i = 0; i < listOfPerson.size(); i++) {
            User user = listOfPerson.get(i).getUser();
            if (user != null) {
                listOfPerson.remove(i);
            }
        }
    }

    @Override
    public List<Person> getTeachers() {
        logger.info(this.getClass().getName() + " method getTeachers started");
        List<Person> teacherList;
        teacherList = personDao.getPersonByRole(3);
        logger.info(this.getClass().getName() + " method getTeachers finished");
        return teacherList;
    }

    @Override
    public void refreshPersonsDataOnModeration(long oldId, long newId) {
        Person oldPerson = personDao.getById(oldId);
        Person registeredPerson = personDao.getById(newId);
        if (oldPerson.getBirthday() == null && registeredPerson.getBirthday() != null) {
            oldPerson.setBirthday(registeredPerson.getBirthday());
        }
        if (oldPerson.getEmail() == null && registeredPerson.getEmail() != null && !registeredPerson.getEmail().isEmpty()) {
            oldPerson.setEmail(registeredPerson.getEmail());
        }
        personDao.addOrUpdatePerson(oldPerson);
    }
}
