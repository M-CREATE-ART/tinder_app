package app.dao;

import app.entities.User;
import app.tools.ConnectionTool;

import java.sql.SQLException;

public class LikeDao {
    ConnectionTool connectionTool = new ConnectionTool();
    public void addLike(String action, User me, User other) throws SQLException {
        connectionTool.addLike(action, me, other);

    }
}
