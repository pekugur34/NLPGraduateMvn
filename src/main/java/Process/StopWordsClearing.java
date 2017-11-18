package Process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Author:Uğur Pek
 * Created Date:11.11.2017
 * 
 * */

public class StopWordsClearing {
	
	public static String clearStopWords(String text) {
		
		List<String> stopWords=new ArrayList<String>();
		String stopWord;
		StringBuilder sb=new StringBuilder();
		
		try {
			BufferedReader reader=new BufferedReader(new FileReader(new File("Stopwords.txt")));
			while((stopWord=reader.readLine())!=null) {
				stopWords.add(stopWord);
			}
			
			reader.close();
			List<String> lstText=new ArrayList<>(Arrays.asList(text.split(" ")));
			
			
			
			for(int i=0;i<stopWords.size();i++) {
				if(lstText.contains(stopWords.get(i))) {
					lstText.remove(stopWords.get(i));
				}
			}
			
			
			
			for(String s:lstText) {
				sb.append(s);
				sb.append("\t");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	
}//end of class
