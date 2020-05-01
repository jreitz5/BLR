package commands;

import database.DBProxy;

import java.util.List;

public class SubmitReview implements Command {

    private DBProxy proxy;

    public SubmitReview() {
        try {
            this.proxy = new DBProxy();
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
}
