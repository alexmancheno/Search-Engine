package qc.cs355.application.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import qc.cs355.application.webcrawler.pWebCrawler;
import qc.cs355.application.database.Database;

/**
 * This is the controller that should get requests that should respond with
 * JSON. Think of this controller as a RESTful service controller.
 */
@RestController
public class DatabaseController {

    @RequestMapping("/search")
    @CrossOrigin(origins = "http://localhost:8081")
    public String search(@RequestParam("query") String query) {
        System.out.println("Query: " + query);
        List<String> results = Database.phatSearch(query);
        // System.out.printf("Query: %s\n", query);
        // List<String> results = new ArrayList<>();
        // results.add("result #1");
        // results.add("result #2");
        // results.add("result #3");

        return new Gson().toJson(results);
    }

    @RequestMapping("/getIndexedPages")
    @CrossOrigin(origins = "http://localhost:8081")
    public String getIndexedPages() {
        // List<String> results = Database.getIndexedPages(100);
        List<String> results = new ArrayList<>();
        results.add("a");
        results.add("b");
        results.add("c");
        results.add("a");
        results.add("b");
        results.add("c");
        results.add("a");
        results.add("b");
        results.add("c");
        results.add("a");
        results.add("b");
        results.add("c");
        return new Gson().toJson(results);
    }

    @RequestMapping("/getUserSearchQueries")
    @CrossOrigin(origins = "http://localhost:8081")
    public String getUserSearchQueries() {
        // Map<String, Integer> results = Database.getUserSearchQueries();
        Map<String, Integer> results = new HashMap<>();
        results.put("a", 2);
        results.put("b", 1);
        results.put("c", 5);
        results.put("a", 2);
        results.put("aa", 1);
        results.put("csd", 5);
        results.put("adsa", 2);
        results.put("dasdb", 1);
        results.put("cdsfs", 5);
        results.put("aasdfad", 2);
        results.put("adfasb", 1);
        results.put("afdsc", 5);
        return new Gson().toJsonTree(results).toString();
    }

    @RequestMapping("/getDatabaseInformation")
    @CrossOrigin(origins = "http://localhost:8081")
    public String getDatabaseInformation() {
        // Map<String, Object> results = Database.getDatabaseInformation();
        Map<String, Object> results = new HashMap<>();
        results.put("Space usage", 124231);
        results.put("Number of searches so far", 1);
        results.put("Most frequent search", "why am I the way I am?");
        return new Gson().toJsonTree(results).toString();
    }

    @RequestMapping("/startWC")
    @CrossOrigin(origins = "http://localhost:8081")
    public void startWC(@RequestParam("password") String password, @RequestParam("seed") String seed) {
        if (password.equals("dasfhuidhgisdfhgsndfgiu822347234723472347hsincsdbvbf")) {
            new Thread(new pWebCrawler(seed, 100)).start();
        }
    }
}