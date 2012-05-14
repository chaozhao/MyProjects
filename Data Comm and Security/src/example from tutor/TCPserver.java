import java.net.*;
import java.io.*;
public class TCPserver {
	public static void main(String[] args) {
	      try {
			ServerSocket serverSocket = new ServerSocket(9000);//@
			Socket clientSocket = serverSocket.accept();//@
			InputStream in = clientSocket.getInputStream();//
			OutputStream out = clientSocket.getOutputStream();//
			InputStreamReader inr = new InputStreamReader(in);//
			BufferedReader br = new BufferedReader(inr);//
			String message = br.readLine();//
			System.out.println("Server start");//
			while(true)
			{
			System.out.println(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
