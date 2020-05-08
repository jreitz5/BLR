package commands;

import database.DBProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetProperties implements Command {

    private DBProxy proxy;

    public GetProperties() {
        try {
            this.proxy = new DBProxy("jdbc:sqlite:data/blr.sqlite3");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

    public GetProperties(String url) {
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

    public List<List<String>> getNames() {
        String stat = "SELECT landlord.first_name, landlord.last_name, properties.address FROM " +
                "landlord JOIN properties ON properties.landlord_id = landlord.landlord_id;";
        PreparedStatement prep;
        try {
            // Prepare statement
            prep = this.proxy.getConnection().prepareStatement(stat);

            ResultSet rs = prep.executeQuery();

            List<List<String>> props = new ArrayList<>();
            while (rs.next()) {
                List<String> single = new ArrayList<>();
                String name;
                String f_name = rs.getString(1);
                String l_name = rs.getString(2);
                if (f_name.equals("")) {
                    name = l_name;
                } else {
                    name = f_name + " " + l_name;
                }
                single.add(name);
                single.add(rs.getString(3));
                props.add(single);
            }
            // Close the connections and return the result
            rs.close();
            prep.close();
            return props;
        } catch (SQLException e) {
            System.out.println("ERROR: Failed to insert new user into database. " + e.getMessage());
            return null;
        }

    }

    public List<List<String>> getAll() {
        String stat = "SELECT landlord.first_name, landlord.last_name, properties.address, properties.property_id FROM " +
                "landlord JOIN properties ON properties.landlord_id = landlord.landlord_id;";
        PreparedStatement prep;
        try {
            // Prepare statement
            prep = this.proxy.getConnection().prepareStatement(stat);

            ResultSet rs = prep.executeQuery();

            List<List<String>> props = new ArrayList<>();
            while (rs.next()) {
                List<String> single = new ArrayList<>();
                String name;
                String f_name = rs.getString(1);
                String l_name = rs.getString(2);
                if (f_name.equals("")) {
                    name = l_name;
                } else {
                    name = f_name + " " + l_name;
                }
                single.add(name);
                single.add(rs.getString(3));
                single.add(Integer.toString(rs.getInt(4)));
                props.add(single);
            }
            // Close the connections and return the result
            rs.close();
            prep.close();
            return props;
        } catch (SQLException e) {
            System.out.println("ERROR: Failed to insert new user into database. " + e.getMessage());
            return null;
        }

    }
}
