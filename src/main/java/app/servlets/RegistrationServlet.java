package app.servlets;

import app.dao.UserDao;
import app.entities.User;
import app.tools.CookieFilter;
import app.tools.TemplateEngine;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class RegistrationServlet extends HttpServlet {

    private final TemplateEngine engine = TemplateEngine.folder("content");
    UserDao userDao = new UserDao();

    public RegistrationServlet() throws IOException, SQLException {
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieFilter cf = new CookieFilter();
        if (!cf.isLogged(req)) {
            HashMap<String, Object> data = new HashMap<>();
            engine.render("register.ftl", data, resp);
        } else {
            resp.sendRedirect("/users");

        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confPass = req.getParameter("confPass");
        String image = req.getParameter("image");
        String register = req.getParameter("register");


        if (userDao.checkDuplicate(email)) {
            engine.render("register.ftl", data, resp);
        } else if (!password.equals(confPass)) {
            engine.render("register.ftl", data, resp);
        } else {

            String imageName = uploadImage(req, email);
            userDao.saveUserInfo(fullname, email, password, imageName);
            resp.sendRedirect("/login");
        }

    }

    String uploadImage(HttpServletRequest req, String email) throws IOException, ServletException {
        StringBuilder fileNameBuilder = new StringBuilder();

        Part p = req.getPart("image");
        InputStream partIS = p.getInputStream();
        String fileSubName = p.getSubmittedFileName();
        String filename = String.format("%s%s", email, fileSubName);
        Files.copy(partIS, Paths.get("content/image/" + filename),
                StandardCopyOption.REPLACE_EXISTING);
        fileNameBuilder.append("image/").append(filename);

        return fileNameBuilder.toString();
    }

}
