package view;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import protocol.TcpClient;
import protocol.Udp;
import protocol.UdpClient;

/**
 * Classe principale d'un client UDP.
 * @author OLIVIER Thomas
 *
 */
public class ClientUdp {

	public static void main(String[] args) {
		
		if ( args.length != 2 ) {
			System.out.println("Error Usage : address port");
			return;
		}
		String hostname = args[0];
		int port = Integer.parseInt(args[1]);
		
		UdpClient udpClient = new UdpClient();
		if ( !udpClient.open() ) {
			return;
		}
		
		String message;
		Scanner input = new Scanner(System.in);
		System.out.println("Send an empty message to close connexion.");
		System.out.print("Your command : ");
		message = input.nextLine();
		while ( !message.equals("") ) {
			udpClient.writeMessage(hostname, port, message);
			udpClient.readMessage();
			System.out.println(udpClient.getMessageDatagram());
			System.out.print("Your command : ");
			message = input.nextLine();
		}
		
		udpClient.close();
		
	}

}
