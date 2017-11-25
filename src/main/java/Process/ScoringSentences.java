package Process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.Token;

import General.FindPOS;
import General.QuestionTypes;
import General.StemmingAndLemmatization;
import Search.SearchQuery;
import zemberek.core.logging.Log;
import zemberek.morphology.ambiguity.TurkishMorphDisambiguator;
import zemberek.morphology.analysis.SentenceAnalysis;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;
import zemberek.morphology.analysis.tr.TurkishSentenceAnalyzer;
import zemberek.tokenization.TurkishTokenizer;
import zemberek.tokenization.antlr.TurkishLexer;

/*
 * Author:Uğur Pek
 * Created Date:18.11.2017
 * 
 * */

public class ScoringSentences {
	
	//Methods
	public static double[] giveScore(String question,TurkishMorphology morphology,TurkishTokenizer tokenizer,TurkishSentenceAnalyzer analyzer) throws Exception {
		
		ArrayList<String> lstParagraphs=new ArrayList<String>();
		double[] pointsOfAnswers=new double[lstParagraphs.size()];
		
		int cK=0;//Query number in answer
		int tP=0;//Distance of queries to each other
		int sk=0;//Sentence number in query
		
		//Start to pointing
		
		SentenceAnalysis analysis = analyzer.analyze(question);
        analyzer.disambiguate(analysis);

        for (SentenceAnalysis.Entry entry : analysis) {
            WordAnalysis wa = entry.parses.get(0);
            System.out.println(wa.dictionaryItem.primaryPos+"-"+wa.dictionaryItem.secondaryPos);
            
        }
		
		
		
		
		return null;
	}
	
	private static ArrayList<String> selectCandidateAnswers(String question,TurkishMorphology morphology,TurkishTokenizer tokenizer,TurkishSentenceAnalyzer analyzer){
		ArrayList<String> finalAnswers=new ArrayList<String>();
		SentenceAnalysis analysis=new SentenceAnalysis();
		try {
			ArrayList<String> lstParagraphs=SearchQuery.getParagraphsFromPages(question);
			List<String> stemsOfQuestion=stems(question, morphology, tokenizer);//Stemming Question
			
			int sizeOfQuestion=stemsOfQuestion.size();
			
			String firstPropNoun=FindPOS.test(question,analyzer);
			
			for(int i=0;i<lstParagraphs.size();i++) {
				List<String> stemsOfAnswer=stems(lstParagraphs.get(i), morphology, tokenizer);//For every Answer
				ArrayList<String> commonElements=new ArrayList<>(stemsOfAnswer);
				
				commonElements.retainAll(stemsOfQuestion);//Compare and remove different elements
				
				if((sizeOfQuestion/2)<=commonElements.size()) {//First rule approved.
					analysis=analyzer.analyze(question);
					analyzer.disambiguate(analysis);
					

					if(commonElements.contains(firstPropNoun)) {//Second Rule
						finalAnswers.add(lstParagraphs.get(i));
					}
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return finalAnswers;
	}
	
	private static String isQuestionType(String question) {
		ArrayList<String> lstYears=QuestionTypes.lstYears();
		String type="";
		
		for(String year:lstYears) {
			if(question.toLowerCase().contains(year.toLowerCase())) {
				type="Year";
			}
			
		}
		
		return type;
	}
	
	private static ArrayList<String> yearPattern(String answer) {
		String[] months= {"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos"
				,"Eylül","Ekim","Kasım","Aralık"};
		
		int startIndex=0;
		int endIndex=0;
		
		ArrayList<String> lstDates=new ArrayList<String>();
		
		for(String month:months) {
		    String wordToFind = month;
		    Pattern word = Pattern.compile(wordToFind);
		    Matcher match = word.matcher(answer);
		    
		    while(match.find()) {
		    	startIndex=match.start();
		    	endIndex=match.end();
		    	String first=answer.substring(startIndex-3,startIndex-1);
				String second=answer.substring(endIndex+1, endIndex+5);
				lstDates.add(first+" "+month+" "+second);
		    	
		    }
		    
		}
		return lstDates;
	}
	
	private static boolean isInteger(String s) {
	      boolean isValidInteger = false;
	      try
	      {
	         Integer.parseInt(s);
	 
	         // s is a valid integer
	 
	         isValidInteger = true;
	      }
	      catch (NumberFormatException ex)
	      {
	         // s is not an integer
	      }
	 
	      return isValidInteger;
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
	
	public static void main(String[] args) throws Exception {
		
		try {
			ArrayList<String> lstCand=selectCandidateAnswers("michael jackson ne zaman öldü?", TurkishMorphology.createWithDefaults(), TurkishTokenizer.DEFAULT, new TurkishSentenceAnalyzer(TurkishMorphology.createWithDefaults(), new TurkishMorphDisambiguator() {	
				@Override
				public void disambiguate(SentenceAnalysis arg0) {
					// TODO Auto-generated method stub
					
				}
			}));
			
			//System.out.println(lstCand);
			
			if(isQuestionType("michael jackson ne zaman öldü?").equalsIgnoreCase("Year")) {
				System.out.println(yearPattern(lstCand.get(0)));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}//end of class
