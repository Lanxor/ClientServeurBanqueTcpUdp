package protocol;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Classe udp
 * @author OLIVIER Thoams
 *
 */
public class Udp {
	
	private DatagramSocket socket;
	
	/**
	 * Constructeur Udp.
	 * @param socket Socket UDP.
	 */
	public Udp(DatagramSocket socket) {
		this.socket = socket;
	}
	
	/**
	 * Attend un message UDP sur le socket de la classe et le renvoie.
	 * @param datagramPacket Message dans lequel on ecris le message recu.
	 * @return Renvoie un message UDP recus.
	 */
	public DatagramPacket readMessage(DatagramPacket datagramPacket) {
		try {
			this.socket.receive(datagramPacket);
			return datagramPacket;
		} catch(IOException e) {
			System.out.println("Error reading : " + e.getMessage());
		}
		return null;
	}
	
	/**
	 * Envoie un message UDP sur le socket de la classe et renvoie si l'operation c'est bien passe.
	 * @param datagramPacket Message a envoyer.
	 * @return Renvoie true si l'envoie du message sur le socket c'est effectue, false sinon.
	 */
	public boolean writeMessage(DatagramPacket datagramPacket) {
		try {
			this.socket.send(datagramPacket);
		} catch(IOException e) {
			System.out.println("Error writing : " + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Ferme le socket UDP.
	 */
	public void close() {
		this.socket.close();
	}

}
