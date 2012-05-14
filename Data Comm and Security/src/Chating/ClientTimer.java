package Chating;

import java.util.StringTokenizer;
import java.util.TimerTask;

public class ClientTimer extends TimerTask 
{
	ConnectRenew renew;
	private String message;
	private int port = 0;

	public ClientTimer(int port,ConnectRenew renew) {
		this.renew=renew;
		this.port = port;
}
	public void run() {
		
		System.out.println("Client sign in every 60 seconds!");
			
			renew.clientTimeCheck(chat.serverIP, Integer.toString(port));
				
			

	}

}
