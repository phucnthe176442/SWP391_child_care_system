package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Submission;

/**
 *
 * @author Admin
 */
public class SubmissionDBContext extends BaseDBContext<Submission> {

    public ArrayList<Submission> listByTaskSlugAndUsername(String slug, String username) {
        ArrayList<Submission> submissions = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from submissions where slug = '" + slug + "' and username = '" + username + "'"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Submission submission = new Submission(
                        rs.getInt("id"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        rs.getString("username"),
                        rs.getString("taskname"),
                        rs.getString("status"),
                        rs.getString("slug"),
                        rs.getString("code")
                );
                submissions.add(submission);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return submissions;
    }

    public ArrayList<Submission> listByUsername(String username) {
        ArrayList<Submission> submissions = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from submissions where username = '" + username + "'"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Submission submission = new Submission(
                        rs.getInt("id"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        rs.getString("username"),
                        rs.getString("taskname"),
                        rs.getString("status"),
                        rs.getString("slug"),
                        rs.getString("code")
                );
                submissions.add(submission);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return submissions;
    }

    public void deleteByUsername(String username) {
        ArrayList<Submission> submissions = this.list();
        for (Submission submission : submissions) {
            if (submission.getUsername().equals(username)) {
                this.delete(submission.getId());
            }
        }
    }

    @Override
    public ArrayList<Submission> list() {
        ArrayList<Submission> submissions = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from submissions"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Submission submission = new Submission(
                        rs.getInt("id"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        rs.getString("username"),
                        rs.getString("taskname"),
                        rs.getString("status"),
                        rs.getString("slug"),
                        rs.getString("code")
                );
                submissions.add(submission);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return submissions;
    }

    @Override
    public Submission get(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from submissions where id = " + id
            );
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Submission submission = new Submission(
                        rs.getInt("id"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        rs.getString("username"),
                        rs.getString("taskname"),
                        rs.getString("status"),
                        rs.getString("slug"),
                        rs.getString("code")
                );
                return submission;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public Submission insert(Submission submission) {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO [submissions]\n"
                    + "           ([username]\n"
                    + "           ,[taskname]\n"
                    + "           ,[createdAt]\n"
                    + "           ,[updatedAt]\n"
                    + "           ,[status]\n"
                    + "           ,[slug]\n"
                    + "           ,[code])\n"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, submission.getUsername());
            statement.setString(2, submission.getTaskname());
            statement.setTimestamp(3, submission.getCreatedAt());
            statement.setTimestamp(4, submission.getUpdatedAt());
            statement.setString(5, submission.getStatus());
            statement.setString(6, submission.getSlug());
            statement.setString(7, submission.getCode());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                submission.setId(id);
                System.out.println("added submission: " + submission.getUsername());
                return submission;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }

    @Override
    public void update(Submission submission) {
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE [submissions]\n"
                    + " SET [status] = ?\n"
                    + "      ,[updatedAt] = ?\n"
                    + " WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, submission.getStatus());
            statement.setTimestamp(2, submission.getUpdatedAt());
            statement.setInt(3, submission.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM [submissions]\n"
                    + "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
