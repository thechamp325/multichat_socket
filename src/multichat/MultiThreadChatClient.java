package multichat;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Vector;

public class MultiThreadChatClient implements Runnable {			// now here we use 2nd method for thread implementation
public static Scanner s = new Scanner(System.in);
  private static Socket clientSocket = null;
  private static PrintStream os = null;
  private static DataInputStream is = null;
  private static String name = null;
  public static String destination=null;
  private static Vector<String> prevgroups = new Vector<String>();
  public static Vector<String> prevdestination= new Vector<String>();;

  public static int choice = 0;

  private static BufferedReader inputLine = null;
  private static boolean closed = false;
  private static Createobject c = null;
  
  public static void main(String[] args) {

    int portNumber = 2222;
    String host = "localhost";

    if (args.length < 2) {
      System.out.println("Usage: java MultiThreadChatClient <host> <portNumber>\n"
              + "Now using host=" + host + ", portNumber=" + portNumber);
    } else {
      host = args[0];
      portNumber = Integer.valueOf(args[1]).intValue();
    }

    try {
      clientSocket = new Socket(host, portNumber);
      inputLine = new BufferedReader(new InputStreamReader(System.in));		// unlike Server now in Client side u have to take ip from keyboard
      os = new PrintStream(clientSocket.getOutputStream());				// writing to Server
      is = new DataInputStream(clientSocket.getInputStream());			// reading from Server
      
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host " + host);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to the host "
          + host);
    }

    if (clientSocket != null && os != null && is != null) {
      try {
    	c=new Createobject();
    	sendname();                                            //sends name of client to server
        new Thread(new MultiThreadChatClient()).start();// create new thread
        while (!closed) {		
        	
        	if(destination==null) {
        		setdestination();	                            // sends destination to server
        	}
        	else {
        		sendmsg();			                           // send msg to server while client is sending msgs is true 
        	}
        }

        os.close();
        is.close();
        clientSocket.close();
      } 
      catch (IOException e) {
        System.err.println("IOException:  " + e);
      }
    }
    
  }

  public void run() {			// this function is outside psvm but inside MultiThreadChatClient class ------------------ note
    String responseLine;        //read received msg

    try {
      while ((responseLine = is.readLine()) != null) {// this msg is from Server cuz 'is' is used for reading msg 
//      System.out.println("msg received at "+name);				
      
      
      String msg[] = {"","","",""};
      int j=0;
     for(int i=0;i<responseLine.length();i++) {
    	 if(String.valueOf(responseLine.charAt(i)).equals("|")) {
    		 j++;
    		 continue;
    	 }
    	 msg[j]=msg[j]+String.valueOf(responseLine.charAt(i));
     
      }
     
     
     
      if((destination!=null&&destination.equals(msg[3])&&name.equals(msg[1])&&(!name.equals(msg[3])))||name.equals(msg[3])) {
    	  System.out.println(msg[3]+">>"+msg[2]);	// print msg to console of client if their boths destination or name is same 
    	  c.cobj(responseLine,name,1); 
      }
      else {
    	  try {
//         System.out.println(msg[3]+">>"+msg[2]);	// print msg to console of client if their boths destination or name is same 
    	  c.cobj(responseLine,name,0); // write msg to json array
    	  }
    	  catch(Exception e) {
    		  e.printStackTrace();
    	  }
	  }
      
      
      
        if (responseLine.indexOf("* Bye") != -1)
          break;
      }
      
      
      closed = true;
    } 
    catch (IOException e) {
      System.err.println("IOException:  " + e);
    }

  }
  
  public static void establishconn() {
	  System.out.println("Select:\n1.group chat\n2.personal chat");
	  System.out.println("then Enter destination name:");
	try {
		choice = Integer.valueOf(inputLine.readLine().trim());
//		 System.out.println("Enter destination name");
		  destination = inputLine.readLine().trim();
		  
//			  System.out.println("char at 1 is "+Integer.valueOf(choice));
	} catch (IOException e) {
		e.printStackTrace();
	}
	  
  }
  
  
  public static String pack(String sendmsgtoserver ) {
	  
//		System.out.println(sendmsgtoserver);
		String packs=choice+"|"+destination+"|"+sendmsgtoserver+"|"+name;
		return packs;
  }
  
  
  
  public static void sendname() {
	System.out.println("Enter your name");
  	String sendmsgtoserver = null;
	try {
		sendmsgtoserver = inputLine.readLine().trim();
	} catch (IOException e) { 
		e.printStackTrace();
	}
	  name=sendmsgtoserver;
	  c.create(name);       //create json file for client
      os.println(sendmsgtoserver);// send msg to server while client is sending msgs is true 
  }
  
  
  public static void setdestination() {
	  establishconn();
      os.println(pack(name+"connected to"+destination));
      if(choice==1) {
      prevgroups.add(destination);
      }
      else if(choice==2) {
    	  System.out.println("Entered choice 2");
    	  try {
          prevdestination.add(destination);
    	  System.out.println("No Problem here"+prevdestination.get(0));
    	  }
    	  catch(Exception e) {
    		  e.printStackTrace();
    	  }

      }
	  System.out.println("Enter the message fom client");
  }
  
  
  public static void sendmsg() {
	  String sendmsgtoserver = null;
		try {
			sendmsgtoserver = inputLine.readLine().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(sendmsgtoserver.equals("--exit--")) {
			System.out.println("Your contacts:");
			System.out.println("personal chats:"+prevdestination);
			System.out.println("group chats:"+prevgroups);
			System.out.println("Enter\n1.contact name\n2.add new contact");
			String dest="";
			try {
				dest = inputLine.readLine().trim();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(dest.equals("2")) {
				setdestination();
			}
			else {
				if(prevdestination.indexOf(dest) != -1) {
					choice=2;
					destination=prevdestination.get(prevdestination.indexOf(dest));
				}
				else if(prevgroups.indexOf(dest) != -1) {
					choice=1;
					destination=prevgroups.get(prevgroups.indexOf(dest));
				}
				System.out.println("Enter your msg");
			}

		}
		else if(destination!=null) {
		   sendmsgtoserver=pack(sendmsgtoserver);
	       os.println(sendmsgtoserver);
		}
  }
  
}