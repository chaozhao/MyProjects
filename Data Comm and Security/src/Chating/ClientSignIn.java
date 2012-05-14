package Chating;

import java.net.*;

import java.io.*;




public class ClientSignIn {

	Socket socket = null;



	DatagramSocket udpsocket;
	DatagramPacket dp;



	public String getClientList() {
		try {
			byte[] buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length, dp
					.getAddress(), dp.getPort());
			udpsocket.receive(packet);
			String received = new String(packet.getData(), 0, packet.getLength());
			udpsocket.close();
			return received;
		} catch (SocketException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void clientSignIn(String serverIP,int port) {
		try {
			byte[] data = Integer.toString(port).getBytes();
			InetSocketAddress sa = new InetSocketAddress(serverIP, 3050);
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
