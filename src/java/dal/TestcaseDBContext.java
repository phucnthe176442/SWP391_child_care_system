package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Testcase;

/**
 *
 * @author Admin
 */
public class TestcaseDBContext extends BaseDBContext<Testcase> {

    public void deleteBySlug(String slug) {
        ArrayList<Testcase> testcases = this.list();
        for (Testcase testcase : testcases) {
            if (testcase.getSlug().equals(slug)) {
                this.delete(testcase.getId());
            }
        }
    }
    
    public ArrayList<Testcase> listByTaskSlug(String slug) {
        ArrayList<Testcase> testcases = new ArrayList<>();
        try {
            String sql = "select * from testcases where slug = '" + slug + "'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Testcase testcase = new Testcase(
                        rs.getInt("id"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        rs.getString("slug"),
                        rs.getString("input"),
                        rs.getString("output")
                );
                testcases.add(testcase);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return testcases;
    }

    @Override
    public ArrayList<Testcase> list() {
        ArrayList<Testcase> testcases = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from testcases"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Testcase testcase = new Testcase(
                        rs.getInt("id"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        rs.getString("slug"),
                        rs.getString("input"),
                        rs.getString("output")
                );
                testcases.add(testcase);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return testcases;
    }

    @Override
    public Testcase get(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from testcases where id = " + id
            );
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Testcase testcase = new Testcase(
                        rs.getInt("id"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        rs.getString("slug"),
                        rs.getString("input"),
                        rs.getString("output")
                );
                return testcase;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public Testcase insert(Testcase testcase) {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO [testcases]\n"
                    + "           ([createdAt]\n"
                    + "           ,[updatedAt]\n"
                    + "           ,[slug]\n"
                    + "           ,[input]\n"
                    + "           ,[output])\n"
                    + "VALUES(?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1, testcase.getCreatedAt());
            statement.setTimestamp(2, testcase.getUpdatedAt());
            statement.setString(3, testcase.getSlug());
            statement.setString(4, testcase.getInput());
            statement.setString(5, testcase.getOutput());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                testcase.setId(id);
                System.out.println("added testcase: " + testcase.getSlug());
                return testcase;
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
    public void update(Testcase testcase) {
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE [testcases]\n"
                    + " SET [input] = ?\n"
                    + "      ,[output] = ?\n"
                    + "      ,[updatedAt] = ?\n"
                    + " WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, testcase.getInput());
            statement.setString(2, testcase.getOutput());
            statement.setTimestamp(3, testcase.getUpdatedAt());
            statement.setInt(4, testcase.getId());
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
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM [testcases]\n"
                    + "WHERE id = ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
