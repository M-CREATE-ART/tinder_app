package app.tools;

import app.entities.Message;
import app.entities.User;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ConnectionTool {
    private final static String URL = "jdbc:postgresql://ec2-34-195-169-25.compute-1.amazonaws.com:5432/d4q9lnfavio6u8";
    private final static String USER = "akhhelevfvkzgi";
    private final static String PASS = "05e6dc82487282701fa2f66fb3b534f666002827417d326ceb4509019c9cdb1a";

    public List<User> getAllUsers() throws SQLException {
        List<User> usersDB = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        String SQL = "SELECT * FROM users";
        PreparedStatement preSt = conn.prepareStatement(SQL);

        ResultSet resultSet = preSt.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String fullname = resultSet.getString("fullname");
            String lastLogin = resultSet.getString("last_login");
            String image = resultSet.getString("image");

            usersDB.add(new User(id, email, password, fullname, lastLogin, image));
        }
        conn.close();
        return usersDB;
    }

    public User getUserById(int id) throws SQLException {
        return getAllUsers().stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .get();
    }

    public List<User> getLikedUser(User me) throws SQLException {
        List<User> likedUsers = new ArrayList<>();

        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        String SQL = "SELECT * FROM likes WHERE liker_id = ? AND  action = ?";
        PreparedStatement preSt = conn.prepareStatement(SQL);
        preSt.setInt(1, me.getId());
        preSt.setString(2, "liked");

        ResultSet resultSet = preSt.executeQuery();

        while (resultSet.next()) {
            int likedId = resultSet.getInt("liked_id");

            likedUsers.add(getUserById(likedId));

        }
        conn.close();
        return likedUsers;
    }

    public List<User> getDislikedUser(User me) throws SQLException {

        List<User> disLikedUsers = new ArrayList<>();

        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        String SQL = "SELECT * FROM likes WHERE liker_id = ? AND action = ?";
        PreparedStatement preSt = conn.prepareStatement(SQL);
        preSt.setInt(1, me.getId());
        preSt.setString(2, "disliked");
        ResultSet resultSet = preSt.executeQuery();

        while (resultSet.next()) {
            int likedId = resultSet.getInt("liked_id");

            disLikedUsers.add(getUserById(likedId));
        }
        conn.close();
        return disLikedUsers;
    }

    public Optional<User> getUnVisitedUser(User me) throws SQLException {
        List<User> allUsers = getAllUsers();
        List<User> visitedUsers = new ArrayList<>();
        List<User> unvisitedUsers = new ArrayList<>();

        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        String SQL = "SELECT * FROM likes WHERE liker_id =?";
        PreparedStatement preSt = conn.prepareStatement(SQL);
        preSt.setInt(1, me.getId());
        ResultSet resultSet = preSt.executeQuery();

        while (resultSet.next()) {
            int likedId = resultSet.getInt("liked_id");

            User user = allUsers.stream()
                    .filter(d -> d.getId() == likedId)
                    .findFirst()
                    .get();

            visitedUsers.add(user);
        }

        User me1 = allUsers.stream().filter(u -> u.getId() == me.getId()).findFirst().get();
        allUsers.remove(me1);
        allUsers.removeAll(visitedUsers);
        unvisitedUsers = allUsers;

        if (unvisitedUsers.size() == 0) return Optional.empty();

        else {
            Random random = new Random();
            int r = random.nextInt(unvisitedUsers.size());
            return Optional.of(unvisitedUsers.get(r));
        }
    }

    public void addLike(String action, User me, User other) throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        String SQL = "INSERT INTO likes (liker_id, liked_id, action) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(SQL);
        ps.setInt(1, me.getId());
        ps.setInt(2, other.getId());
        ps.setString(3, action);
        ps.execute();
        con.close();

    }

    public List<Message> getMessages(User sideA, User sideB) throws SQLException {
        List<Message> messages = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        String SQL = "SELECT * FROM messages WHERE sender_id = ? AND receiver_id = ? ";
        PreparedStatement preSt = conn.prepareStatement(SQL);
        preSt.setInt(1, sideA.getId());
        preSt.setInt(2, sideB.getId());

        ResultSet resultSet = preSt.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int senderId = resultSet.getInt("sender_id");
            int receiverId = resultSet.getInt("receiver_id");
            String text = resultSet.getString("text");
            String date = resultSet.getString("message_date");

            messages.add(new Message(id, senderId, receiverId, text, date));
        }
        conn.close();
        return messages;
    }

    public void saveMessages(User sideA, User sideB, String text) throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedNow = now.format(format);

        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        String SQL = "INSERT INTO messages (sender_id, receiver_id, text, message_date) values(?,?,?,?)";
        PreparedStatement preSt = conn.prepareStatement(SQL);
        preSt.setInt(1, sideA.getId());
        preSt.setInt(2, sideB.getId());
        preSt.setString(3, text);
        preSt.setString(4, formattedNow);

        preSt.execute();

        conn.close();
    }

    public void saveUserInfo(String fullname, String email, String password, String image) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        String SQL = "INSERT INTO users(fullname, email, password, image, last_login) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preSt = conn.prepareStatement(SQL);
        preSt.setString(1, fullname);
        preSt.setString(2, email);
        preSt.setString(3, password);
        preSt.setString(4, image);
        preSt.setString(5, "no login");

        preSt.execute();

        conn.close();
    }

    @SneakyThrows
    public void updateLastLogin(User user) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASS);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedNow = now.format(format);
        String SQL = "UPDATE users SET last_login = ? WHERE id=?";
        PreparedStatement stmtUser =
                conn.prepareStatement(SQL);

        stmtUser.setString(1, formattedNow);
        stmtUser.setInt(2, user.getId());
        stmtUser.execute();
        conn.close();
    }


}
