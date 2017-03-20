/**
 * 
 */
package crawler;

import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author Hussein
 *
 */
public class CrawlerController {
	public int pagesVisitedCount;
	LinkedList<Link> pagesToVisit;
	HashSet<Link> unarrangedPagesToVisit;
	HashMap<String,Link> pagesVisited;
	Crawler[] crawlers;
	int numOfCrawlers;
	//RobotController rController;
	//private Object getLink;
	//private Object addLink;
	private Object LinkLock=new Object();
	CrawlerController(int numOfCrawlers){
		//Incomplete
		pagesVisitedCount=0;
		pagesToVisit=new LinkedList<Link>();
		unarrangedPagesToVisit=new HashSet<Link>();
		pagesVisited=new HashMap<String,Link>();
		this.numOfCrawlers=numOfCrawlers;
		crawlers=new Crawler[numOfCrawlers];
		for(int i=0;i<numOfCrawlers;i++){
			crawlers[i]=new Crawler(this);
			//crawlers[i].setup(this);
		}
		
	}
	Link requestLinkToVisit(){
		//Incomplete
		synchronized(LinkLock){
			Link nextLink;
	       
	        nextLink = this.pagesToVisit.remove(0);
	        unarrangedPagesToVisit.remove(nextLink);
	        while(this.pagesVisited.containsValue(nextLink)){
	        	addBaseLink(nextLink);
	        	nextLink = this.pagesToVisit.remove(0);
		        unarrangedPagesToVisit.remove(nextLink);
	        }
	        
	        return nextLink;
		}
	}
	void addExtractedLink(Link linkToAdd){//linkFromCrawler; we want to add this link to pagesToVisit and unarrangedpagestovisit
		//Incomplete
		synchronized(LinkLock){
			if(!unarrangedPagesToVisit.contains(linkToAdd)){
				unarrangedPagesToVisit.add(linkToAdd);
				pagesToVisit.add(linkToAdd);
			}else{
				int i=pagesToVisit.indexOf(linkToAdd);
				
				if(i!=0)
				{
					int j=i-1;
					Link temp=pagesToVisit.get(i);
					temp.merge(linkToAdd);
					while(j!=0&&pagesToVisit.get(j).inLinksCount<temp.inLinksCount){
						j--;
					}
					pagesToVisit.remove(i);
					pagesToVisit.add(j, temp);
				}
			}

		}
	}
	void addBaseLink(Link link){//Already crawled link; we want to add this link to pagesVisited
		//Incomplete
		synchronized(LinkLock){
			//code
			try {
				String hashValue=SHA256Hasher.getURLHash(link.url);
				if(pagesVisited.containsKey(hashValue)){
					pagesVisited.get(hashValue).merge(link);
				}else{
					pagesVisited.put(hashValue, link);
					pagesVisitedCount++;
					System.out.println(link.url);	//for testing
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	void startCrawling(){
		//incompletecrawlers=new Crawler[numOfCrawlers];
		
		Link seed=new Link("https://www.wikipedia.org/");
		//Link seed=new Link("https://www.wikipedia.org/");
		addExtractedLink(seed);
		crawlers[0].Crawl();
		//test
		System.out.println("Extracted URLs: "+pagesToVisit.size());
		System.out.println("Pages Crawled: "+pagesVisitedCount);
		int i=0;
		for(String hash:pagesVisited.keySet()){
			i++;
			System.out.println("--->"+i+ "  "+pagesVisited.get(hash).url);
		}
		
		System.out.println("Pages Crawled: "+pagesVisitedCount);
		i=0;
		int count=pagesToVisit.size();
		for(int j=0;j<count/3;j++){
			i++;
			Link l=pagesToVisit.remove(0);
			System.out.println("--->"+i+ "  inLinks:"+l.inLinksCount+"  URL:"+l.url);
		}
		/*for(Link l:pagesToVisit){
			i++;
			//System.out.println("--->"+i+ "  inLinks:"+l.inLinksCount+"  URL:"+l.url);
		}*/
		//endtest
	}
}
