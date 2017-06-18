package bluenile.rest.jw;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.*;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Root resource (exposed at "words" path)
 */
@Path("/words")
public class ScrabbleSolver {

  
	final String wordlists_URL = "http://www-01.sil.org/linguistics/wordlists/english/wordlist/wordsEn.txt";
	/*
    @GET
    @Produces(MediaType.TEXT_PLAIN)//@Produces specifies the response MIME media types.
    public String getIt() {
        return "Got it!";
        
    }
    */
     public List<String> read() throws IOException{
    	URL wl_URL = new URL(wordlists_URL);
    	BufferedReader in = new BufferedReader(
    	         new InputStreamReader(wl_URL.openStream()));
        List<String> result = new ArrayList<>();
    	    
    	String inputLine;

    	while ((inputLine = in.readLine()) != null){
    	    result.add(inputLine);
    	 }
    	 in.close();
    	    
    	 return result;
     }
     
     /**
      * Method handling HTTP GET requests. The returned object will be sent
      * to the client as "application/json" media type.
      *
      * @return String that will be returned as a application/json response.
      */
	  @Path("{words}")//@Path specifies the relative path for a resource class or method.
	  @GET
	  @Produces("application/json")
	  public Response convertFtoCfromInput(@PathParam("words") String str) throws JSONException {
		  List<String> params = new ArrayList<>();
		  List<String> JSONlist=new ArrayList<>();
		  List<String> sortedJSONlist=new ArrayList<>();
		  
		  try {
			  params=read();
		  } catch (IOException e) {
			// TODO Auto-generated catch block
		    	e.printStackTrace();
		  }
		  
		  List<String> allSubstrings=new AllSubStrings(str.toLowerCase()).get_AllSubStrings();
		  //System.out.println("allSubstrings: "+allSubstrings.size() +"/"+allSubstrings);
		  
		  for (String subString : allSubstrings) {
			 if(params.contains(subString)){
			 	 JSONlist.add(subString);
			     //System.out.println("Combinations: "+ permutation);
			 }
		}
		 
		 sortedJSONlist= new Point().sortByScore(JSONlist);
		 String json=new JSONArray(sortedJSONlist).toString();
		 
	     return Response.status(200).entity(json).build();
		
	  }
}
