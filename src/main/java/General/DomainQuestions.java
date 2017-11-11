package General;

import java.util.List;

/*
 * Author:Uğur Pek
 * Created Date:08.11.2017
 * 
 * */

public class DomainQuestions {
	//Members
	private String domainName;
	private String domainURL;
	private List<String> lstQuestionTypes;
	
	//Methods
	public DomainQuestions(String domainName, String domainURL, List<String> lstQuestionTypes) {
		super();
		this.domainName = domainName;
		this.domainURL = domainURL;
		this.lstQuestionTypes = lstQuestionTypes;
	}
	
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getDomainURL() {
		return domainURL;
	}
	public void setDomainURL(String domainURL) {
		this.domainURL = domainURL;
	}
	public List<String> getLstQuestionTypes() {
		return lstQuestionTypes;
	}
	public void setLstQuestionTypes(List<String> lstQuestionTypes) {
		this.lstQuestionTypes = lstQuestionTypes;
	}
	
	@Override
	public String toString() {
		return "Base Class of Domain Questions";
	}
	
	
	
}//end of class
