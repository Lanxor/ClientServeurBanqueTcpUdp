package protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe permettant a un client d'initialiser une connexion TCP et d'envoyer des messages.
 * @author OLIVIER Thomas
 * 
 */
public class TcpClient {

	private String hostname;
	private int port;
	private Tcp protocolTcp;
	
	/**
	 * Constructeur ClientTcp.
	 * @param hostname Adresse IP ou nom d'hote d'un serveur.
	 * @param port Port de destination d'un serveur.
	 */
	public TcpClient(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}
	
	/**
	 * Initialise une connexion client sur l'hote et le port definit.
	 * @return Renvoie true si la connexion du socket c'est bien passe, false sinon.
	 */
	public boolean connect() {
		try {
			Socket socket = new Socket(this.hostname, this.port);
			this.protocolTcp = new Tcp(socket);
		} catch(IOException e) {
			System.out.println("Error with open socket.\n" + e);
			return false;
		}
		return true;
	}
	
	/**
	 * Attend et lit un message sur la connexion TCP.
	 * @return Renvoie la chaine de caractere recu par la connexion TCP.
	 */
	public String readMessage() {
		return this.protocolTcp.readMessage();
	}
	
	/**
	 * Ecris un message sur la connexion TCP.
	 * @param message Chaine de caractere a envoye dans la connexion TCP.
	 * @return Renvoie true si l'action c'est effectue, false sinon.
	 */
	public boolean writeMessage(String message) {
		return this.protocolTcp.writeMessage(message);
	}
	
	/**
	 * Ferme la connexion TCP.
	 * @return Renvoie true si la fermeture du socket c'est bien passe, false sinon.
	 */
	public boolean close() {
		return this.protocolTcp.close();
	}
	
}
