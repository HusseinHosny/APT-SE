package crawler;

import java.io.IOException;
import java.util.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import robot.RobotController;

class Crawler implements Runnable{
	String url;
	Link link;
	Set<Link> links;	//HashSet
	Document page;
	CrawlerController cController;	//will make sure of it
	RobotController rController;
	boolean loadDocument(){
		//incomplete
		try
        {
			if(rController.isSafe(url)){
				Connection connection = Jsoup.connect(url);
				page = connection.get();
				int statusCode=connection.response().statusCode();
				if(statusCode/100 == 2) // 200 is the HTTP OK status code
                                                          // indicating that everything is great.
				{
					//System.out.println("\n**Visiting** Received web page at " + url);
					
					if(!connection.response().contentType().contains("text/html"))
	            	{
						//System.out.println("**Failure** Retrieved something other than HTML");
						return false;
	            	}
					else if(!page.hasText()){
						return false;
					}
					return true;
				}
			
			}
			return false;
				
        }
        catch(IOException ioe)
        {
            // We were not successful in our HTTP request
            return false;
        }
	}
	void populateLinks(){
		Elements linksOnPage = page.select("a[href]");
		//System.out.println("Found (" + linksOnPage.size() + ") links");
		Link absURLLink;
		for(Element link : linksOnPage)
		{
			absURLLink=new Link(link.absUrl("href"));
			if(!absURLLink.equals(link))	//add if it's not referring to itself
			{
				this.links.add(absURLLink);
			}
		}
		
	}
	

	///////////////////////////Incomplete////////////////////////////////
	void Crawl(){
		//Incomplete
	}
	Crawler(){
		//Incomplete
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
