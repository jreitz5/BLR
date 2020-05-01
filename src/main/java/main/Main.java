package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import freemarker.template.Configuration;
import freemarker.template.Version;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

/**
 * The Main class of our project. This is where execution begins.
 *
 * @author wauten
 */
public final class Main {
  
    private static final int DEFAULT_PORT = 4567;
    private static final Gson GSON = new Gson();

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
        OptionParser parser = new OptionParser();
        parser.accepts("gui");
        parser.accepts("port").withRequiredArg().ofType(Integer.class)
            .defaultsTo(DEFAULT_PORT);
        OptionSet options = parser.parse(args);

        if (options.has("gui")) {
        runSparkServer((int) options.valueOf("port"));
        }
        REPL repl = new REPL();

        // Register REPL commands here. ie:
        //repl.registerCommand(new CreateProfile());

        repl.runREPL();
    }
    
    // Because getting the "23 is a magic number" error is annoying
    private static final int TWENTY_THREE = 23;

    private static FreeMarkerEngine createEngine() {
      Configuration config = new Configuration(new Version(2, 3, TWENTY_THREE));
      File templates = new File("src/main/resources/spark/template/freemarker");
      try {
        config.setDirectoryForTemplateLoading(templates);
      } catch (IOException ioe) {
        System.out.printf("ERROR: Unable use %s for template loading.%n",
            templates);
        System.exit(1);
      }
      return new FreeMarkerEngine(config);
    }

    private void runSparkServer(int port) {
      Spark.port(port);
      Spark.externalStaticFileLocation("src/main/resources/static");
      Spark.exception(Exception.class, new ExceptionPrinter());

      FreeMarkerEngine freeMarker = createEngine();

      Spark.get("/", new FrontHandler(), freeMarker);
      
    }
    
    private static class FrontHandler implements TemplateViewRoute {
      @Override
      public ModelAndView handle(Request req, Response res) {
        Map<String, Object> variables = ImmutableMap.of("title",
            ":D", "message",
            "Vanilla Reiss");
        return new ModelAndView(variables, "query.ftl");
      }
    }
    
    
    private static class ExceptionPrinter implements ExceptionHandler<Exception> {
      @Override
      public void handle(Exception e, Request req, Response res) {
        res.status(500);
        StringWriter stacktrace = new StringWriter();
        try (PrintWriter pw = new PrintWriter(stacktrace)) {
          pw.println("<pre>");
          e.printStackTrace(pw);
          pw.println("</pre>");
        }
        res.body(stacktrace.toString());
      }
    }
}
