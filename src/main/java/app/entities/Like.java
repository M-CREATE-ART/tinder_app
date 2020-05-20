package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Like {
    private final int id;
    private final int likerId;
    private final int likedId;
    private final String action;



    @Override
    public String toString() {
        return String.format("Like[id=%d, likerId=%d, likedId=%d, action='%s']", id, likerId, likedId, action);
    }
}
