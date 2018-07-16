package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.LessonDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.PersonDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;
import ru.innopolis.stc9.pojo.hibernate.entities.User;
import ru.innopolis.stc9.service.hibernate.interfaces.PersonService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceHibernate implements PersonService {
    private static final Logger logger = Logger.getLogger(PersonServiceHibernate.class);

    private final PersonDao personDao;
    private final LessonDao lessonDao;

    @Autowired
    public PersonServiceHibernate(PersonDao personDao, LessonDao lessonDao) {
        this.personDao = personDao;
        this.lessonDao = lessonDao;
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

    /**
     * Перед внесением изменений по статусу происходит проверка содержимого БД.
     * Если есть группа - статус не изменить, Проводил уроки - статус не изменить и т.д.
     *
     * @param id
     * @param name
     * @param birthday
     * @param email
     * @param status
     */
    @Override
    public void updateExitingPerson(long id, String name, Date birthday, String email, Status status) {
        if (validateInputs(name, birthday, email, status)) {
            Person person = personDao.getById(id);
            if (person != null) {
                person.setName(name);
                person.setBirthday(birthday);
                person.setEmail(email);
                if (canChangeStatus(person)) {
                    person.setStatus(status);
                } else {
                    logger.info("Person status can not be changed.");
                }
                personDao.addOrUpdatePerson(person);
            } else {
                logger.warn("Can not find person with id = " + id);
            }
        } else {
            logger.warn("Wrong inputs.");
        }
    }

    private boolean validateInputs(String name, Date birthday, String email, Status status) {
        return name != null && !name.isEmpty()
                && birthday != null && birthday.after(Date.valueOf("1900-01-01"))
                && email != null && !email.isEmpty() && status != null;
    }

    /**
     * Проверяет, можем ли мы изменить статус пользователя.
     *
     * @param person
     * @return
     */
    private boolean canChangeStatus(Person person) {
        boolean canChangeStatus;
        switch (person.getStatus()) {
            case student:
                canChangeStatus = person.getTeam() == null && person.getPerformances().isEmpty();
                break;
            case admin:
            case manager:
            case teacher:
                canChangeStatus = lessonDao.getLessonCountByPerson(person) == 0;
                break;
            default:
                canChangeStatus = true;
        }
        return canChangeStatus;
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
