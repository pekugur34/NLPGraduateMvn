package Process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;
import zemberek.tokenization.TurkishTokenizer;

/*
 * Author:Uğur Pek
 * Created Date:18.11.2017
 * 
 * */

public class ScoringSentences {
	//Fields
	private static TurkishTokenizer tokenizer=TurkishTokenizer.DEFAULT;
	private static TurkishMorphology morphology;
	
	//Methods
	public static double giveScore(String question) throws Exception {
		
		morphology=TurkishMorphology.createWithDefaults();
		
		ArrayList<String> lstTokenizedQuestion=new ArrayList<String>();
		String puncCleared=PunctuationClearing.clearPunc(question);
		String stopWordCleared=StopWordsClearing.clearStopWords(puncCleared);
		lstTokenizedQuestion=(ArrayList<String>)tokenizer.tokenizeToStrings(stopWordCleared);
		
		System.out.println(lstTokenizedQuestion);
		
		
		return 0;
	}
	
}//end of class
