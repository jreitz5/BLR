package main;

import commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class written to model a READ, EVALUATE, PRINT LOOP.
 *
 * @author wtauten
 */
public class REPL {

    private List<Command> commands;

    /**
     * Constructor for a REPL.
     */
    public REPL() {
        this.commands = new ArrayList<Command>();
    }

    /**
     * Method written to run the REPL. Uses an infinite loop
     * to wait for input from System.in, parse the tokens based
     * off the project indicated by the instance variable this.project,
     * and then output the result of the evaluation.
     */
    public void runREPL() {
        String buffer; // Instantiate a buffer to receive input
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            while ((buffer = reader.readLine()) != null) {
                // Check for exit or quit command
                if (buffer.equals("exit") || buffer.equals("quit")) {
                    break;
                }
                // Delegate parsing of tokens to project specific methods
                this.delegate(buffer);
                buffer = ""; // Reset buffer to empty string
            }
        } catch (IOException e) {
            System.err.println("ERROR: An IOException was caught: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("ERROR: An IOException was caught :"
                        + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Method written to register an command with this REPL. Must
     * implement the command interface.
     * @param command the command to register with the REPL.
     */
    public void registerCommand(Command command) {
        this.commands.add(command);
    }

    /**
     * Method written to delegate the input passed in to its respective
     * Project Interpreter. Loops through the interpreters and if the
     * input (passed in as just the first token of what was inputted into
     * the REPL) is a valid input for one of the projects, it processes
     * it's command and returns a List containing the reply. If no
     * interpreter accepts the keyword, it prints an error message and
     * returns null.
     *
     * @param input the first token of whatever string was passed into the
     *              REPL.
     * @return A list containing the reply from the interpreter, or null if
     *            no interpreters accepted the argument
     */
    public List<String> delegate(String input) {
        for (Command command : this.commands) {
            if (command.validInput(input)) {
                return command.execute(input);
            }
        }
        this.invalidInputPrinter(input);
        return null;
    }

    /**
     * Method written to spit out a message containing a list of
     * valid commands that may be entered.
     *
     * @param line a String representing the offending line of input
     *             into the terminal
     */
    public void invalidInputPrinter(String line) {
        System.err.println("ERROR: Input pattern not recognized.");
    }
}
