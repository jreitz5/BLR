package commands;

import database.DBProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetReviews implements Command {

    private DBProxy proxy;
    private List<List<String>> reviews;

    public GetReviews() {
        try {
            this.proxy = new DBProxy("jdbc:sqlite:data/blr.sqlite3");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }

        List<List<String>> reviews = this.getReviewsByApproved(1);
        this.reviews = reviews;
        for (int i = 0; i < reviews.size(); i++) {
            System.out.println("Review " + i + ": ");
            List<String> review = reviews.get(i);
            for (int j = 0; j < review.size(); j++) {
                System.out.print(review.get(j) + ", ");
            }
            System.out.println("");
        }
    }

    public GetReviews(String url) {
        try {
            this.proxy = new DBProxy(url);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }

        List<List<String>> reviews = this.getReviewsByApproved(1);
        this.reviews = reviews;
//        for (int i = 0; i < reviews.size(); i++) {
//            System.out.println("Review " + i + ": ");
//            List<String> review = reviews.get(i);
//            for (int j = 0; j < review.size(); j++) {
//                System.out.print(review.get(j) + ", ");
//            }
//            System.out.println("");
//        }
    }
    
    public List<List<String>> getReviewsAsList() {
      return this.reviews;
    }

    public boolean validInput(String input) {
        String[] tokens = input.split(" ");
        return tokens[0].equals("fetch");
    }

    public List<String> execute(String input) {
        return null;
    }

    public List<List<String>> getReviewsByApproved(int approved) {
        String stat = "SELECT reviews.review_id, reviews.user_id, reviews.landlord_id, reviews.property_id, reviews.review_rating, reviews.text_review, reviews.anonymous, reviews.date, reviews.approved, landlord.first_name, landlord.last_name"
            + " FROM reviews"
            + " LEFT JOIN landlord ON reviews.landlord_id=landlord.landlord_id"
            + " WHERE approved = ?;";
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
                String landlord_first_name = rs.getString(10);
                review.add(landlord_first_name);
                String landlord_last_name = rs.getString(11);
                review.add(landlord_last_name);
                reviews.add(review);
            }
            // Close the connections and return the result
            rs.close();
            prep.close();
            return reviews;
        } catch (SQLException e) {
            System.out.println("ERROR: Failed to retrieve reviews from database. " + e.getMessage());
            return null;
        }
    }
}
