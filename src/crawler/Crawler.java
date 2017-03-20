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
	
	int HTTPStatus;//test
	
	boolean loadDocument(){
		//incomplete
		try
        {
			if(true){
				
				//if(rController.isSafe(url))
				System.setProperty("http.proxyHost", "207.99.118.74"); // set proxy server
				System.setProperty("http.proxyPort", "8080");  //set proxy port
				Connection connection = Jsoup.connect(url).timeout(3000);
				page = connection.get();
				int statusCode=connection.response().statusCode();
				HTTPStatus=statusCode;
				if(statusCode/100 == 2) // 200 is the HTTP OK status code
                                                          // indicating that everything is great.
				{
					
					
					if(!connection.response().contentType().contains("text/html"))
	            	{
						
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
		for(Element iLink : linksOnPage)
		{
			absURLLink=new Link(iLink.absUrl("href").toLowerCase());
			
			if(!absURLLink.equals(link))	//add if it's not referring to itself
			{
				absURLLink.addInLink(link);
				links.add(absURLLink);
				//System.out.println(absURLLink.url);	//testing
			}
		}
		
	}
	

	///////////////////////////Incomplete////////////////////////////////
	void Crawl(){
		//Incomplete
		long it=System.currentTimeMillis();
		long st=it+10000;
		while(System.currentTimeMillis()<st){
			HTTPStatus=0;
			link=cController.requestLinkToVisit();
			url=link.url;
			links.clear();
			System.out.println("\n**Initiating connection with :" + url);//testing
			if(loadDocument()){
				System.out.println("\n**Connection Successful");//testing
				populateLinks();
				System.out.println("\n**Links found:" + links.size());
				for(Link cLink:links){
					cController.addExtractedLink(cLink);
					//cController.index(page);
				}
				cController.addBaseLink(link);
			}else{
				System.out.println("\n**Failure** Error: "+HTTPStatus);//testing
			}
		}
	}
	
	
	Crawler(CrawlerController c){
		//Incomplete
		//should have robotcontroller passed as a parameter
		cController=c;
		links=new HashSet<Link>();
	}
	
	/*void setup(){
		//Incomplete
		//should have robotcontroller passed as a parameter
		
	}*/
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
