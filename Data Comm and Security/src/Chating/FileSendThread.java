package Chating;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.SwingUtilities;

public class FileSendThread extends Thread {

	String fileName;
	Socket connectionsocket;
	ServerSocket mainSocket;
	long fileLength;
	boolean isConnected=false;

	public FileSendThread(String fileName, long fileLength) {
		this.fileLength = fileLength;
		this.fileName = fileName;
		try{
		mainSocket = new ServerSocket(8989);
		}catch(Exception e){
			System.out.println(e);
		}

	}



	public void run() {
			if(isConnected==false&&!mainSocket.isClosed()){
				try {

					
						connectionsocket = mainSocket.accept();
						isConnected = true;
					
				} catch (Exception e) {
					System.out.println("Open Socket Error in FileSend");
				}
			}
			if(isConnected==true){
			int passed = 0;
			try {
				File file1 = new File(fileName);
				FileInputStream fos = new FileInputStream(file1);
				OutputStream netOut = connectionsocket.getOutputStream();
				DataOutputStream doc = new DataOutputStream(
						new BufferedOutputStream(netOut));
				doc.writeLong(fileLength);
				doc.flush();

				byte[] buf = new byte[4096];
				int num = fos.read(buf);

				while (num != (-1)) {
					passed += num;
					doc.write(buf, 0, num);
					doc.flush();
					num = fos.read(buf);

					System.out.println(passed);
					final int x = (int) (100 * (passed / (double) fileLength));

					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							chat.jProgressBar1.setValue(x);
							chat.jProgressBar1.setString(String.valueOf(x)
									+ "%");

						}
					});
				}
				fos.close();
				doc.close();
				mainSocket.close();
			} catch (Exception ex) {
				System.out.println(ex);
			}
			isConnected=false;
		}
	}
	
}
