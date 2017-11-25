package Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.olingo.commons.api.data.Entity;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.server.model.Resource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Domains.Domains;
import General.FindPOS;
import General.StemmingAndLemmatization;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import zemberek.morphology.ambiguity.Z3MarkovModelDisambiguator;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;
import zemberek.morphology.analysis.tr.TurkishSentenceAnalyzer;
import zemberek.tokenization.TurkishTokenizer;

import Gui.GuiMain;
import Questions.SportQuestions;
import Search.SearchQuery;


public class DTest{
	
	public static void main(String []args)  throws Exception {
		/*TurkishMorphology morphology=TurkishMorphology.createWithDefaults();
		List<WordAnalysis> results=morphology.analyze("şimdilerde");
		results.forEach(s -> System.out.println(s.formatLong()));
		*/
		
	    /* FileReader fl=new FileReader("DATA.txt");
	     BufferedReader reader=new BufferedReader(fl);*/
	     
	     // Percentage
	    /* long maxNumberOnSet = 10;
	     double percentageOfMorphology = 0.0;
	     
	     int i=8;
	     
	     String text="";
	     String tempText="";
	     
	     while((tempText=reader.readLine())!=null) {
	    	 text+=tempText;
	     }
	     reader.close();
	     
	     hurriyetApiClient();
	     
	     TurkishTokenizer tokenizer=TurkishTokenizer.DEFAULT;
	     List<String> tokens=tokenizer.tokenizeToStrings(text);
	     tokens=punctuationsClearing(tokens);
	     tokens=stopWordsClearing(tokens);
	     
	     //System.out.println(tokens);
	     
	     Map<String, Long> counts=tokens.stream().collect(Collectors.groupingBy(s->s,Collectors.counting()));
	     
	     counts.entrySet().stream()
	        .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) 
	        .limit(maxNumberOnSet) ;
	        //.forEach(System.out::println); // or any other terminal method
	     
	     
	     Stream<Entry<String,Long>> lstTop=counts.entrySet().stream()
	 	        .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) 
		        .limit(maxNumberOnSet);
	     
	    List<String> clearedTop = clearTop(lstTop);//Temizlenmiş top 10 Liste
	    
	    lstTop=counts.entrySet().stream()
	 	        .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) 
		        .limit(maxNumberOnSet);
	    
	    
	   
	    morphologicAnalysis(clearTop(lstTop));*/
	     
	   // hurriyetApiClient(Domains.getSportUri());	
		
		//Launching GUI
		Application.launch(GuiMain.class, args);  
	   
		//System.out.println(SportQuestions.getJSON());
		
	    //wikipediaAPI();
	}

	
	private static void wikipediaAPI() throws UnsupportedEncodingException, IOException, ParseException {
		
		String searching = "koşma";
		
		System.out.println(searching);
		
		String encode = "UTF-8";
		
		String wikiApiJson="https://tr.wikipedia.org/w/api.php?action=query&prop=extracts&rvprop=content&format=json&titles="+URLEncoder.encode(searching,encode);
		//
		HttpURLConnection httpCon=(HttpURLConnection) new URL(wikiApiJson).openConnection();
		httpCon.addRequestProperty("User-Agent", "Mozilla/5.0");
		
		BufferedReader reader=new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
		
		String response=reader.lines().collect(Collectors.joining());
		
		String a=StringEscapeUtils.unescapeJava(response);
		
		//System.out.println(a);
		
		reader.close();
		
		
		String result=a.split("\"extract\":\"")[1];
		//System.out.println(result);
		
		Document doc=Jsoup.parse(result);
		Elements paragraphs=doc.select("p");
		
		for(Element p:paragraphs) {
			System.out.println(p.text());
			break;
		}
		
	}
	
