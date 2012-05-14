/*TCP*/
import java.io.*;
import java.net.*;

public class Server{

	public static void main(String[] args) {
		
		ServerSocket serverSocket;
		ServerSocket get_online;
		Socket Connection = null;
		Socket Connection_getonline = null;
		 DataInputStream input;
		 DataOutputStream output;
		try {
			System.out.println("Server Start");
			
			serverSocket = new ServerSocket(6500);/* for connection peers:TCP*/
			get_online = new ServerSocket(7000);/* for get online client*/
			
			Connection = serverSocket.accept();
			Connection_getonline = serverSocket.accept();		 
			
			input = new DataInputStream(Connection.getInputStream());
		    output = new DataOutputStream(Connection.getOutputStream());
		    
		    Double message = input.readDouble();
		    System.out.println(message);
		    
		} catch (IOException e)
		{
			System.out.println("Errors");
		    System.exit(-1);
		}
		
	}




}