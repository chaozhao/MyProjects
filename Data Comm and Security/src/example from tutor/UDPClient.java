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

public class UDPClient implements ActionListener {

	JLabel text, clicked;
	JButton button;
	JPanel panel;
	JTextField textField;
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	JFrame frame;
	DatagramSocket ds;

	public static void main(String[] args) {
		UDPClient u1 = new UDPClient();
		u1.makeFrame();
		u1.openSocket();
		while (true) {
			u1.receiveMessage();
		}

	}

	public void makeFrame() {
		JFrame frame = new JFrame();
		frame.setTitle("Client Program");
		text = new JLabel("Text to send over socket:");
		textField = new JTextField(20);
		button = new JButton("Click Me");
		button.addActionListener(this);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.white);
		frame.getContentPane().add(panel);
		panel.add("North", text);
		panel.add("Center", textField);
		panel.add("South", button);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		frame.pack();
		frame.setVisible(true);

	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();

		if (source == button) {
			// Send data over socket
			String text = textField.getText();
			sendMessage(text);
		}
	}

	public void sendMessage(String message) {
		try {
			InetSocketAddress sa = new InetSocketAddress("136.186.201.91", 8000);
			byte[] data = message.getBytes();
			DatagramPacket dp = new DatagramPacket(data, data.length, sa);
			DatagramSocket udpsocket = new DatagramSocket();
			udpsocket.send(dp);

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void receiveMessage() {
		try {
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			ds.receive(dp);
			int clientport = dp.getPort();
			String message = new String(buf, 0, dp.getLength());
			textField.setText(message);
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

}
