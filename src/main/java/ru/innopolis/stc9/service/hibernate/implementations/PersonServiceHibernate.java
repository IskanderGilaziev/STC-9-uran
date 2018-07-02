package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.PersonDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.StudentDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.TeacherDao;
import ru.innopolis.stc9.pojo.hibernate.entities.*;
import ru.innopolis.stc9.service.hibernate.interfaces.PersonService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceHibernate implements PersonService {
    private static final Logger logger = Logger.getLogger(PersonServiceHibernate.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : ";
    private static final String DEBUC_AFTER = "Before exit.";

    private final PersonDao personDao;
    private final StudentDao studentDao;
    private final TeacherDao teacherDao;

    @Autowired
    public PersonServiceHibernate(PersonDao personDao, StudentDao studentDao, TeacherDao teacherDao) {
        this.personDao = personDao;
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
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
    public void addOrUpdate(Person person) {
        logger.debug(DEBUG_BEFORE);
        if (person != null) {
            if (person.getId() == 0) {
                personDao.addOrUpdatePerson(person);
                logger.info("Add new person");
                Status requiredStatus = person.getStatus();
                switch (requiredStatus) {
                    case student:
                        Student student = new Student(person, null);
                        studentDao.addNewStudent(student);
                        break;
                    case teacher:
                        Teacher teacher = new Teacher(person);
                        teacherDao.addNewTeacher(teacher);
                        break;
                }
                int u = 0;
            } else {
                if (person.getId() > 0) {
                    logger.info("Update the existing object data.");
                } else {
                    logger.warn("negative id.");
                }
            }
        } else {
            logger.warn(WARN_NPE + "person.");
        }
        int u = 0;
        Person persistPerson = personDao.getById(person.getId());

        if (person.getStatus().equals(Status.student)) {
            Student persistStudent = persistPerson.getStudent();
            Student student = new Student();
        }
        personDao.addOrUpdatePerson(person);
        logger.info(this.getClass().getName() + " method add finished");
    }

    private void addStudent() {
    }

    private void addTeacher() {
    }

    @Override
    public List<Person> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Person> personList;
        personList = personDao.getAllPersons();
        logger.info(this.getClass().getName() + " method getAll finished");
        return personList;
    }

    @Override
    public List<Person> getAllStudents() {
        List<Person> list = personDao.getPersonByRole(Status.student);
        return list;
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
                List<Person> statusPerson = personDao.getPersonByRole(s);
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
    public List<Person> getTeachers() {
        logger.info(this.getClass().getName() + " method getTeachers started");
        List<Person> teacherList;
        teacherList = personDao.getPersonByRole(Status.teacher);
        logger.info(this.getClass().getName() + " method getTeachers finished");
        return teacherList;
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
}
