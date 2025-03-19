package menu;

import Persistenza.GestorePersistenza;
import Persistenza.LogicaPersistenza;
import utenti.Configuratore;
import util.InputDati;


/**
 * Classe dedita ad autenticare gli utenti prima di poter agire all'interno del programma
 * @author diego
 *
 */
public class Autenticazione {
	
	private LogicaPersistenza logica;
	
	private static final String USERNAME_PREDEFINITO = "configuratore";
	private static final String PASSWORD_PREDEFINITA = "password";

	private static final String MSG_ASK_USERNAME = "Inserisci l'username > ";
	private static final String MSG_ASK_PASSWORD = "Inserisci la password > ";

	private static final String MSG_NEW_CREDENZIALI = "Inserisci le nuove credenziali di seguito\n\n";
	private static final String MSG_NEW_USERNAME = "Inserisci un nuovo username > ";
	private static final String MSG_NEW_PASSWORD = "Inserisci una nuova password > ";
	
	private static final String MSG_PRIMO_ACCESSO = "Effettua la regitrazione prima di continuare";

	private static final String MSG_ACCESSO_RIUSCITO = "\nAccesso effettuato con successo -- ";

	private static final String MSG_ERRORE = "Username e/o password sono errati";
	private static final String MSG_NON_VALIDO = "Username non valido riprova";
	
	/**
	 * Costruttore della classe Autenticazione 
	 * Riceve come parametro al logica di persistenza per effettuare i controlli
	 * di validita' delle credenziali
	 * @param logica
	 */
	public Autenticazione(LogicaPersistenza logica) {
		this.logica = logica;
	}
	
	/**
	 * Metodo che permette di accedere alle funzionalita' di un configuratore
	 * @return Configuratore
	 */
	public Configuratore accessoConfiguratore() {
		String username = InputDati.leggiStringaNonVuota(MSG_ASK_USERNAME);
		String password = InputDati.leggiStringaNonVuota(MSG_ASK_PASSWORD);
		
		if(username.equals(USERNAME_PREDEFINITO) && password.equals(PASSWORD_PREDEFINITA)) {
			System.out.println(MSG_PRIMO_ACCESSO);
			primoAccessoConfig();
		} 
		
		for(Configuratore c: logica.getConfiguratori()) {
			if(c.getUsername().equals(username)) {
				if(c.getPassword().equals(password)) {
					System.out.println(MSG_ACCESSO_RIUSCITO + username +"\n");
					return c;
				}
			}
		}
		System.out.println(MSG_ERRORE);	
		return null;
	}
	
	/**
	 * Metodo per registrarsi all'interno del programma
	 */
	public void primoAccessoConfig() {
		boolean predefinito = false;
		do {
			String username = InputDati.leggiStringaNonVuota(MSG_ASK_USERNAME);
			String password = InputDati.leggiStringaNonVuota(MSG_ASK_PASSWORD);
			if(!(username.equals(USERNAME_PREDEFINITO) && password.equals(PASSWORD_PREDEFINITA))) {
				System.out.println(MSG_ERRORE);
				predefinito = false;
			} else {
				predefinito = true;
			}
		} while(!predefinito);
			
		System.out.println(MSG_NEW_CREDENZIALI);
		boolean corretto = false;
		String newUsername = "";
		while(!corretto) {
			corretto = true;
			newUsername = InputDati.leggiStringaNonVuota(MSG_NEW_USERNAME);
			if(newUsername.equals(USERNAME_PREDEFINITO)) {
				System.out.println(MSG_NON_VALIDO);
				corretto = false;
			} else {
				for(Configuratore c: logica.getConfiguratori()) {
					if(c.getUsername().equals(newUsername))
						System.out.println(MSG_NON_VALIDO);
					corretto = false;
					break;
				}
			}
		} 
		String newPassword = InputDati.leggiStringaNonVuota(MSG_NEW_PASSWORD);
		logica.addConfiguratore(new Configuratore(newUsername, newPassword));
		GestorePersistenza.salvaConfiguratori(logica.getConfiguratori());
	}
		
	

}
