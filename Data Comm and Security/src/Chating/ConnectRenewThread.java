package Chating;

import java.util.StringTokenizer;

public class ConnectRenewThread extends Thread 
{
	ConnectRenew renew;
	private String message;

	public ConnectRenewThread(ConnectRenew renew)
	{
		this.renew=renew;
	}
	
	public void run() 
	{
		while (true) {
			message = null;
			message = renew.clientListRenew();

			if (message != null) 
			{
				chat.listModel.removeAllElements();
				chat.clientArrayList.clear();
				StringTokenizer str = new StringTokenizer(message, ";");
				int j = str.countTokens();
				String nextToken = "";
				for (int i = 0; i < j; i++) {
					nextToken = str.nextToken();
					nextToken = nextToken.replace("/", "");
					chat.clientArrayList.add(nextToken);
				}

				for (int i = 0; i < chat.clientArrayList.size(); i += 2) 
				{
					chat.listModel.addElement(chat.clientArrayList.get(i));
				}
			}
		}
	}

}
