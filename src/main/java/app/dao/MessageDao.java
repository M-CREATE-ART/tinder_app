package app.dao;

import app.entities.Message;
import app.entities.User;
import app.tools.ConnectionTool;

import java.sql.SQLException;
import java.util.List;

public class MessageDao {
    ConnectionTool connectionTool = new ConnectionTool();


    public List<Message> getMessages(User sideA, User sideB) throws SQLException {
     return  connectionTool.getMessages(sideA, sideB);

    }

    public void saveMessages(User sideA, User sideB, String text) throws SQLException {
        connectionTool.saveMessages(sideA, sideB, text);
    }
}
