package Questions;

import java.util.List;

import General.DomainQuestions;

/*
 * Author:Uğur Pek
 * Created Date:08.11.2017
 * 
 * */

public class PoliticalQuestions extends DomainQuestions{

	public PoliticalQuestions(String domainName, String domainURL, List<String> lstQuestionTypes) {
		super(domainName, domainURL, lstQuestionTypes);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getDomainName() {
		// TODO Auto-generated method stub
		return super.getDomainName();
	}
	@Override
	public void setDomainName(String domainName) {
		// TODO Auto-generated method stub
		super.setDomainName(domainName);
	}
	@Override
	public String getDomainURL() {
		// TODO Auto-generated method stub
		return super.getDomainURL();
	}
	@Override
	public void setDomainURL(String domainURL) {
		// TODO Auto-generated method stub
		super.setDomainURL(domainURL);
	}
	@Override
	public List<String> getLstQuestionTypes() {
		// TODO Auto-generated method stub
		return super.getLstQuestionTypes();
	}
	@Override
	public void setLstQuestionTypes(List<String> lstQuestionTypes) {
		// TODO Auto-generated method stub
		super.setLstQuestionTypes(lstQuestionTypes);
	}
	
	@Override
	public String toString() {
		return "Political Questions";
	}

}//end of class
