package view;

import java.util.Scanner;

import protocol.TcpClient;

/**
 * Classe principale d'un client TCP.
 * @author OLIVIER Thomas
 *
 */
public class ClientTcp {

	public static void main(String[] args) {
		if ( args.length != 2 ) {
			System.out.println("Error Usage : address port");
			return;
		}
		
		TcpClient tcpClient = new TcpClient(args[0], Integer.parseInt(args[1]));
		if ( !tcpClient.connect() ) {
			return;
		}
		
		String message;
		Scanner input = new Scanner(System.in);
		System.out.println("Send an empty message to close connexion.");
		System.out.print("Your command : ");
		message = input.nextLine();
		while ( !message.equals("") ) {
			tcpClient.writeMessage(message);
			message = tcpClient.readMessage();
			if ( message != null ) {
				System.out.println(message);
			}
			System.out.print("Your command : ");
			message = input.nextLine();
		}
		
		tcpClient.close();
	}

}
