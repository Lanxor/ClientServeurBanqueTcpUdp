package protocol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import control.ControlBank;

/**
 * Classe ServerTcp
 * @author OLIVIER Thomas
 *
 */
public class TcpServer {

	private int port;
	/**
	 * @see java.net.ServerSocket
	 */
	private ServerSocket socketListen;
	
	/**
	 * Constructeur ServerTcp.
	 * @param port Numero de port d'ecoute du serveur.
	 */
	public TcpServer(int port) {
		this.port = port;
	}
	
	/**
	 * Initilise le serveur TCP sur le port renseigne.
	 * @return Renvoie true si la fermeture du socket c'est bien passe, false sinon.
	 */
	public boolean open() {
		try {
			this.socketListen = new ServerSocket(this.port);
		} catch(IOException e) {
			System.out.println("Error with open socket.\n" + e);
			return false;
		}
		return true;
	}
	
	/**
	 * Accepte une connexion au serveur et renvoie le socket.
	 * @return Renvoie le socket TCP.
	 */
	public Socket accept() {
		Socket socket = null;
		try {
			socket = this.socketListen.accept();
		} catch(IOException e) {
			System.out.println("Error with socket accept server : " + e.getMessage());
		}
		return socket;
	}
	
	/**
	 * Ferme le socket server.
	 * @return Renvoie true si la fermeture du socket c'est bien passe, false sinon.
	 */
	public boolean close() {
		try {
			this.socketListen.close();
		} catch (IOException e) {
			System.out.println("Error close socket server : " + e.getMessage());
			return false;
		}
		return true;
	}
	
}
