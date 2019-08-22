package com.fariga.servlet.user.logout;

import com.fariga.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogoutServletTest {

    @InjectMocks
    private LogoutServlet logoutServlet;

    @Mock
    private UserService userService;
    @Mock
    private ServletContext servletContext;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;

    @Before
    public void initServletMethodTest() {
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(servletConfig.getServletContext().getAttribute("paramUserService")).thenReturn(userService);
        logoutServlet.init(servletConfig);
    }

    @Test
    public void doPost_GettingUsernameFromSession_CheckingLoginStatus_RedirectForward() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("UserName")).thenReturn("firstUsername");
        when(userService.logout("firstUsername")).thenReturn(true);

        logoutServlet.doPost(request, response);

        verify(response).sendRedirect("/index.jsp");
    }

    @Test
    public void doPost_GettingUsernameFromSession_CheckingLoginStatus_ReturnError() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("UserName")).thenReturn("firstUsername111");

        logoutServlet.doPost(request, response);

        verify(response).sendError(404, "LogoutServlet: logout error");
    }
}