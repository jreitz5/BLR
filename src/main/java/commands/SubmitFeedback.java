package commands;

import database.DBProxy;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SubmitFeedback implements Command{
    private DBProxy proxy;

    public SubmitFeedback() {
        try {
            this.proxy = new DBProxy("jdbc:sqlite:data/blr.sqlite3");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

    public SubmitFeedback(String url) {
        try {
            this.proxy = new DBProxy(url);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }


    @Override
    public boolean validInput(String input) {
        return false;
    }

    @Override
    public List<String> execute(String input) {
        return null;
    }

    public void submitFeedback(String new_land, String new_prop, String other) {
        String stat = "INSERT INTO feedback (new_landlord, new_property, other) VALUES (?, ?, ?);";
        PreparedStatement prep;
        try {
            // Prepare statement
            prep = this.proxy.getConnection().prepareStatement(stat);

            prep.setString(1, new_land);
            prep.setString(2, new_prop);
            prep.setString(3, other);

            prep.executeUpdate();

            prep.close();
        } catch (SQLException e) {
            System.out.println("ERROR: Failed to insert new review into database. " + e.getMessage());
        }
    }
}
