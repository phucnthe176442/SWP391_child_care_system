package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Task;

/**
 *
 * @author Admin
 */
public class TaskDBContext extends BaseDBContext<Task> {

    @Override
    public ArrayList<Task> list() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from tasks"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        rs.getString("taskname"),
                        rs.getString("taskDescription"),
                        rs.getString("timeLimit"),
                        rs.getString("memoryLimit"),
                        rs.getString("slug"),
                        rs.getInt("score")
                );
                tasks.add(task);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tasks;
    }
    
    public Task getBySlug(String slug) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from tasks where slug = '" + slug + "'"
            );
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        rs.getString("taskname"),
                        rs.getString("taskDescription"),
                        rs.getString("timeLimit"),
                        rs.getString("memoryLimit"),
                        rs.getString("slug"),
                        rs.getInt("score")
                );
                return task;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public Task get(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from tasks where id = " + id
            );
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        rs.getString("taskname"),
                        rs.getString("taskDescription"),
                        rs.getString("timeLimit"),
                        rs.getString("memoryLimit"),
                        rs.getString("slug"),
                        rs.getInt("score")
                );
                return task;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public Task insert(Task task) {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO [tasks]\n"
                    + "           ([taskname]\n"
                    + "           ,[taskDescription]\n"
                    + "           ,[createdAt]\n"
                    + "           ,[updatedAt]\n"
                    + "           ,[timeLimit]\n"
                    + "           ,[memoryLimit]\n"
                    + "           ,[slug]\n"
                    + "           ,[score])\n"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getTaskname());
            statement.setString(2, task.getTaskDescription());
            statement.setTimestamp(3, task.getCreatedAt());
            statement.setTimestamp(4, task.getUpdatedAt());
            statement.setString(5, task.getTimeLimit());
            statement.setString(6, task.getMemoryLimit());
            statement.setString(7, task.getSlug());
            statement.setInt(8, task.getScore());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                task.setId(id);
                System.out.println("added task: " + task.getSlug());
                return task;
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
    public void update(Task task) {
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE [tasks]\n"
                    + " SET [taskname] = ?\n"
                    + "      ,[taskDescription] = ?\n"
                    + "      ,[timeLimit] = ?\n"
                    + "      ,[memoryLimit = ?\n"
                    + "      ,[score] = ?\n"
                    + "      ,[updatedAt] = ?\n"
                    + " WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, task.getTaskname());
            statement.setString(2, task.getTaskDescription());
            statement.setString(3, task.getTimeLimit());
            statement.setString(4, task.getMemoryLimit());
            statement.setInt(5, task.getScore());
            statement.setTimestamp(6, task.getUpdatedAt());
            statement.setInt(7, task.getId());
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
        // delete all task's testcase
        TestcaseDBContext testcaseDBContext = new TestcaseDBContext();
        Task task = this.get(id);
        if (task != null) {
            testcaseDBContext.deleteBySlug(task.getSlug());
        }

        // delete task
        try {
            String sql = "DELETE FROM [tasks]\n"
                    + "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
