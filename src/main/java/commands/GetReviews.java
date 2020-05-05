package commands;

import database.DBProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetReviews implements Command {

    private DBProxy proxy;

    public GetReviews() {
        try {
            this.proxy = new DBProxy();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }

        List<List<String>> reviews = this.getReviewsByApproved(1);
        for (int i = 0; i < reviews.size(); i++) {
            System.out.println("Review " + i + ": ");
            List<String> review = reviews.get(i);
            for (int j = 0; j < 8; j++) {
                System.out.println(review.get(j));
            }
        }
    }

    public boolean validInput(String input) {
        String[] tokens = input.split(" ");
        return tokens[0].equals("fetch");
    }

    public List<String> execute(String input) {
        return null;
    }

    public List<List<String>> getReviewsByApproved(int approved) {
        String stat = "SELECT * FROM reviews WHERE approved = ?;";
        PreparedStatement prep;
        try {
            // Prepare statement
            prep = this.proxy.getConnection().prepareStatement(stat);
            // Pass in the desired string
            prep.setInt(1, approved);

            ResultSet rs = prep.executeQuery();

            List<List<String>> reviews = new ArrayList<List<String>>();
            while (rs.next()) {
                List<String> review = new ArrayList<String>();
                String review_id = Integer.toString(rs.getInt(1));
                review.add(review_id);
                String user_id = Integer.toString(rs.getInt(2));
                review.add(user_id);
                String landlord_id = Integer.toString(rs.getInt(3));
                review.add(landlord_id);
                String property_id = Integer.toString(rs.getInt(4));
                review.add(property_id);
                String rating = Integer.toString(rs.getInt(5));
                review.add(rating);
                String text = rs.getString(6);
                review.add(text);
                String anon = Integer.toString(rs.getInt(7));
                review.add(anon);
                String date = rs.getString(8);
                review.add(date);
                reviews.add(review);
            }
            // Close the connections and return the result
            rs.close();
            prep.close();
            return reviews;
        } catch (SQLException e) {
            System.out.println("ERROR: Failed to insert new user into database. " + e.getMessage());
            return null;
        }
    }
}
