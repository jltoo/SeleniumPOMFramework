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
	        
	         
	        try (FileReader reader = new FileReader("/SeleniumPOMFramework/src/main/resources/testdata/testData1.json"))
	        {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            JSONObject flightDetails = (JSONObject) obj;
	            System.out.println(flightDetails);
	            //FlightType and DOM/INT
	            String flightType = (String) flightDetails.get("flightType");
	            String flightDomInt = (String) flightDetails.get("flightDomInt");
	           
	            System.out.println(flightType + " - "+ flightDomInt);
	            
	            	            
	            //Flight
	            if (flightType.equalsIgnoreCase("Round Trip") || flightType.equalsIgnoreCase("One Way")) {
	            	JSONObject flight = (JSONObject) flightDetails.get("flight");
	            	String origin = (String) flight.get("Origin");
	            	String destination = (String) flight.get("Destination");
	            	System.out.println(origin + " - " + destination);
	            } else {
	            	JSONArray flights = (JSONArray) flightDetails.get("flight");
	            	for (Object flight : flights) {	
						JSONObject flightObject = (JSONObject) flight;
						String origin = (String) flightObject.get("Origin");
		            	String destination = (String) flightObject.get("Destination");
		            	System.out.println(origin + " - " + destination);
					}
	            }
	            
	            parsePaxCount(flightDetails);
	            parseBundleList(flightDetails);
	            
	            JSONArray guestDetailsList = (JSONArray) flightDetails.get("guestDetails");
	            for (Object guestDetailsItem : guestDetailsList) {	
					JSONObject guestDetailsObject = (JSONObject) guestDetailsItem;
					
					String gender = (String) guestDetailsObject.get("gender");
					String firstName = (String) guestDetailsObject.get("firstName");
					String middleName = (String) guestDetailsObject.get("middleName");
					String lastName = (String) guestDetailsObject.get("lastName");
					String dateOfBirth = (String) guestDetailsObject.get("dateOfBirth");
					String Nationality = (String) guestDetailsObject.get("Nationality");
					String PassportNo = (String) guestDetailsObject.get("PassportNo");
					String PassportExp = (String) guestDetailsObject.get("PassportExp");
					String PassportLoc = (String) guestDetailsObject.get("PassportLoc");
					String paxType = (String) guestDetailsObject.get("paxType");
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
	 
	 
	 
	 public static void parsePaxCount(JSONObject flightDetails) {
		 //PaxCount
         JSONObject paxCountList = (JSONObject) flightDetails.get("paxCount");
         System.out.println(paxCountList);
         long adtCount = (long) paxCountList.get("adt");
         long chdCount = (long) paxCountList.get("chd");
         long infWithSeat = (long) paxCountList.get("infWithSeat");
         long infOnLap = (long) paxCountList.get("infOnLap");
//         
         System.out.println(adtCount + " - " + chdCount + " - " + infWithSeat + " - " + infOnLap);
	 }
	 
	 public static void parseBundleList(JSONObject flightDetails) {
		 JSONArray bundleList = (JSONArray) flightDetails.get("bundle");
         
         for (Object bundleItem : bundleList) {	
				JSONObject bundleObject = (JSONObject) bundleItem;
				boolean isPerPaxBundleFlight = (boolean) bundleObject.get("isPerPaxBundleFlight");
				if(isPerPaxBundleFlight==false) {
					String bundleType = (String) bundleObject.get("bundleType");
					System.out.println(bundleType);
				} else {
					JSONArray bundleType = (JSONArray) bundleObject.get("bundleType");
					System.out.println(bundleType);
				}
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
