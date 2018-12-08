package qc.cs355.application.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;


/**
 * This is the controller that should get requests that should respond with JSON.
 * Think of this controller as a RESTful service controller.
 */
@RestController
public class DatabaseController 
{

    @RequestMapping("/search")
    @CrossOrigin(origins = "http://localhost:8081")
    public String search(@RequestParam("query") String query) 
    {

        // List<String> results = Database.search(query);
        System.out.printf("Query: %s\n", query);
        List<String> results = new ArrayList<>();
        results.add("result #1");
        results.add("result #2");
        results.add("result #3");

        
        return new Gson().toJson(results);
    }

}