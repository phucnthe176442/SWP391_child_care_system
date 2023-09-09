package dal;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author Admin
 */
public class UserDBContext extends BaseDBContext<User> {

    public ArrayList<User> listByRank() {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from users order by score DESC"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("score")
                );
                users.add(user);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    @Override
    public ArrayList<User> list() {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from users"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("score")
                );
                users.add(user);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public User getByUsername(String username) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from users where username = '" + username + "'"
            );
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("score")
                );
                return user;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public User get(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from users where id = " + id
            );
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("score")
                );
                return user;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public User insert(User user) {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO [users]\n"
                    + "           ([username]\n"
                    + "           ,[password]\n"
                    + "           ,[createdAt]\n"
                    + "           ,[updatedAt]\n"
                    + "           ,[email]\n"
                    + "           ,[score])\n"
                    + "VALUES(?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setTimestamp(3, user.getCreatedAt());
            statement.setTimestamp(4, user.getUpdatedAt());
            statement.setString(5, user.getEmail());
            statement.setInt(6, user.getScore());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                user.setId(id);
                System.out.println("added user: " + user.getUsername());
                return user;
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
    public void update(User user) {
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE [users]\n"
                    + " SET [email] = ?\n"
                    + "      ,[password] = ?\n"
                    + "      ,[updatedAt] = ?\n"
                    + "      ,[score] = ?\n"
                    + " WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setTimestamp(3, user.getUpdatedAt());
            statement.setInt(4, user.getScore());
            statement.setInt(5, user.getId());
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

    public void changePassword(int userId, String newPasssword) {
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE [users]\n"
                    + " SET [password] = ?\n"
                    + " WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, newPasssword);
            statement.setInt(2, userId);
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
        // delete all user's submissions
        SubmissionDBContext submissionDBContext = new SubmissionDBContext();
        User deleteUser = this.get(id);
        if (deleteUser != null) {
            submissionDBContext.deleteByUsername(deleteUser.getUsername());
        }

        //delete user
        try {
            String sql = "DELETE FROM [users]\n"
                    + "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
