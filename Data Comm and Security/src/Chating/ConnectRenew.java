package Chating;

import java.net.*;

import java.io.*;




public class ConnectRenew {

	Socket socket = null;

	DatagramSocket ds ; 

	DatagramSocket udpsocket;
	DatagramPacket dp; 

	public void openSocket() {
		try {
			
			ds = new DatagramSocket(3026);
		} catch (SocketException e) {
			ds.close();
			e.printStackTrace();
		} catch (IOException e) {
			ds.close();
			e.printStackTrace();
		}
	}

	public String clientListRenew() {
		try { 
			byte  [ ] buf = new byte [1024];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			ds.receive(packet); 
			String received = new String(packet.getData(), 0, packet.getLength());
			return received;
		} catch (SocketException e) {
			ds.close();
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			ds.close();
			e.printStackTrace();
			return null;
		}
	}

	public void clientTimeCheck(String serverIP,String port) {
		try {
			byte[] data = port.getBytes();
			InetSocketAddress sa = new InetSocketAddress(serverIP, 3050);
			dp = new DatagramPacket(data, data.length, sa);
			udpsocket = new DatagramSocket();
			udpsocket.send(dp);

		} catch (SocketException e) {
			ds.close();
			udpsocket.close();
			e.printStackTrace();
		} catch (IOException e) {
			ds.close();
			udpsocket.close();
			e.printStackTrace();
		}
	}

}
