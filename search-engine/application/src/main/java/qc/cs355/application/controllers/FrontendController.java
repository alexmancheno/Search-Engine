package qc.cs355.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This is the controller that should get requests that should respond with
 * HTML pages to render. 
 */
@Controller
public class FrontendController 
{

    @GetMapping("/")
    public String greeting()
    {
        System.out.println("hey!!!!!!!!");
        return "index";
    }
}