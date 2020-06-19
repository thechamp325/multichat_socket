package audiotransfer;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Vector;
class Read
{
	Scanner sc =new Scanner(System.in);
	  String str=null;
	  int x; 
	  Socket socket;
	  Socket destisocket;
	  String name;
	  String desti;
	  sockets obj;
	   Read(Socket socket1,String name1,String desti1) throws IOException
	   {
		   System.out.println("in Read constructor");
		   socket=socket1;
		   name=name1;
		   desti=desti1;
		   Thread t=new Thread()
				   {
			   			public void run()
			   			{
			   				try {
								check();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			   			}
				   };
				   t.start();   
	   }
	   public void check() throws IOException
	   {
		   System.out.println("in check");
		   obj=new sockets();
		   while(!(obj.name.contains(desti)));
		   if(obj.name.contains(desti))
		   {
		   int x= obj.name.indexOf(desti);
			 destisocket=(Socket)obj.socket.get(x);
			System.out.println("In_srever_write");
			read();
		   }
		   System.out.println("end of check");
	   }
	public void read() throws IOException
		{
		System.out.println("in read function");
		 if(obj.name.contains(desti))
		 {
			Thread t=new Thread()
			{	
				public void run()
				{
					InputStream input;
					try {
						input = socket.getInputStream();
						DataInputStream	dataIn = new DataInputStream(input);
						 int bytesR = 0;
					        byte[] bytes = new byte[1024];
					        while(bytesR != -1)
					        {
					            try{
					               	
					            	bytesR = dataIn.read(bytes, 0, bytes.length);
					            	new Write(bytes,bytesR,destisocket);
					            	}
					            catch (IOException e)
					            {
					            	e.printStackTrace();
			
					            }
					        }
					
					
						} catch (Exception e) {
							System.out.println("error1");
							e.printStackTrace();
							System.exit(0);
						}
					} 
				//}
			};         
			t.start();
}
		
	 else
	 {
		 str="client not available";
		 System.out.println(str);
		 new Write(str,socket);
	 }
}

   class Write
   { 
	   
	   Scanner sc =new Scanner(System.in);
	   String str=null;
	   Socket socket;
	   byte[] bytes;
	   int bytesR;
	  Write(String str1,Socket socket1) throws IOException
	  {
		  str=str1;
		  socket=socket1; 
	  }
	   Write(byte[] bytes1,int bytesR1,Socket destisocket)
	   {
		   System.out.println("in Write constructor");
		   bytes=bytes1;
		   bytesR=bytesR1;
		   socket=destisocket;  
		   write(bytes,bytesR);
	   }
	  
	   void write(byte[] bytes1,int bytesR1 )
		{
		   System.out.println("in write function");
			Thread t=new Thread() {
				public void run()
				{	
					OutputStream output;
					//InputStream input;
					try {
						output = socket.getOutputStream();
						DataOutputStream dataOut = new DataOutputStream(output);
						 try{
							 System.out.println("from_srever_write:-"+bytesR1);
							 dataOut.write(bytes1, 0,bytesR1);
							 }
						 catch (Exception e)
						 {
							 e.printStackTrace();
						 }
			
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("error2");
						e.printStackTrace();
						System.exit(0);
					}//bytes
					}
			};
		t.start();
	}	   
   }
}
   class sockets extends Thread
   {
	  static Vector <Socket>socket=new Vector<Socket>();
	  static Vector <String>name=new Vector<String>();
	  static Vector <String>type=new Vector<String>();
	  static Vector <String>desti=new Vector<String>();
	  static Vector <Vector>group=new Vector<Vector>();
		ServerSocket Ssocket;
		sockets()
		{
			
		}
		sockets(ServerSocket socket)
		{
			Ssocket=socket;
		}
		public void run()
		{
		for(int i=0;true;i++)
		{
			Socket socket1;
			try {
				System.out.println("in sockets run");
			socket.add(Ssocket.accept());
			System.out.println(i);
			socket1=(Socket)socket.get(i);
			String s=(new BufferedReader(new InputStreamReader(socket1.getInputStream())).readLine());
			String a[]=s.split("@");
			name.add(a[0]);
			desti.add(a[1]);
			System.out.println((String)name.get(i)+" is connected");
			new Read((Socket)socket.get(i),(String)name.get(i),(String)desti.get(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
   }
class serverat
{
	
	public static void main(String args[]) throws IOException 
	{
		System.out.println("in serverat");
		Scanner sc=new Scanner(System.in);
		ServerSocket Ssocket;   
		Ssocket=new ServerSocket(9999);
		new sockets(Ssocket).start();
	}	
}