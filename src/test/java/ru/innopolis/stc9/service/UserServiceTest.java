package ru.innopolis.stc9.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.innopolis.stc9.db.dao.users.UsersDao;
import ru.innopolis.stc9.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private static User user;
    BCryptPasswordEncoder bcryptEncoder;
    UsersDao mockUserDao;
    private UserService userService;

    @Before
    public void init() throws SQLException {
        mockUserDao = mock(UsersDao.class);
        userService = new UserService(mockUserDao);
        user = new User(1, "user", "user", 1);
        when(mockUserDao.getById(1)).thenReturn(user);
        when(mockUserDao.getById(0)).thenReturn(null);
        bcryptEncoder = mock(BCryptPasswordEncoder.class);

    }


    @Test
    public void getUserTest() {

        User result = userService.getById(1);
        assertEquals(result.getId(), 1);
        assertEquals(result.getLogin(), "user");
        assertEquals(result.getPassword(), "user");
        assertEquals(result.getPersonId(), 1);
    }

    @Test
    public void getAllUsers() {
        User user = new User(3, "3", "3", 3);
        User user2 = new User(4, "4", "4", 4);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        when(userService.getAll()).thenReturn(users);
        assertTrue(users.get(0).getLogin().equals(user.getLogin()));
        assertTrue(users.get(1).getLogin().equals(user2.getLogin()));

    }

    @Test
    public void addTest() {
        User res = new User();
        res.setId(2);
        res.setLogin("lol");
        res.setPassword("kek");
        res.setPersonId(2);
        assertEquals(res.getLogin(), "lol");
        assertEquals(res.getPassword(), "kek");
        assertEquals(res.getId(), 2);
        assertEquals(res.getPersonId(), 2);

    }

    @Test
    public void deleteTest() {
        User deleteUser = new User(0, null, null, 0);

        assertNotEquals(user.getId(), deleteUser.getId());
        assertNotEquals(user.getLogin(), deleteUser.getLogin());
        assertNotEquals(user.getPassword(), deleteUser.getPassword());
        assertNotEquals(user.getPersonId(), deleteUser.getPersonId());

    }

    @Test
    public void addUserRegistration() throws SQLException {
        User userNew = new User();
        String login = user.getLogin();
        String password = user.getPassword();
        String role = "admin";
        String bcrypt = bcryptEncoder.encode(password);

        userNew.setLogin(login);
        userNew.setPassword(bcrypt);
        userNew.setRole(role);

        assertNotNull(userNew);
    }


}
