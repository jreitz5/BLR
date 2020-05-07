package main;


import commands.*;

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
    private static final String CLIENT_ID = "1054214273797-fgvbormba22a5hl3fs8ini4p6b3odl27.apps.googleusercontent.com";

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

        // Register REPL commands here.
//        repl.registerCommand(new RegisterUser());
//        repl.registerCommand(new GetUser());

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
      Spark.get("/home", new FrontHandler(), freeMarker);
      Spark.post("/home/reviews", new ReviewsHandler());
      Spark.get("/about", new AboutHandler(), freeMarker);
      Spark.get("/feedback", new FeedbackHandler(), freeMarker);
      Spark.get("/landlord", new LandlordHandler(), freeMarker);
      Spark.get("/login", new LoginHandler(), freeMarker);
      Spark.get("/privacy", new PrivacyHandler(), freeMarker);
      Spark.get("/profile", new ProfileHandler(), freeMarker);
      Spark.get("/submit_review", new SubmitReviewHandler(), freeMarker);
      Spark.post("/submit_review/data", new DataHandler());
      Spark.post("/login/check", new LoginCheckHandler());
      Spark.get("/create", new CreateHandler(), freeMarker);
      Spark.post("/create/register", new RegisterHandler());
    }
    
    private static class ReviewsHandler implements Route {
      @Override
      public String handle(Request req, Response res) {

        // For testing
        GetReviews test = new GetReviews();
        List<List<String>> reviews = test.getReviewsAsList();
        
        Map<String, Object> variables = ImmutableMap.of("reviews",
            reviews);
        return GSON.toJson(variables);
      }
    }

    private class DataHandler implements Route {
        @Override
        public String handle(Request req, Response res) {
            GetLandlords getter = new GetLandlords();
            GetProperties propers = new GetProperties();
            List<String> names = getter.getNames();
            List<List<String>> properties = propers.getNames();

            Map<String, Object> variables =
                    ImmutableMap.of("landlords", names, "properties", properties);

            return GSON.toJson(variables);
        }
    }
    
    private static class FrontHandler implements TemplateViewRoute {
      @Override
      public ModelAndView handle(Request req, Response res) {
        
        Map<String, Object> variables = ImmutableMap.of("title",
            "Brown Landlord Review", "style", "home.css", "client_id", CLIENT_ID);
        return new ModelAndView(variables, "home.ftl");
      }
    }
    
    private static class AboutHandler implements TemplateViewRoute {
      @Override
      public ModelAndView handle(Request req, Response res) {
        Map<String, Object> variables = ImmutableMap.of("title",
            "Brown Landlord Review", "style", "about.css", "client_id", CLIENT_ID);
        return new ModelAndView(variables, "about.ftl");
      }
    }
    
    
    private static class FeedbackHandler implements TemplateViewRoute {
      @Override
      public ModelAndView handle(Request req, Response res) {
        Map<String, Object> variables = ImmutableMap.of("title",
            "Brown Landlord Review", "style", "feedback.css", "client_id", CLIENT_ID);

        return new ModelAndView(variables, "feedback.ftl");
      }
    }
    
    
    private static class LandlordHandler implements TemplateViewRoute {
      @Override
      public ModelAndView handle(Request req, Response res) {
        Map<String, Object> variables = ImmutableMap.of("title",
            "Brown Landlord Review", "style", "landlord.css", "client_id", CLIENT_ID);
        return new ModelAndView(variables, "landlord.ftl");
      }
    }
    
    
    private static class LoginHandler implements TemplateViewRoute {
      @Override
      public ModelAndView handle(Request req, Response res) {
        Map<String, Object> variables = ImmutableMap.of("title",
            "Brown Landlord Review", "style", "login.css", "client_id", CLIENT_ID);
        return new ModelAndView(variables, "login.ftl");
      }
    }

    private static class CreateHandler implements TemplateViewRoute {
        @Override
        public ModelAndView handle(Request req, Response res) {
            Map<String, Object> variables = ImmutableMap.of("title",
                    "Brown Landlord Review", "style", "create.css", "client_id", CLIENT_ID);
            return new ModelAndView(variables, "create.ftl");
        }
    }

    private static class LoginCheckHandler implements Route {
        @Override
        public String handle(Request req, Response res) {
            QueryParamsMap qm = req.queryMap();

            String email = qm.value("email");

            GetUser getter = new GetUser();

            List<String> info = getter.fromEmail(email);

            Map<String, Object> variables;

            if (info == null) {
                variables = ImmutableMap.of("success", "no");
            } else {
                variables = ImmutableMap.of("success", "yes");
            }

            return GSON.toJson(variables);
        }
    }

    private static class RegisterHandler implements Route {
        @Override
        public String handle(Request req, Response res) {
            QueryParamsMap qm = req.queryMap();

            String email = qm.value("email");
            String f_name = qm.value("first");
            String l_name = qm.value("last");

            // First check if the user is already in the database
            GetUser getter = new GetUser();

            List<String> info = getter.fromEmail(email);

            System.out.println("info " + info);
            System.out.println("l_name " + l_name);

            Map<String, Object> variables;

            if (info == null) {
                System.out.println("1");
                variables = ImmutableMap.of("success", "yes");
                RegisterUser register = new RegisterUser();
                register.register(l_name, f_name, email, 0, "no_oauth_yet", "images/landlordicon.png");
            } else {
                System.out.println("2");
                variables = ImmutableMap.of("success", "no");
            }
            System.out.println("3");
            return GSON.toJson(variables);
        }
    }
    
    
    private static class PrivacyHandler implements TemplateViewRoute {
      @Override
      public ModelAndView handle(Request req, Response res) {
        Map<String, Object> variables = ImmutableMap.of("title",
            "Brown Landlord Review", "style", "privacy.css", "client_id", CLIENT_ID);
        return new ModelAndView(variables, "privacy.ftl");
      }
    }

    private static class ProfileHandler implements TemplateViewRoute {
      @Override
      public ModelAndView handle(Request req, Response res) {
        Map<String, Object> variables = ImmutableMap.of("title",
            "Brown Landlord Review", "style", "profile.css", "client_id", CLIENT_ID);
        return new ModelAndView(variables, "profile.ftl");
      }
    }
    
    private static class SubmitReviewHandler implements TemplateViewRoute {
      @Override
      public ModelAndView handle(Request req, Response res) {
        Map<String, Object> variables = ImmutableMap.of("title",
            "Brown Landlord Review", "style", "submit_review.css", "client_id", CLIENT_ID);
        return new ModelAndView(variables, "submit_review.ftl");
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
