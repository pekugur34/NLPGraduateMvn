package Process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import General.StemmingAndLemmatization;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;
import zemberek.tokenization.TurkishTokenizer;

/*
 * Author:Uğur Pek
 * Created Date:18.11.2017
 * 
 * */

public class ScoringSentences {
	
	//Methods
	public static double giveScore(String question,TurkishMorphology morphology,TurkishTokenizer tokenizer) throws Exception {
		
		String pClearedQuestion=PunctuationClearing.clearPunc(question);
		
		List<String> lstTokenized=tokenizer.tokenizeToStrings(pClearedQuestion);
		
		
		System.out.println(lstTokenized);
		
		return 0;
	}
	

	
	public static List<String> stems(String question,TurkishMorphology morphology,TurkishTokenizer tokenizer){
		String punc=PunctuationClearing.clearPunc(question);//Punctuation Clearing
		String stop=StopWordsClearing.clearStopWords(punc);//StopWord Clearing
		
		List<String> tokenized=tokenizer.tokenizeToStrings(stop);
		
		List<String> stems=new ArrayList<>();
		for(String tokens:tokenized) {
			stems.add(StemmingAndLemmatization.analyze(tokens,morphology));//Stemming
		}
		
		return stems;
	}
	
	
	
	
	
}//end of class
