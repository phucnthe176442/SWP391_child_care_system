package models;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class User extends BaseModel {

    private String username;
    private String password;
    private String email;
    private int score;

    public User() {

    }

    public User(
            int id,
            Timestamp createdAt,
            Timestamp updatedAt,
            String username,
            String password,
            String email,
            int score
    ) {
        super(id, createdAt, updatedAt);
        this.username = username;
        this.password = password;
        this.email = email;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getScore() {
        return score;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
