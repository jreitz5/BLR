package commands;

import database.DBProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetLandlords implements Command {

    private DBProxy proxy;

    public GetLandlords() {
        try {
            this.proxy = new DBProxy();
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

    public List<String> getNames() {
        String stat = "SELECT first_name, last_name FROM landlord";
        PreparedStatement prep;
        try {
            // Prepare statement
            prep = this.proxy.getConnection().prepareStatement(stat);

            ResultSet rs = prep.executeQuery();

            List<String> names = new ArrayList<String>();
            while (rs.next()) {
                String name = rs.getString(1) + " " + rs.getString(2);
                names.add(name);
            }
            // Close the connections and return the result
            rs.close();
            prep.close();
            return names;
        } catch (SQLException e) {
            System.out.println("ERROR: Failed to insert new user into database. " + e.getMessage());
            return null;
        }

    }
}
