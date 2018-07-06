package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.ProgramDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Program;
import ru.innopolis.stc9.service.hibernate.interfaces.ProgramService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ProgramServiceHibernate implements ProgramService {

    private static final Logger logger = Logger.getLogger(ProgramServiceHibernate.class);

    private final ProgramDao programDao;

    @Autowired
    public ProgramServiceHibernate(ProgramDao programDao) {
        this.programDao = programDao;
    }

    @Override
    public Program getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Program program = null;
        program = programDao.getById(id);
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return program;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        programDao.deleteByProgramId(id);
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void addOrUpdate(Program program) {
        logger.info(this.getClass().getName() + " method add started");
        programDao.addOrUpdateProgram(program);
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<Program> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Program> programList;
        programList = programDao.getAllPrograms();
        logger.info(this.getClass().getName() + " method getAll finished");
        return programList;
    }

    /**
     * selects a list of programs from the database whose user field is null and status is not unknown
     *
     * @return
     */

}
