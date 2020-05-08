package commands;

import database.DBProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetLandlords implements Command {

    private DBProxy proxy;

    public GetLandlords() {
        try {
            this.proxy = new DBProxy("jdbc:sqlite:data/blr.sqlite3");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

    public GetLandlords(String url) {
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

    public Map<String, Integer> getMap() {
        String stat = "SELECT landlord_id, first_name, last_name FROM landlord";
        PreparedStatement prep;
        try {
            // Prepare statement
            prep = this.proxy.getConnection().prepareStatement(stat);

            ResultSet rs = prep.executeQuery();

            Map<String, Integer> nameToId = new HashMap<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2) + " " + rs.getString(3);
                nameToId.put(name, id);
            }
            // Close the connections and return the result
            rs.close();
            prep.close();
            return nameToId;
        } catch (SQLException e) {
            System.out.println("ERROR: Failed to insert new user into database. " + e.getMessage());
            return null;
        }

    }
}
