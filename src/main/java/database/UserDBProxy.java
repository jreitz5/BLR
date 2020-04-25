package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDBProxy {

    private Connection conn;

    public UserDBProxy() throws ClassNotFoundException, SQLException {
        // Set up a connection and store it in a field
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:data/blr_users.sqlite3";
        conn = DriverManager.getConnection(url);
        Statement stat = conn.createStatement();
        stat.executeUpdate("PRAGMA foreign_keys = ON;");
        stat.close();
    }

}
