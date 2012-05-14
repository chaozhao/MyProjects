package Chating;

import java.net.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UDPClient {

	JLabel text, clicked;
	JButton button;
	JPanel panel;
	JTextField textField;
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	JFrame frame;
	DatagramSocket ds;

	public void sendMessage(String IP, int port, String message) {
		try {
			System.out.println("herere: "+IP+" port: "+port+" message:"+message);
			InetSocketAddress sa = new InetSocketAddress(IP, port);
			byte[] data = message.getBytes();
			DatagramPacket dp = new DatagramPacket(data, data.length, sa);
			DatagramSocket udpsocket = new DatagramSocket();
			udpsocket.send(dp);
			udpsocket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String receiveMessage() {
		try {
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			ds.receive(dp);
			int clientport = dp.getPort();
			String message = new String(buf, 0, dp.getLength());
			return message;
		} catch (SocketException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int openSocket() {
		int i = 2000;

		for (; i < 9000; i++) {
			try {
				ds = new DatagramSocket(i);
				System.out.println("open port :"+i);
				break;
			} catch (SocketException e) {
			} catch (IOException e) {
			} catch (Exception e4) {
				System.out.println("Try to open port" + i + " fail!");
			}
		}
		if (i < 9000)
			return i;
		else
			
		return -1;

	}

}
