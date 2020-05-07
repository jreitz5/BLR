package commands;

import database.DBProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApproveReview implements Command {

    private DBProxy proxy;

    public ApproveReview() {
        try {
            this.proxy = new DBProxy("jdbc:sqlite:data/blr.sqlite3");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

    public ApproveReview(String url) {
        try {
            this.proxy = new DBProxy(url);
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

    public void approve(int review_id) {
        String stat = "UPDATE reviews SET approved = 1 WHERE review_id = ?;";
        PreparedStatement prep;
        try {
            // Prepare statement
            prep = this.proxy.getConnection().prepareStatement(stat);
            // Pass in the desired id
            prep.setInt(1, review_id);

            prep.executeUpdate();

            prep.close();
        } catch (SQLException e) {
            System.out.println("ERROR: Failed to approve review. " + e.getMessage());
        }
    }
}
