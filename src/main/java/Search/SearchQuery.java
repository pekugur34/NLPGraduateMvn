package Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

/*
 * Author:Uğur Pek
 * Created Date:13.11.2017
 * 
 * */

public final class SearchQuery {
	//Fields
	private static final String SEARCH="https://www.google.com/search";
	
	//Methods
	private static String[] searchQuery(String question) {
		int resultNumber=10;
		String query=SEARCH+"?q="+question+"&num="+resultNumber;
		String[] searchURLs=null;
		
		try {
			Document doc=Jsoup.connect(query).userAgent("Mozilla/5.0").get();
			Elements results=doc.select("h3.r > a");
			
			searchURLs=new String[results.size()];
			int  i=0;
			for(Element result : results) {
				searchURLs[i]=result.attr("href").substring(7,result.attr("href").indexOf("&"));
				//System.out.println(searchURLs[i]);
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return searchURLs;
	}
	
	public static String getDataFromPages(String question) throws IOException {
		String[] URLs=searchQuery(question);
		String useThisURL="";
		for(String url:URLs) {
			if(url.contains("tr.wikipedia.org")) {
			    useThisURL=URLDecoder.decode(url,"UTF-8");
				System.out.println("MY Url:"+useThisURL);
			}
		}
		
		URL page=new URL(useThisURL);
		StringBuffer text=new StringBuffer();
		HttpURLConnection conn=(HttpURLConnection) page.openConnection();
		conn.connect();//Hata veren yer burası
		
		InputStreamReader in=new InputStreamReader((InputStream)conn.getContent());
		BufferedReader reader=new BufferedReader(in);
		
		String line;
		String cleanedLine;
		
	    do {
	    	line=reader.readLine();
	    	text.append(line+"\n");
	    }while(line!=null);
		
	    Document doc=Jsoup.parse(text.toString());
	    
	    System.out.println(doc.select("p").text());
		
		return useThisURL;
	}
	
	public SearchQuery() {
		
	}

	
}//end of class
