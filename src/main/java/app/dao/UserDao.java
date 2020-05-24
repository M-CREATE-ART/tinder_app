package app.dao;

import app.entities.User;
import app.tools.ConnectionTool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserDao {
    ConnectionTool connectionTool = new ConnectionTool();

    public UserDao() throws SQLException {
    }

    public List<User> getAllUsers() throws SQLException {
        return connectionTool.getAllUsers();
    }

    public boolean checkUser(String email, String password) throws SQLException {
        return getAllUsers().stream().anyMatch(u -> u.getEmail().equals(email) && u.getPassword().equals(password));
    }

    public User getMeFromCookie(HttpServletRequest req) throws SQLException {
        Cookie[] cookies = req.getCookies();

        String email = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("login"))
                .findFirst()
                .get()
                .getValue();

        return getAllUsers().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .get();


    }

    public List<User> getLikedUsers(User me) throws SQLException {
       return connectionTool.getLikedUser(me);


    }
    public User getUserById(int id) throws SQLException {
        return connectionTool.getUserById(id);
    }


    public List<User> getDislikedUsers(User me) throws SQLException {
        return connectionTool.getDislikedUser(me);
    }

    public Optional<User> getUnVisitedUser(User me) throws SQLException {
        return connectionTool.getUnVisitedUser(me);
    }


    public boolean checkDuplicate(String email) throws SQLException {
        return getAllUsers().stream().anyMatch(u -> u.getEmail().equals(email));
    }

    public void saveUserInfo(String fullname, String email, String password, String image ) throws SQLException {
        connectionTool.saveUserInfo(fullname, email, password, image);
    }

    public void updateLastLogin(User user) throws SQLException {
        connectionTool.updateLastLogin(user);
    }
}
