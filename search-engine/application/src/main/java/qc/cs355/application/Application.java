package qc.cs355.application;

import javax.xml.crypto.Data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import qc.cs355.application.database.Database;

@SpringBootApplication
public class Application 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(Application.class, args);
		//Database.test();
	}
}
