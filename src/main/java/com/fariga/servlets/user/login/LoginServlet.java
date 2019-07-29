package com.fariga.servlets.user.login;

import com.fariga.entity.User;
import com.fariga.data.Storage;
import com.fariga.service.UserService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet("/user/login/")
public class LoginServlet extends HttpServlet {

    private UserService userService;
    private Storage storage;

    public void init(ServletConfig config) {
        log.info("LoginServlet: init method starts");
        userService = (UserService) config.getServletContext().getAttribute("paramUserService");
        storage = (Storage) config.getServletContext().getAttribute("paramStorage");
    }

    //does forward to JSP login
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/user/login/login.jsp").forward(req, resp);
    }

    //change User status to logged-in and redirect to user page
    // and put into session info about user
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (!username.isEmpty() && !password.isEmpty()) {
            boolean checkingResult = userService.check(username, password);
            if (checkingResult) {
                boolean loginUpdateStatus = userService.updateLoginStatus(username);

                User localUser = storage.getUserInfo(username);
                req.setAttribute("uname", localUser.getUsername());
                req.setAttribute("firstname", localUser.getFirstName());
                req.setAttribute("lastname", localUser.getLastName());
                req.setAttribute("status", localUser.getStatus());

                HttpSession session = req.getSession();
                if (localUser.getRole().equals("User")) {
                    session.setAttribute("UserName", localUser.getUsername());
                    session.setAttribute("UserFirstName", localUser.getFirstName());
                    session.setAttribute("UserLastName", localUser.getLastName());
                    session.setAttribute("UserStatus", localUser.getStatus());
                    session.setAttribute("UserRole", "User");
                    log.info("LoginServlet : User {} was {} ", username, loginUpdateStatus);
                    req.getRequestDispatcher("/WEB-INF/user/home/uhome.jsp").forward(req, resp);
                } else {
                    session.setAttribute("UserRole", "Admin");
                    req.getRequestDispatcher("/WEB-INF/management/user/management.jsp").forward(req, resp);
                }
            } else {
                log.info("LoginServlet: doPost error");
                resp.sendError(404, "Such user '" + username + "' - is not found!!!");}
        }
    }
}
