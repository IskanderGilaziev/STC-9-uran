package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.roles.RoleDao;
import ru.innopolis.stc9.pojo.realisationJDBC.Role;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {

    private static final Logger logger = Logger.getLogger(RoleService.class);
    private static final Logger loggerError = Logger.getLogger(RoleService.class);

    @Autowired
    private RoleDao roleDao;

    @Override
    public void update(Role role) {
        logger.info(this.getClass().getName() + " method update started, id = " + role.getId());
        try {
            roleDao.update(role);
        } catch (SQLException e) {
            loggerError.error("Error at method update, id = " + role.getId(), e);
        }
        logger.info(this.getClass().getName() + " method update finished, id = " + role.getId());
    }

    @Override
    public Role getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Role role = null;
        try {
            role = roleDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return role;
    }

    @Override
    public Role getByName(String roleName) {
        logger.debug("Search for the requested role has begun" + roleName);
        Role result = null;
        try {
            result = roleDao.getByName(roleName);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.debug("Search " + (result == null ? "failed" : "succeeded"));
        return result;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            roleDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void add(Role role) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            roleDao.add(role);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<Role> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");

        List<Role> roleList = new ArrayList<>();

        try {
            roleList = roleDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }

        logger.info(this.getClass().getName() + " method getAll finished");

        return roleList;
    }
}
