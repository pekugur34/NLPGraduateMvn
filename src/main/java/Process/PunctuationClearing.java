package Process;

/*
 * Author:Uğur Pek
 * Created Date:11.11.2017
 * 
 * */


public class PunctuationClearing {
	
	public static String clearPunc(String text) {
		
		String res=text.replaceAll("\\p{Punct}|\\d","");
		
		return res;
	}
	
}//end of class
