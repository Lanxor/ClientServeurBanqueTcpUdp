package protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Classe de gestion des socket TCP.
 * @author OLIVIER Thomas
 *
 */
public class Tcp {
	
	private Socket socket;
	
	/**
	 * Constructeur protocol.Tcp;
	 * @param socket Socket de la connexion TCP.
	 */
	public Tcp(Socket socket) {
		this.socket = socket;
	}
	
	/**
	 * Lit une chaine de caractere sur le socket du client.
	 * @return Renvoie une chaine de caractere recupere dans le socket du client, null si une erreur.
	 */
	public String readMessage() {
		String message = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			message = reader.readLine();
		} catch(IOException e) {
			System.out.println("Error reading : " + e.getMessage());
			return null;
		}
		return message;
	}
	
	/**
	 * Envoie une chaine de caractere sur le socket du client.
	 * @param message Chaine de caractere a envoye dans le socket du client.
	 * @return Renvoie true si le message a ete envoye, false sinon.
	 */
	public boolean writeMessage(String message) {
		try {
			PrintStream pStream = new PrintStream(this.socket.getOutputStream());
			pStream.println(message);
		} catch(IOException e) {
			System.out.println("Error wrinting : " + e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean isClose() {
		return this.socket.isClosed();
	}
	
	/**
	 * Ferme le socket.
	 * @return Renvoie true si la fermeture du socket c'est bien passe, false sinon.
	 */
	public boolean close() {
		try {
			this.socket.close();
		} catch (IOException e) {
			System.out.println("Error close socket : " + e.getMessage());
			return false;
		}
		return true;
	}
}
