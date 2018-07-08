package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SpecialityDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SubjectDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.service.hibernate.interfaces.SpecialityService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SpecialityServiceHibernate implements SpecialityService {
    private static final Logger logger = Logger.getLogger(SpecialityServiceHibernate.class);

    private final SpecialityDao specialityDao;
    private final SubjectDao subjectDao;

    @Autowired
    public SpecialityServiceHibernate(SpecialityDao specialityDao, SubjectDao subjectDao) {
        this.specialityDao = specialityDao;
        this.subjectDao = subjectDao;
    }

    @Override
    public List<Speciality> getAllSpecialty() {
        return specialityDao.getAllSpeciality();
    }

    /**
     * Создает новую специальность. По умолчанию для создания необходимо указать хотя бы один предмет.
     *
     * @param name
     * @param yTotal
     * @param isActive
     * @param subjectId
     * @return
     */
    @Override
    public boolean createNewSpecialty(String name, int yTotal, boolean isActive, long subjectId) {
        boolean result = false;
        if (name != null && !name.isEmpty() && yTotal > 0 && subjectId > 0) {
            Subject subject = subjectDao.getById(subjectId);
            if (subject != null) {
                Speciality newSpeciality = new Speciality(name, yTotal);
                newSpeciality.setActive(isActive);
                result = specialityDao.addOrUpdateSpeciality(newSpeciality);
                if (result) {
                    result = subjectDao.addNewSpecialty(subjectId, newSpeciality);
                }
            }
        }
        return result;
    }

    @Override
    public Speciality getById(long id) {
        Speciality speciality = null;
        if (id > 0) {
            speciality = specialityDao.getById(id);
        }
        return speciality;
    }

    @Override
    public boolean updateSpecialty(long specialityId, String newName, int newYTotal) {
        boolean result = false;
        if (specialityId > 0 && newName != null && !newName.isEmpty() && newYTotal > 0) {
            Speciality speciality = specialityDao.getById(specialityId);
            if (speciality != null) {
                speciality.setName(newName);
                speciality.setyTotal(newYTotal);
                result = specialityDao.addOrUpdateSpeciality(speciality);
            }
        }
        return result;
    }

    @Override
    public boolean delete(long id) {
        boolean result = false;
        if (id > 0) {
            Speciality speciality = specialityDao.getById(id);
            if (speciality != null) {
                result = specialityDao.deleteSpecialityFull(speciality);
            }
        }
        return result;
    }
}
