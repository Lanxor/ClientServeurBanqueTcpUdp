package model;

import java.util.Date;

/**
 * Classe Compte en banque.
 * @author OLIVIER Thomas
 *
 */
public class Account {
	private double solde;
	private Date lastOperation;
	
	/**
	 * Constructeur bank.Account.
	 * @param solde Montant initiale lors de la creation d'un compte en banque.
	 */
	public Account(double solde) {
		this.solde = solde;
		this.lastOperation = new Date();
	}
	
	/**
	 * Renvoie le solde du compte en banque.
	 * @return Renvoie le solde du compte en banque.
	 */
	public synchronized double getSolde() {
		return this.solde;
	}
	
	/**
	 * Recupere la date de derniere action effectuer sur le compte en banque.
	 * @return Renvoie la date de la derniere action effectuer sur le compte en banque.
	 */
	public synchronized Date getLastOperation() {
		return this.lastOperation;
	}
	
	/**
	 * Ajoute un certain montant sur le compte en banque.
	 * @param number Valeur a ajouter sur le compte en banque.
	 */
	public synchronized void add(double number) {
		this.solde += number;
		this.lastOperation = new Date();
	}
	
	/**
	 * Decompte un certain montant sur le compte en banque.
	 * @param number Valeur a decompter sur le compte en banque.
	 */
	public synchronized void remove(double number) {
		this.solde -= number;
		this.lastOperation = new Date();
	}
}
