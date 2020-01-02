package multichat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

 
public class Createobject
{
    @SuppressWarnings("unchecked")
    public void cobj(String pack,String name,int read_status)//read_status=1 for read msg and 0 for unread msg
    { 
        JSONObject sender = new JSONObject();// if sender sends data first time create his key

    	
    	String msg[] = {"","","",""};
    	int j=0;
    	for(int i=0;i<pack.length();i++) {
    		if(String.valueOf(pack.charAt(i)).equals("|")) {
    			j++;
    			continue;
    		}
    		msg[j]=msg[j]+String.valueOf(pack.charAt(i));
    	}
//    	if(read(msg[3],name)!=null) {
//    		Calendar cal = Calendar.getInstance();
//            String date_time=cal.getTime().toString();
//		JSONObject msgs=read(msg[3],name);
//		msgs.put(date_time, msg[2]);
//        sender.put(msg[3], msgs);
//        System.out.println(sender);
//    	}

//    	System.out.println("msg[3]is "+msg[3]);
    	if(true) {
        JSONObject putmsg = new JSONObject();
        Calendar cal = Calendar.getInstance();
        String date_time=cal.getTime().toString();
        putmsg.put(date_time, pack);
        sender.put(msg[3], putmsg);
        System.out.println(sender);
    	}
 
         
//        JSONObject employeeObject = new JSONObject(); 
//        employeeObject.put(sendername, pack);
//         
//        //Second Employee
//        JSONObject employeeDetails2 = new JSONObject();
//        employeeDetails2.put("firstName", "Brian");
//        employeeDetails2.put("lastName", "Schultz");
//        employeeDetails2.put("website", "example.com");
//         
//        JSONObject employeeObject2 = new JSONObject(); 
//        employeeObject2.put("employee", employeeDetails2);
         
        //Add employees to list
        
//        employeeList.add(employeeObject2);
        
        //Write JSON file
        try  {
                Files.write(Paths.get(name+".json"), sender.toJSONString().getBytes(),StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void create(String name) {
    	
    	 try (FileWriter file = new FileWriter(name+".json")) {
    		 
             file.flush();
  
         } catch (IOException e) {
             e.printStackTrace();
         }
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public JSONObject read(String sender,String name)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try{
        	 FileReader reader = new FileReader(name+".json");
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject employeeList = (JSONObject) obj;
//            System.out.println(employeeList);
             JSONObject msgs=(JSONObject)employeeList.get(sender);
             return msgs;
            //Iterate over employee array
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        catch(Exception e) {
        	
        }
		return null;
    }
 
    private void parseEmployeeObject(JSONObject employee) 
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");
         
        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");    
        System.out.println(firstName);
         
        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");  
        System.out.println(lastName);
         
        //Get employee website name
        String website = (String) employeeObject.get("website");    
        System.out.println(website);
    }
}


