package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.programs.ProgramsDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Program;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProgramService implements IProgramService {
    private static final Logger logger = Logger.getLogger(ProgramService.class);
    private static final Logger loggerError = Logger.getLogger(ProgramService.class);

    @Autowired
    private ProgramsDao programDao;

    @Override
    public void update(Program program) {
        logger.info(this.getClass().getName() + " method update started, id = " + program.getId());
        try {
            programDao.update(program);
        } catch (SQLException e) {
            loggerError.error("Error at method update, id = " + program.getId(), e);
        }
        logger.info(this.getClass().getName() + " method update finished, id = " + program.getId());
    }

    @Override
    public Program getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Program program = null;
        try {
            program = programDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return program;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            programDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void add(Program program) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            programDao.add(program);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<Program> getAll() {

        logger.info(this.getClass().getName() + " method getAll started");
        List<Program> programList = new ArrayList<>();

        try {
            programList = programDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return programList;
    }
}
