
package models;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class Testcase extends BaseModel {

    private String slug;
    private String input;
    private String output;

    public Testcase() {
    }

    public Testcase(
            int id,
            Timestamp createdAt,
            Timestamp updatedAt,
            String slug,
            String input,
            String output
    ) {
        super(id, createdAt, updatedAt);
        this.slug = slug;
        this.input = input;
        this.output = output;
    }

    public String getSlug() {
        return slug;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setOutput(String output) {
        this.output = output;
    }

}
