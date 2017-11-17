package Answers;

/*
 * Author:Uğur Pek
 * Created Date:08.11.2017
 * 
 * */
public class GreetingsAnswers {
	//Members
	private static final String[] greetings= {"Selam !","Selamlar !","Merhaba !","Merhabalar !"};
	private static final String[] endings= {"Görüşmek üzere","görüşürüz","güle güle","Hoşçakal"};
	
	private static String[] questionGreetings=GreetingsQuestions.getGreetings();
	
	//Methods
	public static String greet(String question){
		if(Arrays.asList(questionGreetings).contains(question)){
		      int index=ThreadLocalRandom.current().nextnt(0,greetings.length+1);
		      
			return greetings[index];
		}
		
		return;
	}
	
	public static String[] getGreetings() {
		return greetings;
	}
	public static String[] getEndings() {
		return endings;
	}
	
}//end of class
