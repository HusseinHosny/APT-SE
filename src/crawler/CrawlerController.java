/**
 * 
 */
package crawler;

import java.util.*;

/**
 * @author Hussein
 *
 */
public class CrawlerController {
	public int pagesVisitedCount;
	LinkedList<Link> pagesToVisit;
	HashSet<Link> pagesVisited;
	Crawler[] crawlers;
	//RobotController rController;
	private Object getLink;
	private Object addLink;

	CrawlerController(int numOfCrawlers){
		//Incomplete
	}
	synchronized Link requestLinkToVisit(){
		//Incomplete
		synchronized(getLink){
			return new Link("a");
		}
	}
	void addExtractedLink(Link link){//linkFromCrawler; we want to add this link to pagesToVisit
		//Incomplete
		synchronized(addLink){
			//code
		}
	}
	void addBaseLink(Link link){//Already crawled link; we want to add this link to pagesVisited
		//Incomplete
		synchronized(addLink){
			//code
		}
	}
	
}
