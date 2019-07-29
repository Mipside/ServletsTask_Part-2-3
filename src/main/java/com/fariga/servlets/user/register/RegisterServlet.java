package com.fariga.servlets.user.register;

import com.fariga.entity.User;
import com.fariga.data.Storage;
import com.fariga.service.UserService;
import lombok.extern.log4j.Log4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j
@WebServlet("/user/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService;
    private Storage storage;

    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute("paramUserService");
        storage = (Storage) config.getServletContext().getAttribute("paramStorage");
        log.info("RegisterServlet: init method starts");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/user/register/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        if (!firstName.isEmpty() && !username.isEmpty() && !password.isEmpty() && !lastName.isEmpty()) {
            boolean check = userService.checkingUsernameInList(username);
            if (check) {
                resp.sendError(404, "Username: '" + username + "' - already exists!!!");
            } else {
                User u = new User();
                u.setFirstName(firstName);
                u.setLastName(lastName);
                u.setUsername(username);
                u.setPassword(password);
                u.setRole("User");
                u.setStatus("not_logged-in");
                List<User> anotherlist = storage.createUser(u);
                resp.sendRedirect("/user/login/");
            }
        } else log.info("RegisterServlet: some parameter is missing");
    }
}
