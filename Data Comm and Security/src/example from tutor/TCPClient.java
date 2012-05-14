/**
 * We need to import classes from two packages while writing TCP programs
 * java.io and java.net
 * 
 *  A TCP connection is stream based. We don't have to worry about creating packets ourselves
 *  In UDP based programs, however, we have to create packets ourselves and send and receive them individually
 *   
 */
import java.io.*;
import java.net.*;
import java.util.*;
public class TCPClient {
	public static void main(String[] args) {
             try {
            	Socket clientSocket = new Socket("136.186.201.91", 9000);
				OutputStream out = clientSocket.getOutputStream();
				OutputStreamWriter writer = new OutputStreamWriter(out);
				PrintWriter pw = new PrintWriter(writer, true);
				System.out.println("Please input message");
				String Message;
				Scanner sc = new Scanner(System.in);
				Message = sc.next();
				pw.println(Message);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
	}

}


/**
Because the constructor of Socket throws exception "UnknownHostException" and
the soc.getOuputStream function throws IOException, we have to enclose these statements in try
block which is followed by respective catch clauses

*/