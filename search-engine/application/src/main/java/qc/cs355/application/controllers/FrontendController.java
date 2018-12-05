package qc.cs355.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This is the controller that should get requests that should respond with
 * HTML pages to render. 
 */
@Controller
public class FrontendController 
{

    /**
     * Actually, this isn't necessary since Spring Boot serves by default index.html
     * in src/resources/static/, without the need of a controller.
     */

    // @GetMapping("/")
    // public String greeting()
    // {
    //     System.out.println("hey!!!!!!!!");
    //     return "index";
    // }
}