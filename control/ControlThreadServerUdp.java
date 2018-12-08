package control;

import java.net.DatagramPacket;

import protocol.UdpServer;

/**
 * Classe ControlThreadServerUdp, traitement des requetes UDP.
 * @author OLIVIER Thomas
 *
 */
public class ControlThreadServerUdp extends Thread {
	
	private UdpServer udpServer;
	private ControlBank controlBank;
	
	/**
	 * Constructeur ControlThreadServerUdp.
	 * @param udpServer Gestion des sockets UDP.
	 * @param controlBank Banque.
	 */
	public ControlThreadServerUdp(UdpServer udpServer, ControlBank controlBank) {
		System.out.println(Thread.currentThread().getName() + " : New Thread Server UDP");
		this.udpServer = udpServer;
		this.controlBank = controlBank;
	}
	
	public void run() {
		System.out.println(Thread.currentThread().getName() + " is the thread of server UDP.");
		
		if ( this.udpServer.open() ) {
			while ( true ) {
				this.udpServer.readMessage();
				String query = this.udpServer.getMessageDatagram();
				System.out.println(Thread.currentThread().getName() + " - QUERY - " + query);
				String response = controlBank.process(query);
				System.out.println(Thread.currentThread().getName() + " - RESPONSE - " + response);
				this.udpServer.writeMessage(this.udpServer.getAddress(), this.udpServer.getPort(), response);
			}
		}
		
	}

}
