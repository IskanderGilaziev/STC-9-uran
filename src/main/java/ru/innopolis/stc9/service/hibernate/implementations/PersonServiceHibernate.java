package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.PersonDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.service.hibernate.interfaces.PersonService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceHibernate implements PersonService {
    private static final Logger logger = Logger.getLogger(PersonServiceHibernate.class);
    private static final Logger loggerError = Logger.getLogger(PersonServiceHibernate.class);

    @Autowired
    private PersonDao personDao;

    @Override
    public void updateById(Person person) {
        logger.info(this.getClass().getName() + " method updateById started, id = " + person.getId());
        personDao.updatePerson(person);
        logger.info(this.getClass().getName() + " method updateById finished, id = " + person.getId());
    }

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
    public void add(Person person) {
        logger.info(this.getClass().getName() + " method add started");
        personDao.addPerson(person);
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<Person> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Person> personList = new ArrayList<>();
        personList = personDao.getAllPersons();
        logger.info(this.getClass().getName() + " method getAll finished");
        return personList;
    }

    @Override
    public List<Person> getTeachers() {
        logger.info(this.getClass().getName() + " method getTeachers started");
        List<Person> teacherList = new ArrayList<>();
        teacherList = personDao.getPersonByRole(3);
        logger.info(this.getClass().getName() + " method getTeachers finished");
        return teacherList;
    }
}
