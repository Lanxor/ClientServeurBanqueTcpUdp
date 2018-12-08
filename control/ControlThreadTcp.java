package control;

import protocol.Tcp;

/**
 * Classe ControlThreadTcp, traitement d'une connexion TCP par thread.
 * @author OLIVIER Thomas
 *
 */
public class ControlThreadTcp extends Thread {
	
	private Tcp connexionTcp;
	private ControlBank controlBank;
	
	/**
	 * Constructeur ControlThread.
	 * @param connexionTcp Connexion tcp a paralleliser dans un thread.
	 * @param controlBank Banque associe.
	 */
	public ControlThreadTcp(Tcp connexionTcp, ControlBank controlBank) {
		System.out.println(Thread.currentThread().getName() + " : New Thread TCP");
		this.connexionTcp = connexionTcp;
		this.controlBank = controlBank;
	}
	
	/**
	 * Traitement de la connexion TCP par le thread.
	 */
	public void run() {
		String query, response;
		query = connexionTcp.readMessage();
		while ( query != null ) {
			System.out.println(Thread.currentThread().getName() + " - QUERY - " + query);
			response = controlBank.process(query);
			System.out.println(Thread.currentThread().getName() + " - RESPONSE - " + response);
			connexionTcp.writeMessage(response);
			query = connexionTcp.readMessage();
		}
		System.out.println(Thread.currentThread().getName() + " : Close tcp connexion");
		this.connexionTcp.close();
	}

}
