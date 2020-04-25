package commands;

import java.util.List;

/**
 * The Interpreter interface has methods to be implemented
 * by classes that will take inputs from the REPL and interpret
 * them for a specific command.
 *
 * @author wtauten
 */
public interface Command {

    /**
     * A method that will determine whether a command
     * inputted into the REPL/GUI is valid for a particular command.
     * @param input a line of input into the REPL/GUI
     * @return a boolean representing whether or not the input was valid
     */
    boolean validInput(String input);

    /**
     * Method that takes a line of input and calls the command-specific
     * methods needed to execute it.
     * @param input a line of input into the REPL/GUI
     * @return a list of strings representing the "reply", to output to
     * either the terminal or GUI
     */
    List<String> execute(String input);

}
