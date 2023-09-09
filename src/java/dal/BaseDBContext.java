package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.BaseModel;

public abstract class BaseDBContext<T extends BaseModel> {

    public Connection connection;

    public BaseDBContext() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(DBConfig.URL,
                    DBConfig.USERNAME, DBConfig.PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BaseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract ArrayList<T> list();
    public abstract T get(int id);
    public abstract T insert(T model);
    public abstract void update(T model);
    public abstract void delete(int id);
}
