package commands;

import database.DBProxy;

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
    }

    public boolean validInput(String input) {
        String[] tokens = input.split(" ");
        return tokens[0].equals("fetch");
    }

    public List<String> execute(String input) {
        return null;
    }
}
