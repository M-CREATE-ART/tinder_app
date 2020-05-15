package app.entities;

public class User {
    private final int id;
    private final String email;
    private final String password;
    private final String fullname;
    private final String lastLogin;
    private final String image;

    public User(int id, String email, String password, String fullname, String lastLogin, String image) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.lastLogin = lastLogin;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public String getImage(){return image;}

    @Override
    public String toString() {
        return String.format("User[id=%d, email='%s', password='%s', fullname='%s', lastLogin='%s', image='%s']", id, email, password, fullname, lastLogin, image);
    }
}
