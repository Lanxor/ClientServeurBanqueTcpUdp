package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe d'un banque simple.
 * @author OLIVIER Thomas
 *
 */
public class Bank {
	
	private HashMap<String, Account> accounts;
	
	/**
	 * Constructeur Bank.
	 */
	public Bank() {
		this.accounts = new HashMap<String, Account>();
	}
	
	/**
	 * Cree un compte en banque et l'ajoute dans la banque.
	 * @param id Identifiant du compte en banque.
	 * @param number Solde de depart lors de la creation.
	 */
	public synchronized void createAccount(String id, double number) {
		this.accounts.put(id, new Account(number));
	}
	
	/**
	 * Ajoute un montant dans un compte en banque specifique.
	 * @param id Identifiant du compte en banque.
	 * @param number Montant a ajouter dans le compte en banque.
	 */
	public synchronized void add(String id, double number) {
		Account account = this.accounts.get(id);
		account.add(number);
	}
	
	/**
	 * Decompte un montant dans un compte en banque specifique.
	 * @param id Identifiant du compte en banque.
	 * @param number Montant a enlever dans le compte en banque.
	 */
	public synchronized void remove(String id, double number) {
		Account account = this.accounts.get(id);
		account.remove(number);
	}
	
	/**
	 * Recupere le solde  d'un compte en banque.
	 * @param id Identifiant du compte en banque.
	 * @return Renvoie la valeur du solde du compte en banque.
	 */
	public synchronized double getSolde(String id) {
		Account account = this.accounts.get(id);
		return account.getSolde();
	}
	
	/**
	 * Recupere la date de la derniere action effectuer sur le compte en banque.
	 * @param id Identifiant du compte en banque.
	 * @return Renvoie la date de la derniere action effectuer sur le compte en banque.
	 */
	public synchronized Date getLastOperation(String id) {
		Account account = this.accounts.get(id);
		return account.getLastOperation();
	}
	
	/**
	 * Verifie si un compte en banque existe dans la banque.
	 * @param id Identifiant du compte en banque.
	 * @return Renvoie True s'il existe un compte en banque comportant l'identifiant, sinon renvoie False.
	 */
	public synchronized boolean existAccount(String id) {
		return this.accounts.containsKey(id);
	}
}
