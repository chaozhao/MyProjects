package Chating;

import java.net.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientSignIn {
	JLabel text, clicked;
	JButton button;
	JPanel panel;
	JTextArea textArea;
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	JFrame frame;
	DatagramSocket udpsocket;
	DatagramPacket dp;

	public static void main(String[] args) {
		ClientSignIn c = new ClientSignIn();
		c.clientSignIn();
		c.makeFrame();
		c.getClientList();
	}

	public void makeFrame() {
		JFrame frame = new JFrame();
		frame.setTitle("Client Program");
		text = new JLabel("Text to send over socket:");
		textArea = new JTextArea(20, 20);
		button = new JButton("Click Me");

		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.white);
		frame.getContentPane().add(panel);
		panel.add("North", text);
		panel.add("Center", textArea);
		panel.add("South", button);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		frame.pack();
		frame.setVisible(true);

	}

	public void getClientList() {
		try {
			byte[] buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length, dp
					.getAddress(), dp.getPort());
			udpsocket.receive(packet);
			String received = new String(packet.getData(), 0, packet.getLength());
			textArea.setText(received);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clientSignIn() {
		try {
			byte[] data = "8000".getBytes();
			InetSocketAddress sa = new InetSocketAddress("136.186.202.48", 8000);
			dp = new DatagramPacket(data, data.length, sa);
			udpsocket = new DatagramSocket();
			udpsocket.send(dp);

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
