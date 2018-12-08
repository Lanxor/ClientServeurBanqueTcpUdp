package protocol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Classe permettant a un client de pouvoir envoyer et recevoir des messages UDP.
 * @author OLIVIER Thomas
 *
 */
public class UdpClient {
	
	private Udp udpClient;
	private byte[] buffer;
	private DatagramPacket datagramPacket;
	
	/**
	 * Constructeur UdpClient.
	 */
	public UdpClient() {
		this.buffer = new byte[1024];
	}
	
	/**
	 * Ouvre un port UDP et initialise le buffer.
	 * @return Renvoie true si l'operation c'est effectue, false sinon.
	 */
	public boolean open() {
		try {
			DatagramSocket socket = new DatagramSocket();
			this.udpClient = new Udp(socket);
			this.datagramPacket = new DatagramPacket(this.buffer, this.buffer.length);
		} catch(IOException e) {
			System.out.println("Error with open socket.\n" + e);
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
	 * Attend et lit un message UDP.
	 */
	public void readMessage() {
		this.datagramPacket.setData(this.buffer);
		this.datagramPacket = this.udpClient.readMessage(this.datagramPacket);
	}
	
	/**
	 * Envoie un message a une adresse de destination et d'un port de destination avec une chaine de caractere.
	 * @param hostname Adresse de destination du message UDP.
	 * @param port Numero de port du message UDP.
	 * @param message Chaine de caractere du message UDP.
	 * @return Renvoie true si le message c'est envoye, false sinon.
	 */
	public boolean writeMessage(String hostname, int port, String message) {
		try {
			InetAddress address = InetAddress.getByName(hostname);
			this.datagramPacket.setAddress(address);
			this.datagramPacket.setPort(port);
			this.datagramPacket.setData(message.getBytes(), 0, message.getBytes().length);
		} catch (IOException e) {
			System.out.println("Error create packet : " + e.getMessage());
			return false;
		}
		return this.udpClient.writeMessage(this.datagramPacket);
	}
	
	/**
	 * Ferme le socket UDP.
	 */
	public void close() {
		this.udpClient.close();
	}

}
