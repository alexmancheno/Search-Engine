package qc.cs355.backend.backend;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class DatabaseController 
{

    @RequestMapping("/")
    public String index() 
    {
        return "Greetings from Phat Search!!!!";
    }

}