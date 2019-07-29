package com.fariga.servlets.management.user;

import com.google.gson.Gson;
import com.fariga.entity.User;
import com.fariga.service.UserService;
import lombok.extern.log4j.Log4j;

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
        userService = (UserService) config.getServletContext().getAttribute("paramUserService");
    }

    //getting information about users
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Этот рабочий метод для возвращения листа в postman! не удалять
  /*     List<User> users = userService.getListOfUsers();
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonFromJavaArrayList = gsonBuilder.toJson(users);
        log.info("Getting list of users");
        resp.getWriter().println(jsonFromJavaArrayList);

*/

        log.info("!!!! DO GET METHOD !!!!");
        List<User> users = userService.getListOfUsers();
        String jsonFromJavaArrayList = new Gson().toJson(users);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(jsonFromJavaArrayList);
        out.flush();

    }
}
