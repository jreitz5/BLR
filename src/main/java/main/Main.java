package main;

/**
 * The Main class of our project. This is where execution begins.
 *
 * @author wauten
 */
public final class Main {

    private static final int DEFAULT_PORT = 4567;

    /**
     * The initial method called when execution begins.
     *
     * @param args An array of command line arguments
     */
    public static void main(String[] args) {
        new Main(args).run();
    }

    private String[] args;

    private Main(String[] args) {
        this.args = args;
    }

    private void run() {
        REPL repl = new REPL();

        // Register REPL commands here. ie:
        //repl.registerCommand(new CreateProfile());

        repl.runREPL();
    }
}
