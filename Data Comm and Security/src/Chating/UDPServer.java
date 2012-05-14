package Chating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class UDPServer{

	private JPanel filePanel;
	private JLabel label;
	private JFrame frame;
	public static ArrayList clientList = new ArrayList();;
	DatagramSocket ds;
	String list;
	public static ArrayList timeClient = new ArrayList();

	public static void main(String[] args) {
		UDPServer u1 = new UDPServer();
		u1.makeFrame();
		u1.openSocket();
		ServerTimer st = new ServerTimer();
		Timer t = new Timer();
		t.schedule(st, 2000, 6000);

		while (true) {
			u1.getClient();

		}

	}

	public void getClient() {
		try {
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			ds.receive(dp);
			int clientport = dp.getPort();
			InetAddress clientAddress = dp.getAddress();
			String message = new String(buf, 0, dp.getLength());
			int a = 0;
			for (int i = 0; i < clientList.size(); i += 2) {
				if ((clientList.get(i).toString().trim()).equals(clientAddress
						.toString().trim())) {
					clientList.set(i + 1, message);
					timeClient.set(i + 1,Long.toString(new Date().getTime()));
					a = 1;
				}
			}
			if (a != 1) {
				clientList.add(clientAddress);
				clientList.add(message);
				timeClient.add(clientAddress);
				timeClient.add(Long.toString(new Date().getTime()));
			}
			list = "";
			for (int i = 0; i < clientList.size(); i++) {
				list = list + clientList.get(i);
				list += ";";

			}
			byte[] listBuf = list.getBytes();
			DatagramPacket packet = new DatagramPacket(listBuf, listBuf.length,
					clientAddress, clientport);
			ds.send(packet);
			clientListRenew();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openSocket() {
		try {
			ds = new DatagramSocket(3050);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clientListRenew() {
		try {
			DatagramPacket packet2;
			for (int i = 0; i < clientList.size() / 2; i++) {
				if (list != null) {
					byte[] listBuf = list.getBytes();
					packet2 = new DatagramPacket(listBuf, listBuf.length,
							(InetAddress) clientList.get(i * 2), 3026);
					ds.send(packet2);
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void makeFrame(){
		frame=new JFrame("Server");
		frame.setLayout(new BorderLayout());

		label = new JLabel("Server Started!");

		filePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		filePanel.add(label);
		frame.getContentPane().add(filePanel, BorderLayout.NORTH);
		frame.setBounds(100, 100, 300, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
