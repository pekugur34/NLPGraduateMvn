package Domains;


/*
 * Author:Uğur Pek
 * Created Date:15.11.2017
 * 
 * */

public class Domains {

	//Fields
	private static final String sportURI="https://api.hurriyet.com.tr/v1/pages/586364356d7745d27cdb003e?";
	private static final String lastNewsURI="https://api.hurriyet.com.tr/v1/pages/571637076d774a1b64c18b2d?";
	private static final String economyURI="https://api.hurriyet.com.tr/v1/pages/556c3d006534821a54c671e5?";
	private static final String techURI="https://api.hurriyet.com.tr/v1/pages/556c6a756534851a54c5a6cc?";
	
	//Methods
	public static String getSportUri() {
		return sportURI;
	}
	public static String getLastnewsUri() {
		return lastNewsURI;
	}
	public static String getEconomyUri() {
		return economyURI;
	}
	public static String getTechUri() {
		return techURI;
	}
	
}//end of class
