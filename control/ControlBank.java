package control;

import model.Bank;

/**
 * Classe ServerBank, interface entre une banque et les serveurs de requete (TCP/UDP).
 * @author OLIVIER Thomas
 */
public class ControlBank {
	
	/**
	 * @see model.Bank
	 */
	private Bank bank;
	
	/**
	 * Constructeur de la classe ServerBank.
	 * @param bank Banque.
	 */
	public ControlBank(Bank bank) {
		this.bank = bank;
	}
	
	/**
	 * Traite une requete, verifie si elle existe, execute l'action associe a la requete et renvoie la reponse formate.
	 * @param query Chaine de cacractere a traiter.
	 * @return Renvoie la reponse a la requete sous forme de chaine de caractere 
	 */
	public String process(String query) {
		String command = query.split(" ")[0].trim().toUpperCase();
		String response;
		
		switch ( command ) {
			case "CREATE":
				response = this.processCreate(query);
				break;
			case "GET":
				response = this.processGet(query);
				break;
			case "ADD":
				response = this.processAdd(query);
				break;
			case "REMOVE":
				response = this.processRemove(query);
				break;
			default:
				response = "ERROR Unknow command - Available command CREATE, GET, ADD, REMOVE\n";
		}
		
		return response;
	}
	
	/**
	 * Verifie les parametres d'une requete CREATE et execute la creation d'un compte en banque. Renvoie un message si tout vas bien.
	 * La synatxe d'une requete CREATE est : 'CREATE id montant_initiale'.
	 * @param query Chaine de caractere a traiter en tant que requete CREATE.
	 * @return Renvoie une chaine de caractere indiquant si l'operation c'est bien deroule ou pas.
	 */
	private synchronized String processCreate(String query) {
		String[] valuesCommand = query.split(" ");
		if ( valuesCommand.length != 3 )
			return "ERROR Bad syntaxe command CREATE - Usage : CREATE id initial_solde";
		String id = valuesCommand[1];
		if ( this.bank.existAccount(id) ) {
			return "ERROR Account alwready exist";
		}
		double number = Double.valueOf(valuesCommand[2]);
		this.bank.createAccount(id, number);
		return "OK " + this.bank.getLastOperation(id);
	}
	
	/**
	 * Verifie les parametres d'une requete GET et recupere le solde du compte en banque. Renvoie un message avec le montant.
	 * La syntaxe d'une requete GET est : 'GET id'.
	 * @param query Chaine de caractere a traiter en tant que requete GET.
	 * @return Renvoie une chaine de caractere avec le montant du solde d'un compte en banque, un message d'erreur sinon.
	 */
	private synchronized String processGet(String query) {
		String[] valuesCommand = query.split(" ");
		if ( valuesCommand.length != 2 )
			return "ERROR Bad syntaxe command GET - Usage : GET id";
		String id = valuesCommand[1];
		if ( !this.bank.existAccount(id) ) {
			return "ERROR Account doesn't exist";
		}
		double solde = this.bank.getSolde(id);
		return "SOLDE " + solde + " " + this.bank.getLastOperation(id);
	}
	
	/**
	 * Verifie les parametres d'une requete ADD et execute l'ajout d'un montant pour un compte en banque. Renvoie un message si tout vas bien.
	 * La syntaxe d'une requete ADD : 'ADD id montant'.
	 * @param query Chaine de caractere a traiter en tant que requete ADD.
	 * @return Renvoie une chaine de caractere indiquant si l'operation c'est bien deroule ou pas.
	 */
	private synchronized String processAdd(String query) {
		String[] valuesCommand = query.split(" ");
		if ( valuesCommand.length != 3 )
			return "ERROR Bad syntaxe command ADD - USAGE : ADD id number";
		String id = valuesCommand[1];
		if ( !this.bank.existAccount(id) ) {
			return "ERROR Account doesn't exist";
		}
		double number = Double.valueOf(valuesCommand[2]);
		this.bank.add(id, number);
		return "OK " + this.bank.getLastOperation(id);
	}
	
	/**
	 * Verifie les parametres d'une requete REMOVE et execute le prelevement d'un montant d'un compte en banque. Renvoie un message si tout vas bien.
	 * La syntaxe d'une requete REMOVE est : 'REMOVE id montant'.
	 * @param query Chaine de caractere a traiter en tant que requete REMOVE.
	 * @return Renvoie une chaine de caractere indiquant si l'operation c'est bien deroule ou pas.
	 */
	private synchronized String processRemove(String query) {
		String[] valuesCommand = query.split(" ");
		if ( valuesCommand.length != 3 )
			return "ERROR Bad syntaxe command REMOVE - USAGE : REMOVE id number";
		String id = valuesCommand[1];
		if ( !this.bank.existAccount(id) ) {
			return "ERROR Account doesn't exist";
		}
		double number = Double.valueOf(valuesCommand[2]);
		this.bank.remove(id, number);
		return "OK " + this.bank.getLastOperation(id);
	}
	
}
