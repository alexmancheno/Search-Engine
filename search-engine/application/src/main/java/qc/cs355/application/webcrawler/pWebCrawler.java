package qc.cs355.application.webcrawler;

import java.util.Set;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.io.IOException;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

public class pWebCrawler implements Callable<Vector<URLAndKeywords>>{
	public Set<String> visitedURLs = new HashSet<String>();
	public Queue<String> URLsToVisit = new LinkedList<String>();
	
	long visitLimit = 0;

	public pWebCrawler(String seed, long visitLimit) {
		this.visitLimit = visitLimit;
		URLsToVisit.add(seed);
	}
	
	@Override
	public Vector<URLAndKeywords> call() {
		Vector<URLAndKeywords> resultList = new Vector<URLAndKeywords>();

		while(!URLsToVisit.isEmpty() && visitLimit > 0) {
			String candidate = URLsToVisit.poll();
			if(visitedURLs.contains(candidate)) {
				continue;
			}
			else {
				visitedURLs.add(candidate);
			}
			try {
				resultList.add(pScraper.scrape(candidate, URLsToVisit));
			} catch (IOException e) {
				System.out.println("Error accessing the candidate: " + candidate);
			}
			visitLimit--;
			
		}
		return resultList;
	}
	
}
