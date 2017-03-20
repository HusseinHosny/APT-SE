package crawler;

import java.security.NoSuchAlgorithmException;

public class test {
public static void main(String[] args){
	CrawlerController c=new CrawlerController(1);
	c.startCrawling();
	}
	

void test1(){
	long st,et,rt;
	st=System.currentTimeMillis();
	String hashValue="";
	try {
		hashValue = SHA256Hasher.getURLHash("https://www.google.com/search?q=how+to+add+and+delete+folders+in+github&oq=how+to+add+and+delete+folders+in+github&aqs=chrome..69i57.9803j0j7&sourceid=chrome&ie=UTF-8");
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	et=System.currentTimeMillis();
	rt=et-st;
	
	
	System.out.println("Time to calculate hashvalue for: https://www.google.com/search?q=how+to+add+and+delete+folders+in+github&oq=how+to+add+and+delete+folders+in+github&aqs=chrome..69i57.9803j0j7&sourceid=chrome&ie=UTF-8\nIs: "+rt+"\nHashvalue="+hashValue);
	

}
}
