package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    private final int id;
    private final String email;
    private final String password;
    private final String fullname;
    private final String lastLogin;
    private final String image;


    @Override
    public String toString() {
        return String.format("User[id=%d, email='%s', password='%s', fullname='%s', lastLogin='%s', image='%s']", id, email, password, fullname, lastLogin, image);
    }
}
