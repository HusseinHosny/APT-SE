package crawler;

import java.util.*;

public class Link {
	String url;
	int inLinksCount;
	Set<Link> inLinks;
	Link(){
		inLinksCount=0;
		inLinks=new HashSet<Link>();
	}
	Link(String _url){
		url=_url;
		inLinksCount=0;
		inLinks=new HashSet<Link>();
	}
	synchronized void addInLink(Link inLink) {
		inLinksCount++;
		inLinks.add(inLink);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	void merge(Link link){
		inLinksCount+=link.inLinksCount;
		inLinks.addAll(link.inLinks);
	}
}
