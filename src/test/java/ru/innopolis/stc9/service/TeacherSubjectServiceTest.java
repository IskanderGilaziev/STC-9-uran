package ru.innopolis.stc9.service;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import ru.innopolis.stc9.db.dao.teacherSubject.TeacherSubjectDao;
import ru.innopolis.stc9.pojo.TeacherSubject;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TeacherSubjectServiceTest {

    private static TeacherSubject teacherSubject;
    TeacherSubjectDao tsdaoMock;
    private TeacherSubjectService tsservice;
    private List<TeacherSubject> listTS;

    @Before
    public void init() throws SQLException, IllegalAccessException {
        tsdaoMock = PowerMockito.mock(TeacherSubjectDao.class);
        tsservice = new TeacherSubjectService(tsdaoMock);
        teacherSubject = new TeacherSubject(1, 1, "Fred", 2, "java");
        Field fields = PowerMockito.field(TeacherSubjectService.class, "teacherDao");
        fields.set(tsservice, tsdaoMock);
        listTS = new ArrayList<>();
        listTS.add(teacherSubject);
    }


    @Test
    public void addTeacherSub() throws SQLException {
        PowerMockito.when(tsdaoMock.add(teacherSubject)).thenReturn(true);
        assertTrue(tsservice.add(teacherSubject));
    }

    @Test
    public void updateTeacherSub() throws SQLException {
        PowerMockito.when(tsdaoMock.update(teacherSubject)).thenReturn(true);
        assertTrue(tsservice.update(teacherSubject));
    }

    @Test
    public void deleteTeacherSubjectById() throws SQLException {
        PowerMockito.when(tsdaoMock.deleteById(1)).thenReturn(true);
        assertTrue(tsservice.deleteById(1));
    }

    @Test
    public void getByIdTeacherSub() throws SQLException {
        PowerMockito.when(tsdaoMock.getById(1)).thenReturn(teacherSubject);
        assertEquals(tsservice.getById(1), teacherSubject);
    }

    @Test
    public void getAllTeacherSub() throws SQLException {
        PowerMockito.when(tsdaoMock.getAll()).thenReturn(listTS);
        assertEquals(tsservice.getAll(), listTS);

    }


}
