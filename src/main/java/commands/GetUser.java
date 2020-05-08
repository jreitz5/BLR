package commands;

import database.DBProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUser implements Command {

    private DBProxy proxy;

    public GetUser() {
        try {
            this.proxy = new DBProxy("jdbc:sqlite:data/blr.sqlite3");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

    public boolean validInput(String input) {
        return false;
    }

    public List<String> execute(String input) {
        return null;
    }

    public List<String> fromEmail(String email) {
        String stat = "SELECT first_name, last_name, admin, hash FROM users WHERE email = ?;";
        PreparedStatement prep;
        try {
            // Prepare statement
            prep = this.proxy.getConnection().prepareStatement(stat);

            prep.setString(1, email);

            ResultSet rs = prep.executeQuery();

            List<String> user = new ArrayList<String>();
            while (rs.next()) {
                String name = rs.getString(1) + " " + rs.getString(2);
                user.add(name);
                user.add(Integer.toString(rs.getInt(3)));
                user.add(rs.getString(4));
            }
            if (user.size() < 1) {
                return null;
            }
            // Close the connections and return the result
            rs.close();
            prep.close();
            return user;
        } catch (SQLException e) {
            System.out.println("ERROR: Failed to read user from database. " + e.getMessage());
            return null;
        }
    }
}
