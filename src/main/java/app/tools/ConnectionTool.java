package app.tools;

import app.entities.User;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ConnectionTool {
    private final static String URL = "jdbc:postgresql://localhost:5432/tinder";
    private final static String USER = "postgres";
    private final static String PASS = "secret";

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

    public List<User> getLikedUser(User me) throws SQLException {
        List<User> allUsers = getAllUsers();
        List<User> likedUsers = new ArrayList<>();

        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        String SQL = "SELECT * FROM likes WHERE liker_id = ? AND  action = ?";
        PreparedStatement preSt = conn.prepareStatement(SQL);
        preSt.setInt(1, me.getId());
        preSt.setString(2, "liked" );

        ResultSet resultSet = preSt.executeQuery();

        while (resultSet.next()){
            int likedId = resultSet.getInt("liked_id");

            User user = allUsers.stream().filter(l -> l.getId() == likedId).findFirst().get();
            likedUsers.add(user);

        }
        conn.close();
        return likedUsers;
    }

    public List<User> getDislikedUser(User me) throws SQLException {

        List <User> allusers = getAllUsers();
        List<User> disLikedUsers = new ArrayList<>();

        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        String SQL =  "SELECT * FROM likes WHERE liker_id = ? AND action = ?";
        PreparedStatement preSt = conn.prepareStatement(SQL);
        preSt.setInt(1, me.getId());
        preSt.setString(2, "disliked");
        ResultSet resultSet = preSt.executeQuery();

        while(resultSet.next()){
            int likedId = resultSet.getInt("liked_id");

            User user = allusers.stream().filter(d -> d.getId() == likedId).findFirst().get();
            disLikedUsers.add(user);
        }

        conn.close();
        return disLikedUsers;
    }

    public Optional<User> getUnVisitedUser(User me) throws SQLException {
        List<User> allUsers = getAllUsers();
        List<User> visitedUsers = new ArrayList<>();
        List<User> unvisitedUsers = new ArrayList<>();

        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        String SQL =  "SELECT * FROM likes WHERE liker_id =?";
        PreparedStatement preSt = conn.prepareStatement(SQL);
        preSt.setInt(1, me.getId());
        ResultSet resultSet = preSt.executeQuery();

        while(resultSet.next()){
            int likedId = resultSet.getInt("liked_id");

            User user = allUsers.stream().filter(d -> d.getId() == likedId).findFirst().get();
            visitedUsers.add(user);
        }
        conn.close();

        allUsers.removeAll(visitedUsers);
        allUsers.remove(me.getId());
        unvisitedUsers.addAll(allUsers);

        if (unvisitedUsers.size()==0) return Optional.empty();

        else{
            Random random = new Random();
            int r = random.nextInt(unvisitedUsers.size());
            return Optional.of(unvisitedUsers.get(r));
        }

    }

    public void addLike(String action, User me, User other) throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASS);


        if(action.equals("like")) {
            String SQL = "INSERT INTO likes (liker_id, liked_id, action) VALUES (?,?,'liked')";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, me.getId());
            ps.setInt(2, other.getId());
            ps.execute();
        } else {
            String SQL = "INSERT INTO likes (liker_id, liked_id, action) VALUES (?,?,'disliked')";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, me.getId());
            ps.setInt(2, other.getId());
            ps.execute();

        }

        con.close();
    }
}
