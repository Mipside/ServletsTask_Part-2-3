package com.fariga.filter;

import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j
@WebFilter(urlPatterns = {"/user/"})
public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        String userRole = (String) session.getAttribute("UserRole");
        if (uri.endsWith("/user/login/") && (userRole.equals("Admin"))) {
            filterChain.doFilter(request, response);
        } else if (uri.endsWith("/user/login/") && (userRole.equals("null"))) {
            request.getRequestDispatcher("/WEB-INF/user/login/login.jsp").forward(request, response);
        } else {
            // pass the request along the filter chain
            filterChain.doFilter(request, response);
            log.info("Access filter: session is NOT Null because there is a user");
        }
    }

    @Override
    public void destroy() {
    }
}
