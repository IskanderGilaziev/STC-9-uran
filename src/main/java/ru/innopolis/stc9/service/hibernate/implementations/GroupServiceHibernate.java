package ru.innopolis.stc9.service.hibernate.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.TeamDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Team;
import ru.innopolis.stc9.service.hibernate.interfaces.GroupService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class GroupServiceHibernate implements GroupService {
    private final TeamDao teamDao;

    @Autowired
    public GroupServiceHibernate(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    /**
     * Список всех групп в системе
     *
     * @return
     */
    @Override
    public List<Team> getAllTeams() {
        List<Team> list = teamDao.getAll();
        return list;
    }
}
