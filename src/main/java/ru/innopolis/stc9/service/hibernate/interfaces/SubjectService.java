package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import java.util.List;

public interface SubjectService {

    Subject getById(long id);

    void deleteById(long id);

    void addOrUpdate(Subject subject);

    /**
     * Обновить существующий предмет
     *
     * @param id
     * @param name
     */
    void updateExitingSubject(long id, String name);

    List<Subject> getAll();
}
