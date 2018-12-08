package qc.cs355.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import qc.cs355.application.webcrawler.pWebCrawler;

@SpringBootApplication
public class Application 
{

	public static void main(String[] args) 
	{
		//Starting the web crawler at server start, with the first site
		//being cnn, and the maximum number of crawled sites set at 100000
		//new Thread(new pWebCrawler("https://www.cnn.com", 100000));
		SpringApplication.run(Application.class, args);
	}
}
