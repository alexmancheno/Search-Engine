package qc.cs355.application.webcrawler;

import qc.cs355.application.database.*;
import java.util.Vector;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

public class pWebCrawler implements Runnable{
	public Queue<String> URLsToVisit = new LinkedList<String>();
	
	long visitLimit = 0;

	public pWebCrawler(String seed, long visitLimit) {
		this.visitLimit = visitLimit;
		URLsToVisit.add(seed);
	}
	
	@Override
	public void run() {
		System.out.println("start crawler"); //debug
		while(!URLsToVisit.isEmpty() && visitLimit > 0) {
			System.out.println("New URL scraped");  //debug
			String candidate = URLsToVisit.poll();
			try {
				URLAndKeywords result = pScraper.scrape(candidate, URLsToVisit);
				Database.insertScrapeResults(result);
				//System.out.println("crawled: " + result.url); //debug
			} catch (IOException e) {
				//System.out.println("Error accessing the candidate: " + candidate);
			}
			visitLimit--;
		}
	}
	
}
