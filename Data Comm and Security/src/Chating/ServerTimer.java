package Chating;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

public class ServerTimer extends TimerTask {

UDPServer us=new UDPServer();

	public void run() {
		for (int i = 0; i < UDPServer.timeClient.size(); i += 2)
		{
			Date date2=new Date();
			System.out.println("Time count:"+(date2.getTime()-Long.parseLong(UDPServer.timeClient.get(i + 1).toString())));
			//System.out.println(date1);
			//System.out.println(date1.getTime());
			if ((date2.getTime()-Long.parseLong(UDPServer.timeClient.get(i + 1).toString()))>6000) {
				UDPServer.timeClient.remove(i);
				UDPServer.clientList.remove(i);
				i--;
				
				UDPServer.clientList.remove(i+1);
				UDPServer.timeClient.remove(i+1);
				i--;
			}
			us.clientListRenew();
		}
		System.out.println("Server Check every 60 Seconds!");
	}

}
