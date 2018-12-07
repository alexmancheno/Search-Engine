package qc.cs355.application.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * This is the controller that should get requests that should respond with JSON.
 * Think of this controller as a RESTful service controller.
 */
@RestController
public class DatabaseController 
{

    @RequestMapping("/search")
    public List<String> search(@RequestParam("query") String query) 
    {
        System.out.printf("Query: %s\n", query);
        List<String> results = new ArrayList<>();
        results.add("result #1");
        results.add("result #2");
        results.add("result #3");

        return results;
    }

}