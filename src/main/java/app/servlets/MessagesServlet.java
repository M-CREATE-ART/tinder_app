package app.servlets;

import app.dao.MessageDao;
import app.dao.UserDao;
import app.entities.Message;
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
import java.util.*;

public class MessagesServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    MessageDao messageDao = new MessageDao();

    private final TemplateEngine engine;

    public MessagesServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String , Object>  data = new HashMap<>();
        User me = userDao.getMeFromCookie(req);

        Optional<Cookie> email = Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals("message"))
                .findFirst();

        if (email.equals(Optional.empty())){
            resp.sendRedirect("/liked");
        }else {
            int oppositeId = Integer.parseInt(email.get().getValue());
            User other = userDao.getUserById(oppositeId);
            userDao.updateLastLogin(me);

            List<Message> sent = messageDao.getMessages(me, other);
            List<Message> received = messageDao.getMessages(other, me);
            List<Message> allMessages = new ArrayList<>();
            allMessages.addAll(sent);
            allMessages.addAll(received);

            Comparator<Message> compareById = Comparator.comparing(Message::getId);
            Collections.sort(allMessages, compareById);

            data.put("messages", allMessages);
            data.put("me", me);
            data.put("other", other);

            engine.render("chat.ftl", data, resp);

        }

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String, Object> data = new HashMap<>();
        String message = req.getParameter("message");
        String submit = req.getParameter("submit");
        User me = userDao.getMeFromCookie(req);

        Optional<Cookie> email = Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals("message"))
                .findFirst();

        int receiverId = Integer.parseInt(email.get().getValue());
        User other = userDao.getUserById(receiverId);

        if (submit!=null){
            messageDao.saveMessages(me, other,message);
            List<Message> sent = messageDao.getMessages(me, other);
            List<Message> received = messageDao.getMessages(other, me);
            List<Message> allMessages = new ArrayList<>();
            allMessages.addAll(sent);
            allMessages.addAll(received);

            Comparator<Message> comparator = Comparator.comparing(Message :: getId);
            Collections.sort(allMessages, comparator);
            data.put("messages", allMessages);
            data.put("me", me);
            data.put("other", other);
            userDao.updateLastLogin(me);
            engine.render("chat.ftl", data, resp);
        }



    }
}
