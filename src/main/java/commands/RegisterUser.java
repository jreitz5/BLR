package commands;

import database.DBProxy;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RegisterUser implements Command {

    private DBProxy proxy;

    public RegisterUser() {
        try {
            this.proxy = new DBProxy("jdbc:sqlite:data/blr.sqlite3");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

    public RegisterUser(String url) {
        try {
            this.proxy = new DBProxy(url);
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
        if (tokens.length <= 4) {
            System.err.println("Error: not enough inputs to register new user");
            return null;
        }
        String last_name = tokens[1];
        String first_name = tokens[2];
        int admin = Integer.parseInt(tokens[3]);
        String email = tokens[4];
        String oauth_key = "";
        if (tokens.length >= 6) {
            oauth_key = tokens[5];
        }
        String image_path = "";
        if (tokens.length >= 7) {
            image_path = tokens[6];
        }
        return this.register(last_name, first_name, email, admin, oauth_key, image_path);
    }

    public List<String> register(String l_name, String f_name, String email, int admin, String oauth, String img) {
        String stat = "INSERT INTO users (last_name, first_name, email, admin, oauth_key, image_path) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement prep;
        try {
            // Prepare statement
            prep = this.proxy.getConnection().prepareStatement(stat);
            // Pass in the desired string
            prep.setString(1, l_name);
            prep.setString(2, f_name);
            prep.setString(3, email);
            prep.setInt(4, admin);
            prep.setString(5, oauth);
            prep.setString(6, img);

            prep.executeUpdate();

            prep.close();
        } catch (SQLException e) {
            System.out.println("ERROR: Failed to insert new user into database. " + e.getMessage());
            return null;
        }
        return null;
    }
}
