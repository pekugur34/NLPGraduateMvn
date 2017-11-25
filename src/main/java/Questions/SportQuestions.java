package Questions;

import java.util.List;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.JsonSerializer;

import Domains.Domains;
import General.DomainQuestions;


/*
 * Author:Uğur Pek
 * Created Date:08.11.2017
 * 
 * */

public class SportQuestions extends Domains{
    
	//Methods
	public static String getJSON() throws Exception{
		String URI=getSportUri();
		
		Client client=ClientBuilder.newClient();
		WebTarget target=client.target(URI);
		
		String s=target.request().accept(MediaType.APPLICATION_JSON).header("apikey", "d8fafd060cd14206b23b6cf93b61689d").get(String.class);
		
		JSONParser parser=new JSONParser();
		
		Object obj=parser.parse(s);
		
		JSONObject jsonObject = (JSONObject) obj;
		
		JSONArray arr=(JSONArray) jsonObject.get("PageNews");
		
		JSONObject desc=(JSONObject)arr.get(1);
		//JSONArray file=(JSONArray) arr.get(11);
		
	    //JSONObject photo=(JSONObject) file.get(0);
	    //System.out.println(photo);
		
		JSONArray files=(JSONArray) jsonObject.get(11);
		
		System.out.println(files);
	
		
		//JSONObject photo=(JSONObject) files.get(0);
		
		//System.out.println(photo);
		
		for(int i=0;i<5;i++) {
			JSONObject temp=(JSONObject) arr.get(i);
			//System.out.println(temp.get("Title")+""+temp.get("FileUrl"));
			
			
		}
		

		return desc.get("Url").toString();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(getJSON());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}
