package Chating;

import java.util.StringTokenizer;

import javax.swing.JTextPane;

class MessReceiveThread extends Thread

{
	private UDPClient client;
	private String message = "";
	public static int port=0;

	public MessReceiveThread(UDPClient client) {
		this.client = client;

	}

	public void run() {
		while (true) {
			message = client.receiveMessage();
			StringTokenizer str = new StringTokenizer(message, ";");
			/*str is a arraylist*/
			String nextToken = "";
			String[] messageType=new String[2];
			for (int i = 0; i < 2; i++) 
			{
				nextToken = str.nextToken();
				messageType[i]=nextToken;
			}
			
			if(messageType[0].equals("message"))
			{
			chat.jTextPane0.setText(chat.jTextPane0.getText() + messageType[1] + '\r'
					+ '\n');}
			else if(messageType[0].equals("file"))
			{
				chat.jLabel5.setText(messageType[1]);
				port=Integer.parseInt(str.nextToken());
			}
			else if(messageType[0].equals("send"))
			{
				chat.jLabel10.setText(messageType[1]);
			}
			
		}
		/*
		 * try { sleep(10000); } catch (InterruptedException e) { return; }
		 */

	}
}