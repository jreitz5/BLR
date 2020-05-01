package commands;

import database.DBProxy;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RegisterUser implements Command {

    private DBProxy proxy;

    public RegisterUser() {
        try {
            this.proxy = new DBProxy();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

    public boolean validInput(String input) {
        String[] tokens = input.split(" ");
        return tokens[0].equals("register");
    }

    public List<String> execute(String input) {
        String[] tokens = input.split(" ");
        String name = tokens[1];
        String email = tokens[2];
        int admin = Integer.parseInt(tokens[3]);
        String oauth_key = "";
        if (tokens.length >= 5) {
            oauth_key = tokens[4];
        }
        String image_path = "";
        if (tokens.length >= 6) {
            image_path = tokens[5];
        }
        return this.register(name, email, admin, oauth_key, image_path);
    }

    public List<String> register(String name, String email, int admin, String oauth, String img) {
        String stat = "INSERT INTO users (name, email, admin, oauth_key, image_path) " +
                "VALUES (?, ?, ?, ?, ?);";
        PreparedStatement prep;
        try {
            // Prepare statement
            prep = this.proxy.getConnection().prepareStatement(stat);
            // Pass in the desired string
            prep.setString(1, name);
            prep.setString(2, email);
            prep.setInt(3, admin);
            prep.setString(4, oauth);
            prep.setString(5, img);

            prep.executeUpdate();

            prep.close();
        } catch (SQLException e) {
            System.out.println("ERROR: Failed to insert new user into database. " + e.getMessage());
            return null;
        }
        return null;
    }
}
