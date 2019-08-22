package com.fariga.servlet.management.user;

import com.google.gson.Gson;
import com.fariga.entity.User;
import com.fariga.service.UserService;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.BasicConfigurator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Log4j
@WebServlet("/management/users")
public class UsersServlet extends HttpServlet {
    private UserService userService;

    public void init(ServletConfig config) {
        BasicConfigurator.configure(); //this line helps to initialize the log4j system properly
        userService = (UserService) config.getServletContext().getAttribute("paramUserService");
    }

    //getting information about users
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("UsersServlet: doGet method");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        List<User> users = userService.getListOfUsers();
        String jsonFromJavaArrayList = new Gson().toJson(users);
        out.print(jsonFromJavaArrayList);
        out.flush();
    }
}
