package com.fariga.servlets.management.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@WebServlet("/management/user")
public class UserServlet extends HttpServlet {

    private Storage storage;
    private JsonObject jsonObject;
    private UserService userService;

    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute("paramUserService");
        storage = (Storage) config.getServletContext().getAttribute("paramStorage");
    }

    //getting user info by username
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        req.setAttribute("username", (username == null) ? "human" : username);
        User user = storage.getUserInfo(username);
        if (user.getUsername().equals("nonUser")) {
            log.info("UserServlet: doGet error");
            System.out.println("doGet error");
            resp.sendError(400, "user: '" + user.getUsername() + "' is null!!!");
        } else {
            Gson gson = new Gson();
            String stringForParsing = gson.toJson(user);
            jsonObject = new JsonParser().parse(stringForParsing).getAsJsonObject();
            log.info("UserServlet: getting info about {} ", username);
            resp.getWriter().println(jsonObject);
        }
    }


    //create user fields : firstName, username and password – must be filled
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = parser(req, resp);
        if (!(user.getUsername().equals("nonUser"))) {
            Boolean bolCheck = userService.checkingUsernameInList(user.getUsername());
            if (!bolCheck) {
                List<User> list;
                list = storage.createUser(user);
                Gson gsonBuilder = new GsonBuilder().create();
                String jsonFromJavaArrayList = gsonBuilder.toJson(list);
                resp.getWriter().println(jsonFromJavaArrayList);
            } else {
                resp.sendError(404, "Username: '" + user.getUsername() + "' - already exists!!!");
            }
        } else {
            log.info("UserServlet: doPost error");
            resp.sendError(404, "UserServlet: some fields are missing!!!");
        }
    }


    //you can update only firstName, lastName and username
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
      //old version for json
        user = parser(req, resp);
        if (!(user.getUsername().equals("nonUser"))) {
            Boolean boolCheck = userService.checkingUsernameInList(user.getUsername());
            if (!boolCheck) {
                List<User> list;
                list = storage.updateUserInfo(user.getFirstName(), user.getLastName(), user.getUsername());
                Gson gsonBuilder = new GsonBuilder().create();
                String jsonFromJavaArrayList = gsonBuilder.toJson(list);
                resp.getWriter().println(jsonFromJavaArrayList);
            } else {
                resp.sendError(404, "User with username: '" + user.getUsername() + "' - is not found!!!");
            }
        } else {
            System.out.println("doPut error");
        }
        */

        /*
        //working with json parameters
        log.info("doPut method");
        user = parser(req, resp);
        if (!(user.getUsername().equals("nonUser"))) {
            Boolean boolCheck = userService.checkingUsernameInList(user.getUsername());
            if (!boolCheck) {
                List<User> list;
                list = storage.updateUserInfo(user.getFirstName(), user.getLastName(), user.getUsername());
                log.info("user: " + user.getUsername() + "was updated");
            } else {
                resp.sendError(404, "Such user '" + user.getUsername() + "' - is not found!!!");
            }
        } else {
            log.info("doPut error");
        }
        */

        //working with url parameters
        String uname = req.getParameter("username");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        if (!uname.isEmpty()) {
            boolean check2 = userService.checkingUsernameInList(uname);
            if (check2) {
                List<User> list = storage.updateUserInfo(firstname, lastname, uname);
                log.info("UserServlet: user {} was updated", uname);
            } else {
                resp.sendError(404, "Such user is not found!!!");
            }
        } else {
            log.info("UserServlet: doPut error");
            resp.sendError(404, "some fields are missing!!!");
        }

    }


    // delete user by username
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      /*
      //old version for json
       User user = parser(req, resp);
        if (!(user.getUsername().equals("nonUser"))) {
            boolean check2 = userService.check(user.getUsername(), user.getPassword());
            if (check2) {
                List<User> list;
                list = storage.deleteUser(user.getUsername());
                Gson gsonBuilder = new GsonBuilder().create();
                String jsonFromJavaArrayList = gsonBuilder.toJson(list);
                resp.getWriter().println(jsonFromJavaArrayList);
            } else {
                resp.sendError(404, "Such user '" + user.getUsername() + "' - is not found!!!");
            }
        } else {
            System.out.println("doDelete error");
        }*/

        String uname = req.getParameter("username");
        if (!uname.isEmpty()) {
            boolean check2 = userService.checkingUsernameInList(uname);
            if (check2) {
                List<User> list = storage.deleteUser(uname);
                log.info("UserServlet: user {} - was deleted", uname);
            } else {
                resp.sendError(404, "Such user '" + uname + "' - is not found!!!");
            }
        } else {
            log.info("UserServlet: doDelete error");
            resp.sendError(404, "some fields are missing!!!");
        }
    }


    private User parser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream io = req.getInputStream();
        int read = io.read();
        while (read >= 0) {
            sb.append((char) read);
            read = io.read();
        }
        String str = sb.toString();
        jsonObject = new JsonParser().parse(str).getAsJsonObject();
        String fname = jsonObject.get("firstname").getAsString();
        String lname = jsonObject.get("lastname").getAsString();
        String uname = jsonObject.get("username").getAsString();
        String pass = jsonObject.get("password").getAsString();
        String stat = jsonObject.get("status").getAsString();

        if (!fname.isEmpty() && !uname.isEmpty() && !pass.isEmpty()) {
            User user = new User();
            user.setFirstName(fname);
            user.setLastName(lname);
            user.setUsername(uname);
            user.setPassword(pass);
            user.setStatus(stat);
            return user;
        } else {
            User nonUser = new User();
            nonUser.setUsername("nonUser");
            return nonUser;
        }
    }

}
