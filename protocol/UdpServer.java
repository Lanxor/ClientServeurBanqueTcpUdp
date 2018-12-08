package protocol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Classe permettant a un serveur d'ecouter sur un port et d'envoyer des messages UDP.
 * @author OLIVIER Thomas
 *
 */
public class UdpServer {
	
	private int port;
	private Udp udpServer;
	private byte[] buffer;
	private DatagramPacket datagramPacket;
	
	/**
	 * Constructeur UdpServer.
	 * @param port Port UDP d'ecoute. 
	 */
	public UdpServer(int port) {
		this.port = port;
		this.buffer = new byte[1024];
	}
	
	/**
	 * Ouvre le port UDP et initialise le buffer.
	 * @return Renvoie true si l'operation c'est effectue, false sinon.
	 */
	public boolean open() {
		try {
			DatagramSocket socket = new DatagramSocket(this.port);
			this.udpServer = new Udp(socket);
			this.datagramPacket = new DatagramPacket(this.buffer, this.buffer.length);
		} catch(IOException e) {
			System.out.println("Error open socket : " + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Recupere la chaine de caractere du dernier message UDP lu.
	 * @return Renvoie la chaine de caractere du dernier message UDP lu.
	 */
	public String getMessageDatagram() {
		return new String(this.buffer, 0, this.datagramPacket.getLength());
	}
	
	/**
	 * Recupere l'adresse du dernier message UDP lu.
	 * @return Renvoie l'adresse du dernier message UDP lu.
	 */
	public InetAddress getAddress() {
		return this.datagramPacket.getAddress();
	}
	
	/**
	 * Recupere le numero de port du dernier message UDP lu.
	 * @return Renvoie le numero de port du dernier message UDP lu.
	 */
	public int getPort() {
		return this.datagramPacket.getPort();
	}
	
	/**
	 * Attend et lit un message UDP.
	 */
	public void readMessage() {
		this.datagramPacket.setData(buffer);
		this.datagramPacket = this.udpServer.readMessage(this.datagramPacket);
	}
	
	/**
	 * Construit une message UDP a destination d'une adresse et d'un numero de port avec une chaine de caractere.
	 * @param address L'adresse destination du message UDP.
	 * @param port Le numero de port destination du message UDP.
	 * @param message Chaine de caractere du message UDP.
	 * @return Renvoie true si le message c'est envoye, false sinon.
	 */
	public boolean writeMessage(InetAddress address, int port, String message) {
		this.datagramPacket.setAddress(address);
		this.datagramPacket.setPort(port);
		this.datagramPacket.setData(message.getBytes(), 0, message.getBytes().length);
		return this.udpServer.writeMessage(datagramPacket);
	}
	
	/**
	 * Ferme le socket UDP.
	 */
	public void close() {
		this.udpServer.close();
	}

}
