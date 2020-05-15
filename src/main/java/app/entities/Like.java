package app.entities;

public class Like {
    private final int id;
    private final int likerId;
    private final int likedId;
    private final String action;


    public Like(int id, int likerId, int likedId, String action) {
        this.id = id;
        this.likerId = likerId;
        this.likedId = likedId;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public int getLikerId() {
        return likerId;
    }

    public int getLikedId() {
        return likedId;
    }

    public String getAction(){return action;}

    @Override
    public String toString() {
        return String.format("Like[id=%d, likerId=%d, likedId=%d, action='%s']", id, likerId, likedId, action);
    }
}
