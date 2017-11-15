package General;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Author:Uğur Pek
 * Created Date:12.11.2017
 * 
 * */

public class QuestionSpecification {
	//Fields
	private ArrayList<String> lstQuestionWord;
	private ArrayList<String> lstQuestionType;
	private ArrayList<String> lstToDelete;
	
	//Methods
	public ArrayList<String> getLstQuestionWord() {
		return lstQuestionWord;
	}
	public ArrayList<String> getLstQuestionType() {
		return lstQuestionType;
	}
	public ArrayList<String> getLstToDelete() {
		return lstToDelete;
	}
	
	public QuestionSpecification() {
		this.lstQuestionWord=new ArrayList<String>(Arrays.asList(""));
		this.lstQuestionType=new ArrayList<String>(Arrays.asList(""));
		this.lstToDelete=new ArrayList<String>(Arrays.asList(""));
	}

	

}//end of class
