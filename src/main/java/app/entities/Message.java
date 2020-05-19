package app.entities;

public class Message {
    private final int id;
    private final int senderId;
    private final int receiver;
    private final String text;
    private final String date;

    public Message(int id, int senderId, int receiverId, String text, String date) {
        this.id = id;
        this.senderId = senderId;
        this.receiver = receiverId;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiver() {
        return receiver;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("Message[id=%d, senderId=%d, receiverId=%d, text='%s', date='%s']", id, senderId, receiver, text, date);
    }
}
