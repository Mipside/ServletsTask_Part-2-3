package com.fariga.servlet.management.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserServletRedirectTest {

    @InjectMocks
    private CreateUserServletRedirect createUserServletRedirect;

    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Test
    public void doGet_IfThisMethodIsCalled_RedirectToPath() throws ServletException, IOException {
        when(request.getRequestDispatcher("/WEB-INF/management/createUser/createUser.jsp")).thenReturn(requestDispatcher);

        createUserServletRedirect.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }
}