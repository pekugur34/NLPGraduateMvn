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
			
			for(int i=0;i<lstText.size();i++) {
				for(int j=0;j<stopWords.size();j++) {
					if(lstText.get(i).equalsIgnoreCase(stopWords.get(j))) {
						lstText.remove(i);
					}
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
