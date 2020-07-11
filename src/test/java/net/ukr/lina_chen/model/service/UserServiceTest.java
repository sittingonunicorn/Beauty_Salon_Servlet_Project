package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.exceptions.InvalidDataException;
import net.ukr.lina_chen.exceptions.UserExistsException;
import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.entity.User;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserService service = new UserService();
    private HttpServletRequest request = mock(HttpServletRequest.class);

    @Test
    public void getUserByEmailAndPasswordNotFound() {
        Optional<UserDTO> user = service.getUserByEmailAndPassword("test", "", Locale.ENGLISH);
        assertEquals(user, Optional.empty());
    }

    @Test
    public void getUserByEmailAndPasswordFound() {
        Optional<UserDTO> user = service.getUserByEmailAndPassword("anna@gmail.com", "1", Locale.ENGLISH);
        assertTrue(user.isPresent());
        assertEquals("Anna", user.get().getName());
    }

    @Test
    public void userExists() {
        boolean exists = service.userExists("anna@gmail.com", Locale.ENGLISH);
        assertTrue(exists);
    }

    @Test
    public void userExistsNot() {
        boolean exists = service.userExists("test@test.com", Locale.ENGLISH);
        assertFalse(exists);
    }


    @Test(expected = UserExistsException.class)
    public void saveNewUser() throws UserExistsException, SQLException {
        User user = new User();
        user.setEmail("anna@gmail.com");
        service.saveNewUser(user, Locale.ENGLISH);
        fail();
    }

    @Test (expected = InvalidDataException.class)
    public void extractUserFromRequest() throws InvalidDataException {
        when(request.getParameter("email")).thenReturn("123456@3");
        when(request.getParameter("name")).thenReturn("тест");
        when(request.getParameter("nameUkr")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("123");
        when(request.getParameter("confirmPassword")).thenReturn("1234");
        service.extractUserFromRequest(request);
        fail();
    }

    @Test
    public void getUserById() {
        Optional<User> user = service.getUserById(1L, Locale.ENGLISH);
        assert(user.isPresent());
        assertEquals("anna@gmail.com", user.get().getEmail());
    }
}
