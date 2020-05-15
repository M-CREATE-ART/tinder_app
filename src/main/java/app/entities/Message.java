package app.entities;

public class Message {
    private final int id;
    private final int senderId;
    private final int recieverId;
    private final String text;
    private final String date;

    public Message(int id, int senderId, int recieverId, String text, String date) {
        this.id = id;
        this.senderId = senderId;
        this.recieverId = recieverId;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getRecieverId() {
        return recieverId;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("Message[id=%d, senderId=%d, recieverId=%d, text='%s', date='%s']", id, senderId, recieverId, text, date);
    }
}
