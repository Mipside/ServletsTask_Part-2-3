package com.fariga.servlet.management.user;

import com.fariga.data.Storage;
import com.fariga.entity.User;
import com.fariga.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServletTest {

    @InjectMocks
    private UserServlet userServlet;
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
    private ServletContext servletContext;
    @Mock
    private ServletConfig servletConfig;

    /* @Before annotation as an alternative for @Test annotation for init() method
    makes some errors in other methods, that is why @Test for init() it is the best option*/
    @Test
    public void init() {
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        userServlet.init(servletConfig);
        verify(servletConfig, atLeastOnce()).getServletContext();
    }

    @Test
    public void doGet_GettingNonUser_ReturnError() throws IOException {
        when(request.getParameter(anyString())).thenReturn("nonUser");
        when(user.getUsername()).thenReturn("nonUser");
        when(storage.getUserInfo("nonUser")).thenReturn(user);

        userServlet.doGet(request, response);

        verify(response).sendError(400, "user: 'nonUser' is null!!!");
    }

    @Test(expected = NullPointerException.class)
    public void doPost_GettingUser_creatingNewUser_sendErrorOrReturnUserListInJSON() throws IOException {
        userServlet.doPost(request, response);
        //this could be some doPostTest with nice verify method
    }

    @Test
    public void doPut_getRequestParametersOfExistedUser_UpdateUser() throws IOException {
        when(request.getParameter(anyString())).thenReturn("User");
        when(userService.checkingUsernameInList("User")).thenReturn(true);

        userServlet.doPut(request, response);

        Assert.assertFalse(request.getParameter("username").isEmpty());

    }

    @Test
    public void doPut_getEmptyRequestParameters_sendError() throws IOException {
        when(request.getParameter(anyString())).thenReturn("");

        userServlet.doPut(request, response);

        verify(response).sendError(404, "some fields are missing!!!");
    }

    @Test
    public void doPut_getRequestParametersOfNonExistedUser_sendError() throws IOException {
        when(request.getParameter(anyString())).thenReturn("User2");
        when(userService.checkingUsernameInList("User2")).thenReturn(false);

        userServlet.doPut(request, response);

        verify(response).sendError(404, "Such user is not found!!!");
    }

    @Test
    public void doDelete_getExistedUsernameFromRequest_rDeleteUser() throws IOException {
        when(request.getParameter(anyString())).thenReturn("User");
        when(userService.checkingUsernameInList("User")).thenReturn(true);

        userServlet.doDelete(request, response);

        Assert.assertFalse(request.getParameter("username").isEmpty());
    }

    @Test
    public void doDelete_getEmptyRequestParameters_sendError() throws IOException {
        when(request.getParameter(anyString())).thenReturn("");

        userServlet.doDelete(request, response);

        verify(response, atLeastOnce()).sendError(404, "some fields are missing!!!");
    }

    @Test
    public void doDelete_getNonExistedUsernameFromRequest_sendError() throws IOException {
        when(request.getParameter(anyString())).thenReturn("User2");

        userServlet.doDelete(request, response);

        verify(response).sendError(404, "Such user 'User2' - is not found!!!");
    }

}