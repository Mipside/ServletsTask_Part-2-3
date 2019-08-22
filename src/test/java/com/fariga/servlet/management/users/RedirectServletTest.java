package com.fariga.servlet.management.users;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RedirectServletTest {

    @InjectMocks
    private RedirectServlet redirectServlet;

    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Test
    public void doGet_GettingUser_ResponseError() throws ServletException, IOException {
        when(request.getRequestDispatcher("/WEB-INF/management/users/crudUsers1.jsp")).thenReturn(requestDispatcher);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("UserRole")).thenReturn("User");

        redirectServlet.doGet(request, response);

        verify(response).sendError(404, "you are not allowed");
    }

    @Test
    public void doGet_GettingAdmin_SendForward() throws ServletException, IOException {
        when(request.getRequestDispatcher("/WEB-INF/management/users/crudUsers1.jsp")).thenReturn(requestDispatcher);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("UserRole")).thenReturn("Admin");

        redirectServlet.doGet(request, response);

        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/management/users/crudUsers1.jsp");
        verify(requestDispatcher).forward(request, response);
    }
}