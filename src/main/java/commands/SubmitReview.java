package commands;

import database.DBProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubmitReview implements Command {

    private DBProxy proxy;

    public SubmitReview() {
        try {
            this.proxy = new DBProxy("jdbc:sqlite:data/blr.sqlite3");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

    public SubmitReview(String url) {
        try {
            this.proxy = new DBProxy(url);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

    public boolean validInput(String input) {
        String[] tokens = input.split(" ");
        return tokens[0].equals("review");
    }

    public List<String> execute(String input) {
        return null;
    }

    public void addReviewToDB(int user_id, int landlord_id, Integer property_id, int rating,
                              String text, int anon, String date, int approved) {
        String stat = "INSERT INTO reviews (user_id, landlord_id, property_id, review_rating, text_review, anonymous," +
                "date, approved) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement prep;
        try {
            // Prepare statement
            prep = this.proxy.getConnection().prepareStatement(stat);

            prep.setInt(1, user_id);
            prep.setInt(2, landlord_id);
            prep.setInt(3, landlord_id);
            prep.setInt(4, rating);
            prep.setString(5, text);
            prep.setInt(6, anon);
            prep.setString(7, date);
            prep.setInt(8, approved);

            System.out.println("made it here!!!");

            prep.executeUpdate();

            prep.close();
        } catch (SQLException e) {
            System.out.println("ERROR: Failed to insert new review into database. " + e.getMessage());
        }
    }
}
