package qc.cs355.application.webcrawler;

import java.util.HashMap;

public class URLAndKeywords {
	public HashMap<String, Integer> keywords;
	public String url;
	
	public URLAndKeywords(String url) {
		this.url = url;
		keywords = new HashMap<String, Integer>();
	}
	
}
