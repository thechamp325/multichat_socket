package multichat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

 
public class Createobject
{
    @SuppressWarnings("unchecked")
  public void cobj(String pack,String sender,String receiver,String name,int read_status)//read_status=1 for read msg and 0 for unread msg
  {
    	
    	String msg[] = {"","","",""};
    	int j=0;
    	for(int i=0;i<pack.length();i++) {
    		if(String.valueOf(pack.charAt(i)).equals("|")) {
    			j++;
    			continue;
    		}
    		msg[j]=msg[j]+String.valueOf(pack.charAt(i));
    	}
    	

      Calendar cal = Calendar.getInstance();
      String date_time=cal.getTime().toString();
    	
    	List<List<String>> rows = Arrays.asList(
    		    Arrays.asList(sender, date_time,receiver,pack)
//    		    Arrays.asList("David", "editor", "Python"),
//    		    Arrays.asList("Scott", "editor", "Node.js")
    		);
    	FileWriter csvWriter=null;
    	if(name.equals(sender)) {
//    		System.out.println(receiver);
    	File f = new File(sender+"\\"+receiver+".tsv");
    	if(!f.exists()) { 
    	    // do something
    	
    	try {
    		 csvWriter = new FileWriter(sender+"\\"+receiver+".tsv",true); 
    		csvWriter.append("Sender");
    		csvWriter.append("	");
    		csvWriter.append("Time");
    		csvWriter.append("	");
    		csvWriter.append("Receiver");
    		csvWriter.append("	");
    		csvWriter.append("Message");
    		csvWriter.append("\n");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
 
    	try {
    		for (List<String> rowData : rows) {
    		    csvWriter.append(String.join("	", rowData));
    		    csvWriter.append("\n");
    		}

    		csvWriter.flush();
    		csvWriter.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	}
    	else if(!f.isDirectory()) { 
    		try {
				csvWriter = new FileWriter(sender+"\\"+receiver+".tsv",true);
				for (List<String> rowData : rows) {
	    		    csvWriter.append(String.join("	", rowData));
	    		    csvWriter.append("\n");
	    		}

	    		csvWriter.flush();
	    		csvWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	   
    	}
    	}
    	else if(receiver.equals(name)) {
//    		System.out.println(sender);

    		File f = new File(receiver+"\\"+sender+".tsv");
        	if(!f.exists()) { 
        	    // do something
        	
        	try {
        		 csvWriter = new FileWriter(receiver+"\\"+sender+".tsv",true); 
        		csvWriter.append("Sender");
        		csvWriter.append("	");
        		csvWriter.append("Time");
        		csvWriter.append("	");
        		csvWriter.append("Receiver");
        		csvWriter.append("	");
        		csvWriter.append("Message");
        		csvWriter.append("\n");
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
     
        	try {
        		for (List<String> rowData : rows) {
        		    csvWriter.append(String.join("	", rowData));
        		    csvWriter.append("\n");
        		}

        		csvWriter.flush();
        		csvWriter.close();
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	}
        	else if(!f.isDirectory()) { 
        		try {
    				csvWriter = new FileWriter(receiver+"\\"+sender+".tsv",true);
    				for (List<String> rowData : rows) {
    	    		    csvWriter.append(String.join("	", rowData));
    	    		    csvWriter.append("\n");
    	    		}

    	    		csvWriter.flush();
    	    		csvWriter.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
        	   
        	}
    		
    	}
  }
//    public void cobj(String pack,String sender,String receiver,int read_status)//read_status=1 for read msg and 0 for unread msg
//    { 
//        JSONObject sender = new JSONObject();// if sender sends data first time create his key
//
//    	
//    	String msg[] = {"","","",""};
//    	int j=0;
//    	for(int i=0;i<pack.length();i++) {
//    		if(String.valueOf(pack.charAt(i)).equals("|")) {
//    			j++;
//    			continue;
//    		}
//    		msg[j]=msg[j]+String.valueOf(pack.charAt(i));
//    	}
////    	if(read(msg[3],sender)!=null) {
////    		Calendar cal = Calendar.getInstance();
////            String date_time=cal.getTime().toString();
////		JSONObject msgs=read(msg[3],sender);
////		msgs.put(date_time, msg[2]);
////        sender.put(msg[3], msgs);
////        System.out.println(sender);
////    	}
//
////    	System.out.println("msg[3]is "+msg[3]);
//    	if(true) {
//        JSONObject putmsg = new JSONObject();
//        Calendar cal = Calendar.getInstance();
//        String date_time=cal.getTime().toString();
//        putmsg.put(date_time, pack);
//        sender.put(msg[3], putmsg);
////        System.out.println(sender);
//    	}
// 
//         
////        JSONObject employeeObject = new JSONObject(); 
////        employeeObject.put(sendersender, pack);
////         
////        //Second Employee
////        JSONObject employeeDetails2 = new JSONObject();
////        employeeDetails2.put("firstsender", "Brian");
////        employeeDetails2.put("lastsender", "Schultz");
////        employeeDetails2.put("website", "example.com");
////         
////        JSONObject employeeObject2 = new JSONObject(); 
////        employeeObject2.put("employee", employeeDetails2);
//         
//        //Add employees to list
//        
////        employeeList.add(employeeObject2);
//        
//        //Write JSON file
//        try  {
//                Files.write(Paths.get(sender+"\\"+receiver+".json"), sender.toJSONString().getBytes(),StandardOpenOption.APPEND);
//
//        }
//        catch (IOException e) {
//        	System.out.println("File doesnt exist");
//	       	 try (FileWriter file = new FileWriter(sender+"\\"+receiver+".json")) {
//	            file.flush();
//	 
//	        } catch (IOException e1) {
//	            e1.printStackTrace();
//	        }
//            e.printStackTrace();
//        }
//    	}
    public void create(String sender) {
    	
    	File f= new File("D:\\Ganu\\Codes\\espressif_internship\\javacodes\\multichat\\"+sender);
    	Boolean a =f.mkdir();
    	if(a) {
    		System.out.println("directory made");
    	}
    	else {
    		System.out.println("directory not made");
    	}
//    	 try (FileWriter file = new FileWriter(sender+"\\"+sender+".json")) {
//    		 
//             file.flush();
//  
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    public String read(String sender,String name)
//    {
//        //JSON parser object to parse read file
//        JSONParser jsonParser = new JSONParser();
//         System.out.println("Reading the json file");
//        try{
//        	 FileReader reader = new FileReader(sender+"\\"+sender+".json");
//            //Read JSON file
//            Object obj = jsonParser.parse(reader);
//            JSONObject employeeList = (JSONObject) obj;
////            System.out.println(employeeList);
//             JSONObject msgs=(JSONObject)employeeList.get(sender);
//             return msgs;
//            //Iterate over employee array
// 
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        catch(Exception e) {
//        	
//        }
//		return null;
//    }
 
    public List<String[]> read(String filename,String name) {
    	System.out.println("---Reading previous messages---");
    	File csvFile = new File(name+"\\"+filename+".tsv");
    	List<String[]> data= new ArrayList<String[]>();
    	if (csvFile.isFile()) {
    		try {
    	    // create BufferedReader and read data from csv
    		BufferedReader csvReader = new BufferedReader(new FileReader(name+"\\"+filename+".tsv"));
    		String row=null;
    		int i=0;
    		while ((row = csvReader.readLine()) != null) {
    		     String[] msg = row.split("	");;
//    		     System.out.println(Arrays.toString(msg));
//    		     i++;
    		    // do something with the data
    		     data.add(msg);
    		}
    		csvReader.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		
    	}
//    		System.out.println("---Printing previous data of messages here data 0 ---");
//        	System.out.println(Arrays.toString(data.get(0)));
    	}
    	
    	return data;
    }
    
    private void parseEmployeeObject(JSONObject employee) 
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");
         
        //Get employee first sender
        String firstsender = (String) employeeObject.get("firstsender");    
        System.out.println(firstsender);
         
        //Get employee last sender
        String lastsender = (String) employeeObject.get("lastsender");  
        System.out.println(lastsender);
         
        //Get employee website sender
        String website = (String) employeeObject.get("website");    
        System.out.println(website);
    }
}


