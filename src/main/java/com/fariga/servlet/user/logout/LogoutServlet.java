package com.fariga.servlet.user.logout;

import com.fariga.service.UserService;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.BasicConfigurator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j
@WebServlet("/user/logout")
public class LogoutServlet extends HttpServlet {

    private UserService userService;

    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute("paramUserService");
        BasicConfigurator.configure(); //this line helps to initialize the log4j system properly
        log.info("LogoutServlet: init method starts");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String uname = session.getAttribute("UserName").toString();
        boolean checkingLogoutstatus = userService.logout(uname);
        if (checkingLogoutstatus) {
            session.invalidate();
            resp.sendRedirect("/index.jsp");
        } else {
            log.info("LogoutServlet: user wasn't logout");
            resp.sendError(404, "LogoutServlet: logout error");
        }
    }
}
