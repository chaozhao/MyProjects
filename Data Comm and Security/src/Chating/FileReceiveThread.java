package Chating;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.Socket;

import javax.swing.SwingUtilities;

public class FileReceiveThread extends Thread {
	private Socket socket;
	private boolean isConnected;
	private String IP = "";
	private String fileName = "";

	public FileReceiveThread(String IP, String fileName) {
		this.IP = IP;
		this.fileName = fileName;
		getConnection();
	}

	public void getConnection() {
		try {
			socket = new Socket(IP, 8989);
			isConnected = true;
		} catch (Exception e) { 
			System.out.println(e);
			System.out.println("Connect error in FileReceiveThread");
		}
	}

	public void run() {
		if (isConnected == true) {
			try {
				File file2 = new File("/"+fileName);
				file2.createNewFile();
				RandomAccessFile raf = new RandomAccessFile(file2, "rw");

				InputStream netIn = socket.getInputStream();
				DataInputStream in = new DataInputStream(
						new BufferedInputStream(netIn));
				long filelength = in.readLong();
				byte[] buf = new byte[4096];
				int num = in.read(buf);
				int passed = 0;
				while (num != (-1)) {
					raf.write(buf, 0, num);
					raf.skipBytes(num);
					passed += num;
					num = in.read(buf);

					final int x = (int) (100 * (passed / (double) filelength));

					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							chat.jProgressBar0.setValue(x);
							chat.jProgressBar0.setString(String.valueOf(x)
									+ "%");

						}
					});

				}

				in.close();
				raf.close();
				socket.close();
			} catch (Exception ex) {
				System.out.println(ex);
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			isConnected = false;
		}
	}
}
