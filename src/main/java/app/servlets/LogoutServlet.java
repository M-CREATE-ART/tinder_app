package app.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Arrays.stream(req.getCookies()).forEach(c-> {
            c.setMaxAge(0);
            resp.addCookie(c);
        });
        resp.sendRedirect("/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Arrays.stream(req.getCookies()).forEach(c-> {
            c.setMaxAge(0);
            resp.addCookie(c);
        });
        resp.sendRedirect("/login");

    }
}
