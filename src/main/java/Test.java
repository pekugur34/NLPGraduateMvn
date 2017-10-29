import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;
import zemberek.tokenization.TurkishTokenizer;

public class Test {
	public static void main(String []args) throws Exception {
		/*TurkishMorphology morphology=TurkishMorphology.createWithDefaults();
		List<WordAnalysis> results=morphology.analyze("şimdilerde");
		results.forEach(s -> System.out.println(s.formatLong()));
		*/
		
	     FileReader fl=new FileReader("DATA.txt");
	     BufferedReader reader=new BufferedReader(fl);
	     
	     
	     
	     String text="";
	     String tempText="";
	     
	     while((tempText=reader.readLine())!=null) {
	    	 text+=tempText;
	     }
	     reader.close();
	     
	     
	     
	     TurkishTokenizer tokenizer=TurkishTokenizer.DEFAULT;
	     List<String> tokens=tokenizer.tokenizeToStrings(text);
	     tokens=punctuationsClearing(tokens);
	     tokens=stopWordsClearing(tokens);
	     
	     //System.out.println(tokens);
	     
	     Map<String, Long> counts=tokens.stream().collect(Collectors.groupingBy(s->s,Collectors.counting()));
	     
	     counts.entrySet().stream()
	        .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) 
	        .limit(10) ;
	        //.forEach(System.out::println); // or any other terminal method
	     
	     
	     Stream<Entry<String,Long>> lstTop=counts.entrySet().stream()
	 	        .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) 
		        .limit(10);
	     
	    List<String> clearedTop = clearTop(lstTop);//Temizlenmiş top 10 Liste
	    
	    lstTop=counts.entrySet().stream()
	 	        .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) 
		        .limit(10);
	    
	    

	   
	    morphologicAnalysis(clearTop(lstTop));
		
		
	}
	
	
	private static void morphologicAnalysis(List<String> topClearedElements) throws Exception {//Yüzdeleme işlemi burda yapılacak...
		TurkishMorphology morphology=TurkishMorphology.createWithDefaults();
		List<WordAnalysis> result=morphology.analyze("Fenerbahçe");
		
		result.forEach(s -> System.out.println(s.formatLong()));
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
		    		||value.equals("”")||value.equals("'")||value.equals("\"")) {
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
