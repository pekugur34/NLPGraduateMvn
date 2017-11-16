package Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
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

import General.StemmingAndLemmatization;
import Process.PunctuationClearing;
import Process.StopWordsClearing;
import Search.SearchQuery;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;
import zemberek.tokenization.TurkishTokenizer;

public class UTest {
	public static void main(String []args) throws Exception {
		
		/*TurkishMorphology morphology=TurkishMorphology.createWithDefaults();
		StemmingAndLemmatization st=new StemmingAndLemmatization(morphology);
		
		
		String firstQuery="Kuş";
		
		String clearedPunc=PunctuationClearing.clearPunc(firstQuery);
		
		String []str=clearedPunc.split(" ");
		
		String beforeStopWord="";
		
		for(int i=0;i<str.length;i++) {
			st.analyze(str[i]);
			System.out.println("");
		}*/
		
		System.out.println(SearchQuery.getDataFromPages("atatürk kimdir?"));
		
		
	
	    
	}
	
	
	
	private static void morphologicAnalysis(List<String> topClearedElements) throws Exception {
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
	
	
}
