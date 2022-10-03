package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {
	 @SuppressWarnings("unchecked")
	    public void readJson() 
	    {
	        //JSON parser object to parse read file
	        JSONParser jsonParser = new JSONParser();
	        
	         
	        try (FileReader reader = new FileReader("/SeleniumPOMFramework/src/main/resources/testdata/testData3.json"))
	        {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            JSONObject flightDetails = (JSONObject) obj;
	            System.out.println(flightDetails);
	            
	            String flightType = (String) flightDetails.get("flightType");
	            String flightDomInt = (String) flightDetails.get("flightDomInt");
	           
	            System.out.println(flightType + " - "+ flightDomInt);
	           
	            
	            if (flightType.equalsIgnoreCase("Round Trip") || flightType.equalsIgnoreCase("One Way")) {
	            	JSONObject flight = (JSONObject) flightDetails.get("flight");
	            	String origin = (String) flight.get("Origin");
	            	String destination = (String) flight.get("Destination");
	            	System.out.println(origin + " - " + destination);
	            } else {
	            	JSONArray flights = (JSONArray) flightDetails.get("flight");
	            	for (Object flight : flights) {
						System.out.println(flight);
						
						JSONObject flightObject = (JSONObject) flight;
						String origin = (String) flightObject.get("Origin");
		            	String destination = (String) flightObject.get("Destination");
		            	System.out.println(origin + " - " + destination);
					}
	            }
	            
	            
	            
	            //Iterate over employee array
//	            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
	 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }
	
	 
//	    private static void parseEmployeeObject(JSONObject employee) 
//	    {
//	        //Get employee object within list
//	        JSONObject employeeObject = (JSONObject) employee.get("employee");
//	         
//	        //Get employee first name
//	        String firstName = (String) employeeObject.get("firstName");    
//	        System.out.println(firstName);
//	         
//	        //Get employee last name
//	        String lastName = (String) employeeObject.get("lastName");  
//	        System.out.println(lastName);
//	         
//	        //Get employee website name
//	        String website = (String) employeeObject.get("website");    
//	        System.out.println(website);
//	    }
	

}
