package control;

import java.net.Socket;

import protocol.Tcp;
import protocol.TcpServer;

/**
 * Classe ControlThreadServerTcp, traitement des connexions TCP.
 * @author OLIVIER Thomas
 *
 */
public class ControlThreadServerTcp extends Thread {

	private TcpServer tcpServer;
	private ControlBank controlBank;

	/**
	 * Constructeur ControlThreadServerTcp.
	 * @param tcpServer Connexion tcpServer.
	 * @param controlBank Banque.
	 */
	public ControlThreadServerTcp(TcpServer tcpServer, ControlBank controlBank) {
		System.out.println(Thread.currentThread().getName() + " : New Thread Server TCP");
		this.tcpServer = tcpServer;
		this.controlBank = controlBank;
	}
	
	public void run() {
		System.out.println(Thread.currentThread().getName() + " is the thread of server TCP.");
		if ( !this.tcpServer.open() ) {
			return;
		}
		while ( true ) {
			Socket socket = this.tcpServer.accept();
			Tcp connexionTcp = new Tcp(socket);
			ControlThreadTcp controlThreadTcp = new ControlThreadTcp(connexionTcp, controlBank);
			controlThreadTcp.start();
		}
	}
	
}
