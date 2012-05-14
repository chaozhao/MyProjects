import java.io.*;
import java.net.*;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Client Start");
			Socket clientSocket = new Socket("localhost", 6500);
			
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
			double side = 200.00;
			output.writeDouble(side);
			
		} catch (IOException e) {
			System.out.println("Client: Accept failed: 6500");
			System.exit(-1);
		}
	

		

	}
}