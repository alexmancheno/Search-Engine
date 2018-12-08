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
		while(!URLsToVisit.isEmpty() && visitLimit > 0) {
			String candidate = URLsToVisit.poll();
			try {
				URLAndKeywords result = pScraper.scrape(candidate, URLsToVisit);
				Database.insertScrapeResults(result);
			} catch (IOException e) {
				System.out.println("Error accessing the candidate: " + candidate);
			}
			visitLimit--;
		}
	}
	
}
