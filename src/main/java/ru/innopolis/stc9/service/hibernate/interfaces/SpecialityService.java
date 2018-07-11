package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.pojo.hibernate.entities.Team;

import java.util.List;

public interface SpecialityService {

    Speciality getById(long id);

    void deleteById(long id);

    /**
     * По умолчанию специальность создается активной.
     *
     * @param name
     * @param yTotal
     */
    void addNew(String name, int yTotal);

    /**
     * Обновить существующую специальность, не затрагивая ее статуса действующая/архивная
     *
     * @param id
     * @param name
     * @param yTotal
     */
    void updateExiting(long id, String name, int yTotal);

    List<Speciality> getAll();

    /**
     * Список предметов, которых нет в данной специальности
     *
     * @param speciality
     * @return
     */
    List<Subject> getSuitSubjects(Speciality speciality);

    /**
     * Список групп, которых не обучаются по данной специальности
     *
     * @param speciality
     * @return
     */
    List<Team> getSuitGroups(Speciality speciality);

    /**
     * Добавить к специальности c указанным id предмет с указанным id
     *
     * @param specialityId
     * @param subjectId
     */
    void addSubject(long specialityId, long subjectId);

    /**
     * Уалить из специальности учебную дисциплину
     *
     * @param specialityId
     * @param subjectId
     */
    void delSubjectFromSpeciality(long specialityId, long subjectId);

    /**
     * Добавить к специальности c указанным id учебную группу с указанным id
     *
     * @param specialityId
     * @param teamId
     */
    void addTeam(long specialityId, long teamId);

    /**
     * Удалить группу из специальности (а точнее, специальность из группы)
     *
     * @param teamId
     */
    void delTeamFromSpecialty(long teamId);

    /**
     * Помещает специальность в статус архива
     * (т.е. по данной специальности можно только доучить уже назначенные группы, но нельзя начать обучать новую группу)
     * или наоборот, делает ее активной.
     *
     * @param speciality id специальности
     */
    void changeStatus(long speciality);
}
