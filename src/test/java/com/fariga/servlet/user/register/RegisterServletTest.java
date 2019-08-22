package com.fariga.servlet.user.register;

import com.fariga.data.Storage;
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
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterServletTest {

    @InjectMocks
    private RegisterServlet registerServlet;

    @Mock
    private UserService userService;
    @Mock
    private Storage storage;
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

    //Before annotation makes some errors in the doPost method, that is why @Test for init it is the best option
    @Test
    public void initServletConfig() {
        when(servletConfig.getServletContext()).thenReturn(servletContext);

        registerServlet.init(servletConfig);

        verify(servletConfig, atLeastOnce()).getServletContext();
    }

    @Test
    public void doGet_WhenMethodCalled_SendForwardByPath() throws ServletException, IOException {
        when(request.getRequestDispatcher("/WEB-INF/user/register/register.jsp")).thenReturn(requestDispatcher);

        registerServlet.doGet(request, response);

        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/user/register/register.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doPost_GettingRequestParameters_DoesNotSettingNewUser_ReturnErrorOrRedirect() throws ServletException, IOException {
        when(request.getParameter(anyString())).thenReturn("firstUsername");
        when(userService.checkingUsernameInList("firstUsername")).thenReturn(true);

     //   assertTrue(userService.checkingUsernameInList("firstUsername"));
        registerServlet.doPost(request, response);

        verify(response).sendError(404, "Username: 'firstUsername' - already exists!!!");
    }

    @Test
    public void doPost_GettingSimilarRequestParameters_SettingNewUser_ReturnRedirect() throws ServletException, IOException {
        when(request.getParameter(anyString())).thenReturn("firstUsername");
        when(userService.checkingUsernameInList("firstUsername")).thenReturn(false);

     //   assertFalse(userService.checkingUsernameInList("firstUsername"));
        registerServlet.doPost(request, response);

        verify(response).sendRedirect("/user/login/");
    }
}
