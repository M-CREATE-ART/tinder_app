package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Message {
    private final int id;
    private final int senderId;
    private final int receiver;
    private final String text;
    private final String date;



    @Override
    public String toString() {
        return String.format("Message[id=%d, senderId=%d, receiverId=%d, text='%s', date='%s']", id, senderId, receiver, text, date);
    }
}
