package net.ukr.lina_chen.controller.filters;

import net.ukr.lina_chen.model.entity.Role;
import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class AuthFilterTest {

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final HttpSession session = mock(HttpSession.class);
    private final FilterChain chain = mock(FilterChain.class);

    @Test
    public void unauthorizedAccess() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn(null);
        when(request.getRequestURL()).thenReturn(new StringBuffer("/app/admin"));
        AuthFilter filter = new AuthFilter();
        filter.doFilter(request, response, chain);
        verify(response, times(1)).sendRedirect("/app/login");
    }

    @Test
    public void userUnauthorizedAccess() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn(Role.USER);
        when(request.getRequestURL()).thenReturn(new StringBuffer("/app/admin"));
        AuthFilter filter = new AuthFilter();
        filter.doFilter(request, response, chain);
        verify(response, times(1)).sendRedirect("/app/error");
    }

    @Test
    public void correctAccess() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn(Role.USER);
        when(request.getRequestURL()).thenReturn(new StringBuffer("/app/user"));
        AuthFilter filter = new AuthFilter();
        filter.doFilter(request, response, chain);
        verify(chain, times(1)).doFilter(request, response);
    }
}
