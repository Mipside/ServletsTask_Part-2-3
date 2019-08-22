package com.fariga.filter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccessFilterTest {

    @InjectMocks
    private AccessFilter accessFilter;

    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private FilterConfig filterConfig;
    @Mock
    private HttpSession session;

    @Before
    public void initMethod() throws ServletException {
        accessFilter.init(filterConfig);
    }

    @Test
    public void doFilter_GettingUserRoleFromSession_ReturnPathOrGiveForwardAccess() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("UserRole")).thenReturn("Admin");
        when(request.getSession().getAttribute("UserRole")).thenReturn("User");
        when(request.getRequestURI()).thenReturn("/user/login/");
        when(request.getRequestDispatcher("/WEB-INF/user/login/login.jsp")).thenReturn(requestDispatcher);

        accessFilter.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void doFilter_GettingUserRoleFromSession_GiveForwardAccessIfItIsNotUserOrAdmin() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        when(request.getRequestURI()).thenReturn("/user/login/");
        when(request.getSession().getAttribute("UserRole")).thenReturn(anyString());
        when(request.getRequestDispatcher("/WEB-INF/user/login/login.jsp")).thenReturn(requestDispatcher);

        accessFilter.doFilter(request, response, filterChain);

        verify(request).getRequestDispatcher(anyString());
    }

    @After
    public void doFilter_destroy(){
        accessFilter.destroy();
    }
}