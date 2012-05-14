package Chating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UDPServer {

	ArrayList clientList = new ArrayList();;
	DatagramSocket ds;

	public static void main(String[] args) {
		UDPServer u1 = new UDPServer();
		u1.openSocket();
		while (true) {
			u1.getClient();

			u1.getArrayList();
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
			for (int i = 0; i < clientList.size(); i++) {
				if ((clientList.get(i).toString().trim()).equals(clientAddress.toString().trim())) {
					a = 1;
				}
			}
			if (a != 1) {
				clientList.add(clientAddress);
				clientList.add(message);
			}
			String list = "";
			for (int i = 0; i < clientList.size(); i++) {
				list = list + clientList.get(i);
				list += ";";

			}
			byte[] listBuf = list.getBytes();
			DatagramPacket packet = new DatagramPacket(listBuf, listBuf.length,
					clientAddress, clientport);
			ds.send(packet);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openSocket() {
		try {
			ds = new DatagramSocket(8000);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getArrayList() {
		for (int i = 0; i < clientList.size(); i++) {
			System.out.println(clientList.get(i));

		}
	}

}
