package com.fariga.servlet.user.login;

import com.fariga.data.Storage;
import com.fariga.entity.User;
import com.fariga.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest {

    @InjectMocks
    private LoginServlet loginServlet;

    @Mock
    private Storage storage;
    @Mock
    private UserService userService;
    @Mock
    private User user;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private ServletContext servletContext;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private HttpSession session;

    /* @Before annotation as an alternative for @Test annotation for init() method
       makes some errors in other methods, that is why @Test for init() it is the best option*/
    @Test
    public void initServletConfig() {
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        loginServlet.init(servletConfig);

        verify(servletConfig, atLeastOnce()).getServletContext();
    }

    @Test
    public void doGet_WhenMethodIsCalled_SendForwardByPath() throws ServletException, IOException {
        when(request.getRequestDispatcher("/WEB-INF/user/login/login.jsp")).thenReturn(requestDispatcher);

        loginServlet.doGet(request, response);

        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/user/login/login.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doPost_GettingValidUser() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("firstUsername");
        when(request.getParameter("password")).thenReturn("111");
        when(userService.check("firstUsername", "111")).thenReturn(true);
        when(storage.getUserInfo("firstUsername")).thenReturn(user);
        when(request.getSession()).thenReturn(session);
        when(user.getRole()).thenReturn("User");
        when(request.getRequestDispatcher("/WEB-INF/user/home/uhome.jsp")).thenReturn(requestDispatcher);

        loginServlet.doPost(request, response);

        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/user/home/uhome.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doPost_GettingValidAdmin() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("firstUsername2");
        when(request.getParameter("password")).thenReturn("1112");
        when(userService.check("firstUsername2", "1112")).thenReturn(true);
        when(storage.getUserInfo("firstUsername2")).thenReturn(user);
        when(request.getSession()).thenReturn(session);
        when(user.getRole()).thenReturn("Admin");
        when(request.getRequestDispatcher("/WEB-INF/management/user/management.jsp")).thenReturn(requestDispatcher);

        loginServlet.doPost(request, response);

        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/management/user/management.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doPost_GettingNonValidPerson() throws ServletException, IOException {
        when(request.getParameter(anyString())).thenReturn("first3");
        when(userService.check("first3", "1112")).thenReturn(false);

        loginServlet.doPost(request, response);

        verify(response).sendError(404, "Such user 'first3' - is not found!!!");
    }

}