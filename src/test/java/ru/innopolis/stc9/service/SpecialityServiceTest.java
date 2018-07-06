package ru.innopolis.stc9.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.innopolis.stc9.db.dao.speciality.SpecialityDaoImpl;
import ru.innopolis.stc9.pojo.Speciality;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(PowerMockRunner.class)
@PrepareForTest(SpecialityService.class)
public class SpecialityServiceTest {

    List<Speciality> listSpec;
    private SpecialityService specialityService;
    private SpecialityDaoImpl mockDao;
    private Speciality speciality;

    @Before
    public void init() throws IllegalAccessException {
        speciality = new Speciality(1, "Java developer", 1);
        mockDao = PowerMockito.mock(SpecialityDaoImpl.class);
        specialityService = new SpecialityService(mockDao);
        Field fields = PowerMockito.field(SpecialityService.class, "specialityDao");
        fields.set(specialityService, mockDao);
        listSpec = new ArrayList<>();
        listSpec.add(speciality);
    }

    @Test
    public void addSpecialityTest() {
        PowerMockito.when(mockDao.add(speciality)).thenReturn(true);
        assertTrue(specialityService.add(speciality));
    }

    @Test
    public void deleteSpecialityTest() {
        PowerMockito.when(mockDao.deleteById(1)).thenReturn(true);
        assertTrue(specialityService.deleteById(1));
    }

    @Test
    public void updateSpecialityTest() {
        PowerMockito.when(mockDao.update(speciality)).thenReturn(true);
        assertTrue(specialityService.updateById(speciality));
    }

    @Test
    public void getByIdSpecialityTest() {
        PowerMockito.when(mockDao.getById(1)).thenReturn(speciality);
        assertEquals(specialityService.getById(1), speciality);
    }

    @Test
    public void getAllSpecialityTest() {
        PowerMockito.when(mockDao.getAll()).thenReturn(listSpec);
        assertEquals(specialityService.getAll(), listSpec);
    }


}