	private static void hurriyetApiClient(String uri) throws IOException, ParseException {
		
		//Creating the client.
		Client client = ClientBuilder.newClient();
		//Targeting the URI.
		WebTarget target = client.target(uri); //Sports news
		//Getting response.
		
	//	System.out.println(target.request().accept(MediaType.APPLICATION_JSON).header("apikey", "d8fafd060cd14206b23b6cf93b61689d").get(String.class));	

		
		String s=target.request().accept(MediaType.APPLICATION_JSON).header("apikey", "d8fafd060cd14206b23b6cf93b61689d").get(String.class);
		
		JSONParser parser=new JSONParser();
		
		try {
			Object obj=parser.parse(s);
			
			JSONObject jsonObject=(JSONObject)obj;
			
			System.out.println(jsonObject);
			
			JSONArray arr=(JSONArray)jsonObject.get("Description");
			
			 Iterator<String> iterator = arr.iterator();
	            while (iterator.hasNext()) {
	                System.out.println(iterator.next());
	            }
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		    
	}
	
	private static void morphologicAnalysis(List<String> topClearedElements) throws Exception {//Yüzdeleme işlemi burda yapılacak...
		TurkishMorphology morphology=TurkishMorphology.createWithDefaults();
		//List<WordAnalysis> result=morphology.analyze("Fenerbahçe");	
		
		int countNoun=0;
		int countAdj=0;
		int countnounProp=0;
		int countVerb=0;
		
		for(int i=0;i<topClearedElements.size();i++) {
			if(topClearedElements.get(i).equals("")) {
				continue;
			}else {
			String s=morphology.analyze(topClearedElements.get(i)).get(0).getDictionaryItem().id.toString();
			//System.out.println(topClearedElements.get(i));
			//System.out.println(morphology.analyze(topClearedElements.get(i)).get(0).getDictionaryItem().id.toString());
			//System.out.println(s.substring(s.indexOf("_")+1));
		    if(s.substring(s.indexOf("_")+1).equalsIgnoreCase("Noun")) {
		    	countNoun+=1;
		    }
		    else if(s.substring(s.indexOf("_")+1).equalsIgnoreCase("Noun_Prop")){
		    	countnounProp+=1;
		    }
		    else if(s.substring(s.indexOf("_")+1).equalsIgnoreCase("Adj")) {
		    	countAdj+=1;
		    }
		    else if(s.substring(s.indexOf("_")+1).equalsIgnoreCase("Verb")) {
		    	countVerb+=1;
		    }
		    }
		}
		
		int total=countAdj+countNoun+countnounProp+countVerb;
		
		double adjRatio=1.0*(countAdj*100)/total;
		double nounRatio=1.0*(countNoun*100)/total;
		double nounPropRatio=1.0*(countnounProp*100)/total;
		double verbRatio=1.0*(countVerb*100)/total;
		
		System.out.println("Sıfat Oranı:%"+adjRatio+"\n"+"İsim Oranı:%"+nounRatio
				+"\n"+"İsim Prop Oranı:%"+nounPropRatio+"\n"+"Fiil Oranı:%"+verbRatio);

	}
	
	private static List<String> clearTop(Stream<Entry<String, Long>> lstTop) {
		
	     List<Entry<String, Long>> target=lstTop.collect(Collectors.toList());
	     List<String> topClearedElements=new ArrayList<String>();
	     
	     for(int i=0;i<target.size();i++) {
	    	 topClearedElements.add(i,target.get(i).toString().replaceAll("[-+.^:,=]","").replaceAll("\\d","") );
	     }
	     
	     return topClearedElements;
	}
	
	private static void analyzeWords(Map<String,Long> counts) {
		List<String> lstTopWords=new ArrayList<String>(counts.keySet());
		
		for (String string : lstTopWords) {
			System.out.println(string);
		}
	}
	
	private static ArrayList<String> readStopWords() throws IOException {
		BufferedReader reader=new BufferedReader(new FileReader("Stopwords.txt"));
		
		ArrayList<String> stopWords=new ArrayList<>();
		
		String inLine;
		while((inLine=reader.readLine())!=null) {
			stopWords.add(inLine);
		}
		
		reader.close();
		
		return stopWords;
	}
	
	private static List<String> punctuationsClearing(List<String> text){
		for (Iterator<String> iterator = text.iterator(); iterator.hasNext(); ) {
		    String value = iterator.next();
		    if (value.equals(",")||value.equals("!")||value.equals(".")||value.equals("?")
		    		||value.equals(":")||value.equals(";")||value.equals("...")
		    		||value.equals(")")||value.equals("(")||value.equals("“")
		    		||value.equals("”")||value.equals("'")||value.equals("\"")
		    		||value.equals(" ")) {
		        iterator.remove();
		    }
		}
		return text;
	}
	
	private static List<String> stopWordsClearing(List<String> text) throws IOException{
		try {
			ArrayList<String> stopWords=readStopWords();
			for(int i=0;i<text.size();i++) {
				for(int j=0;j<stopWords.size();j++) {
					if(text.get(i).equals(stopWords.get(j))) {
						text.remove(i);
					}
				}
			}
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}

		return text;
	}
}
