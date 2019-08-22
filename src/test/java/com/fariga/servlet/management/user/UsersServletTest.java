package com.fariga.servlet.management.user;

import com.fariga.data.Storage;
import com.fariga.entity.User;
import com.fariga.service.UserService;
import com.google.gson.Gson;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UsersServletTest {

    @InjectMocks
    private UsersServlet usersServlet;

    @Mock
    private Storage storage;
    @Mock
    private UserService userService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private ServletContext servletContext;

    /* @Before annotation as an alternative for @Test annotation for init() method
       makes some errors in other methods, that is why @Test for init() it is the best option*/
    @Test
    public void initInstances() {
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        usersServlet.init(servletConfig);
        verify(servletConfig, atLeastOnce()).getServletContext();
    }

    @Test
    public void doGet_WhenMethodIsCalled_OutPrintJsonListToAjaxMethod() throws ServletException, IOException {
        List<User> actualList = storage.listOfUsers();
        when(userService.getListOfUsers()).thenReturn(actualList);
        String jsonFromJavaArrayList = new Gson().toJson(actualList);
        PrintWriter printWriter = new PrintWriter(jsonFromJavaArrayList);
        when(response.getWriter()).thenReturn(printWriter);

        usersServlet.doGet(request, response);

        verify(response, atLeastOnce()).getWriter();
    }
}