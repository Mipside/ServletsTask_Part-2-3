package com.fariga.servlets.management.users;


import lombok.extern.log4j.Log4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j
@WebServlet("/management/usersred")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String userRole = (String) session.getAttribute("UserRole");
        if (!(userRole == null) && userRole.equals("Admin")) {
            req.getRequestDispatcher("/WEB-INF/management/users/crudUsers1.jsp").forward(req, resp);
        } else {
            log.info("Access filter: session is NOT Null because there is a user");
            resp.sendError(404, "you are not allowed");
        }

    }
}
