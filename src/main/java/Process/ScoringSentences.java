package Process;

import java.util.ArrayList;

import zemberek.tokenization.TurkishTokenizer;

/*
 * Author:Uğur Pek
 * Created Date:18.11.2017
 * 
 * */

public class ScoringSentences {
	//Fields
	private static TurkishTokenizer tokenizer=TurkishTokenizer.DEFAULT;
	
	//Methods
	public static double giveScore(String question) {
		
		ArrayList<String> lstTokenizedQuestion=new ArrayList<String>();
		
		String puncCleared=PunctuationClearing.clearPunc(question);
		
		lstTokenizedQuestion=(ArrayList<String>)tokenizer.tokenizeToStrings(puncCleared);
		
		for(String s:lstTokenizedQuestion) {
			System.out.println(s);
		}
		
		
		return 0;
	}
	
}//end of class
