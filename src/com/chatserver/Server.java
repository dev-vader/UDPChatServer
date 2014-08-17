package com.chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

	private final static int PORT = 40123;
	private final static int MAX_PACKET_SIZE = 65507;
	private static boolean connected = true;
	
	public static void main(String[] args) throws IOException{
		
		DatagramSocket socket = new DatagramSocket(PORT);

		byte[] buffer = new byte[MAX_PACKET_SIZE];
		
		DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
		
		System.out.println("Server up " + System.currentTimeMillis());
		while(connected){
			
			socket.receive(inPacket);
			
			String receivedMessage = new String(inPacket.getData(), 0 , inPacket.getLength()) + " From Address: " +
			inPacket.getAddress() + " , PORT: " + inPacket.getPort();
			
			System.out.println(receivedMessage);
			
			BufferedReader writeMessage = new BufferedReader(new InputStreamReader(System.in));
			String handledMessage = writeMessage.readLine();
			buffer = handledMessage.getBytes();
	
			
			DatagramPacket outPacket = new DatagramPacket(buffer, buffer.length, inPacket.getAddress() , 
					inPacket.getPort());
			socket.send(outPacket);
		}
	}
}
