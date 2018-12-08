package qc.cs355.application.webcrawler;

import qc.cs355.application.database.*;

import java.io.IOException;
import java.util.List;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class pScraper {

	static public URLAndKeywords scrape(String URL, Queue<String> pages) throws IOException{
		URLAndKeywords result = new URLAndKeywords(URL);
		try {
			Document document = Jsoup.connect(URL).get();
			
			Elements links = document.select("a");
			List<String> linkList = links.eachAttr("href");
			for(String candidateURL : linkList) {
				candidateURL = candidateURL.split("#", 2)[0];
				if(!Database.isWebPageInDatabase(candidateURL)) pages.add(candidateURL);
			}
			
			String words = document.body().text();
			String[] keywords = words.split("[\\p{Punct}\\s]+");
			for(String keyword : keywords) {
				if(result.keywords.containsKey(keyword)) {
					result.keywords.put(keyword, result.keywords.get(keyword)+1);
				}
				else result.keywords.put(keyword, 1);
			}
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
}
