package com.fariga.listener;

import com.fariga.entity.User;
import com.fariga.data.Storage;
import com.fariga.service.UserService;
import org.apache.log4j.BasicConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BasicConfigurator.configure(); //this line helps to initialize the log4j system properly
        Storage storage = new Storage();
        UserService userService = new UserService(storage);
        User user = new User();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("paramUserService", userService);
        servletContext.setAttribute("paramStorage", storage);
        servletContext.setAttribute("paramUser", user);
    }
}
