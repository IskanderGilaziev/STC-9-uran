package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.PersonDao;
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

    @Autowired
    public PersonServiceHibernate(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public Person getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Person person = personDao.getById(id);
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
                List<Person> statusPerson = personDao.getPersonsByRole(s);
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
                i--;
            }
        }
    }

    @Override
    public List<Person> getPersonsByRole(Status status) {
        logger.info(this.getClass().getName() + " method getPersonsByRole started");
        List<Person> personList;
        personList = personDao.getPersonsByRole(status);
        logger.info(this.getClass().getName() + " method getPersonsByRole finished");
        return personList;
    }

    @Override
    public void refreshPersonsDataOnModeration(Person oldPerson, Person registeredPerson) {
        if (oldPerson.getBirthday() == null && registeredPerson.getBirthday() != null) {
            oldPerson.setBirthday(registeredPerson.getBirthday());
        }
        if (oldPerson.getEmail() == null && registeredPerson.getEmail() != null && !registeredPerson.getEmail().isEmpty()) {
            oldPerson.setEmail(registeredPerson.getEmail());
        }
        personDao.addOrUpdatePerson(oldPerson);
    }

    @Override
    public List<Person> getStudentById(long id) {
        logger.info(this.getClass().getName() + " method getStudentById started, id = " + id);
        List<Person> studentList;
        studentList = personDao.getPersonsByRole(Status.student);
        logger.info(this.getClass().getName() + " method getStudentById finished, id = " + id);
        return studentList;
    }


}
