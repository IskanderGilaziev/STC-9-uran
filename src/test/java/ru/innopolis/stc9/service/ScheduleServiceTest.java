package ru.innopolis.stc9.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.innopolis.stc9.db.dao.schedule.ScheduleDaoImpl;
import ru.innopolis.stc9.pojo.Group;
import ru.innopolis.stc9.pojo.ScheduleItem;
import ru.innopolis.stc9.pojo.Status;
import ru.innopolis.stc9.pojo.TeacherSubject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ScheduleService.class)
public class ScheduleServiceTest {

    private ScheduleService scheduleService;
    private ScheduleDaoImpl mockDaoSchedule;
    private ScheduleItem schedule;
    private Group group;
    private TeacherSubject teacherSubject;
    private List<ScheduleItem> list;
    private String LINK_SEE = "SEE";
    private String LINK_EDIT = "Edit";
    private String LINK_CREATE = "Create";


    @Before
    public void init() {

        teacherSubject = new TeacherSubject();
        schedule = new ScheduleItem(1, 2, group, teacherSubject, 255);
        mockDaoSchedule = PowerMockito.mock(ScheduleDaoImpl.class);
        scheduleService = new ScheduleService(mockDaoSchedule);
        list = new ArrayList<>();
        list.add(schedule);
    }


    @Test
    public void addScheduleTest() throws SQLException {
        PowerMockito.when(mockDaoSchedule.add(schedule)).thenReturn(true);
        assertTrue(scheduleService.add(schedule));
    }

    @Test
    public void deleteScheduleTest() throws SQLException {
        PowerMockito.when(mockDaoSchedule.deleteById(1)).thenReturn(true);
        assertTrue(scheduleService.deleteById(1));
    }

    @Test
    public void updateScheduleTest() throws SQLException {
        PowerMockito.when(mockDaoSchedule.update(schedule)).thenReturn(true);
        assertTrue(scheduleService.updateById(schedule));
    }

    @Test
    public void getByIdScheduleTest() throws SQLException {
        PowerMockito.when(mockDaoSchedule.getById(1)).thenReturn(schedule);
        assertEquals(scheduleService.getById(1), schedule);
    }

    @Test
    public void getAllScheduleTest() throws SQLException {
        PowerMockito.when(mockDaoSchedule.getAll()).thenReturn(list);
        assertEquals(scheduleService.getAll(), list);
    }

    @Test
    public void collectLinksOnPageTest() {
        Status status = Status.IN_PLAN;
        Map<String, String> mapStatus = new LinkedHashMap<>();
        mapStatus.put("/see", LINK_SEE);
        mapStatus.put("/edit", LINK_EDIT);
        mapStatus.put("/create", LINK_CREATE);
//        PowerMockito.when(scheduleService.collectLinksOnPage(status)).thenReturn(mapStatus);
        assertNotEquals(scheduleService.collectLinksOnPage(status), mapStatus);
    }

    @Test
    public void getMainScheduleTest() throws SQLException {
//        Program program=null;
//        Group group1 = new Group(1,1,program);
//        List<Group> groups = new ArrayList<>();
//        groups.add(group1);
//        schedule = new ScheduleItem(2,2,group,teacherSubject,42);
//        List<ScheduleItem> itemList = new ArrayList<>();
//        itemList.add(schedule);
//        Schedule schedule = new Schedule();
//      PowerMockito.when(mockDaoSchedule.getByGroupId(1)).thenReturn(itemList);
//      assertEquals(scheduleService.getMainSchedule(groups),schedule);
    }

}
