package models;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class Submission extends BaseModel {

    private String username;
    private String taskname;
    private String status;
    private String slug;
    private String code;

    public Submission() {
    }

    public Submission(
            int id,
            Timestamp createdAt,
            Timestamp updatedAt,
            String username,
            String taskname,
            String status,
            String slug,
            String code
    ) {
        super(id, createdAt, updatedAt);
        this.username = username;
        this.taskname = taskname;
        this.status = status;
        this.slug = slug;
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public String getTaskname() {
        return taskname;
    }

    public String getStatus() {
        return status;
    }

    public String getSlug() {
        return slug;
    }

    public String getCode() {
        return code;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
