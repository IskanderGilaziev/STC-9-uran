package ru.innopolis.stc9.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.innopolis.stc9.db.dao.subjects.SubjectDao;
import ru.innopolis.stc9.db.dao.subjects.SubjectDaoImpl;
import ru.innopolis.stc9.pojo.Subject;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(PowerMockRunner.class)
@PrepareForTest(SubjectService.class)
public class SubjectserviceTest {

    private Subject subject;
    private SubjectDao mockSubjectDao;
    private SubjectService subjectService;
    private List<Subject> list;

    @Before
    public void init() throws SQLException, IllegalAccessException {
        subject = new Subject(1, "Java");
        mockSubjectDao = PowerMockito.mock(SubjectDaoImpl.class);
        subjectService = new SubjectService(mockSubjectDao);
        Field fields = PowerMockito.field(SubjectService.class, "subjectDao");
        fields.set(subjectService, mockSubjectDao);
        list = new ArrayList<>();
        list.add(subject);
    }

    @Test
    public void deleteSubjectTest() {
        PowerMockito.when(mockSubjectDao.deleteById(1)).thenReturn(true);
        assertTrue(subjectService.deleteById(1));
    }

    @Test
    public void addSubjectTest() {
        PowerMockito.when(mockSubjectDao.add(subject)).thenReturn(true);
        assertTrue(subjectService.add(subject));
    }

    @Test
    public void updateSubjectTest() {
        PowerMockito.when(mockSubjectDao.update(subject)).thenReturn(true);
        assertTrue(subjectService.update(subject));
    }

    @Test
    public void getByIdSubjectTest() {
        PowerMockito.when(mockSubjectDao.getById(1)).thenReturn(subject);
        assertEquals(subjectService.getById(1), subject);
    }

    @Test
    public void getAllSubjectTest() {
        PowerMockito.when(mockSubjectDao.getAll()).thenReturn(list);
        assertEquals(subjectService.getAll(), list);
    }
}

