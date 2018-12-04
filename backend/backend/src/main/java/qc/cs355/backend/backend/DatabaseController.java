package qc.cs355.backend.backend;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * This is the controller that should get requests that should respond with JSON.
 * Think of this controller as a RESTful service controller.
 */
@RestController
public class DatabaseController 
{

    @RequestMapping("/")
    public String index() 
    {
        return "Greetings from Phat Search!!!!";
    }

}