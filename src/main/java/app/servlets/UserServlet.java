package app.servlets;

import app.dao.LikeDao;
import app.dao.UserDao;
import app.entities.User;
import app.tools.TemplateEngine;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserServlet extends HttpServlet {
    private final TemplateEngine engine;

    UserDao userDao = new UserDao();
    LikeDao likeDao = new LikeDao();

    public UserServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;

    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        User currentUser = userDao.getMeFromCookie(req);
        userDao.updateLastLogin(currentUser);
        printUnlikedUser(resp, data, currentUser);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();

        String button = req.getParameter("btn");
        User me = userDao.getMeFromCookie(req);

        Cookie[] cookies = req.getCookies();

        String email = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("like"))
                .findFirst()
                .get()
                .getValue();
        User other = userDao.getAllUsers()
                .stream().filter(u -> u.getEmail().equals(email))
                .findFirst()
                .get();

        if (button != null) {
            likeDao.addLike(button, me, other);
            userDao.updateLastLogin(me);
            Optional<User> unVisitedUser = userDao.getUnVisitedUser(me);

            if (unVisitedUser.equals(Optional.empty())) {
                resp.sendRedirect("/liked");
            } else {
                Cookie cookie = new Cookie("like", unVisitedUser.get().getEmail());
                cookie.setMaxAge(60 * 60);
                resp.addCookie(cookie);
                data.put("user", unVisitedUser.get());
                userDao.updateLastLogin(me);
                engine.render("like-page.ftl", data, resp);
            }
        }



    }

    private void printUnlikedUser(HttpServletResponse resp, HashMap<String, Object> data, User currentUser) throws SQLException, IOException {
        Optional<User> unLikedUser = userDao.getUnVisitedUser(currentUser);

        if (unLikedUser.equals(Optional.empty())) {
            resp.sendRedirect("/liked");
        } else {
            Cookie cookie = new Cookie("like", unLikedUser.get().getEmail());
            resp.addCookie(cookie);
            data.put("user", unLikedUser.get());
            engine.render("like-page.ftl", data, resp);
        }
    }
}

