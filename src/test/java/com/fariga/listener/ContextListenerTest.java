package com.fariga.listener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContextListenerTest {

    @InjectMocks
    private ContextListener contextListener;

    @Mock
    private ServletContext servletContext;
    @Mock
    private ServletContextEvent servletContextEvent;

    @Test
    public void contextInitialized_CreatingInstances_SettingThemAsContextAttributes() {
        when(servletContextEvent.getServletContext()).thenReturn(servletContext);

        contextListener.contextInitialized(servletContextEvent);

        verify(servletContextEvent, atLeastOnce()).getServletContext();
    }
}