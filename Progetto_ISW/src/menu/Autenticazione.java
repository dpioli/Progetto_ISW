package menu;

import Persistenza.GestorePersistenza;
import Persistenza.LogicaPersistenza;
import utenti.Configuratore;
import util.InputDati;


/**
 * Classe dedita ad autenticare gli utenti prima di poter agire all'interno del programma
 * @author Diego Pioli 736160
 *
 */
public class Autenticazione {
	
	private LogicaPersistenza logica;
	
	private static final String USERNAME_PREDEFINITO = "configuratore";
	private static final String PASSWORD_PREDEFINITA = "password";

	private static final String MSG_ASK_USERNAME = "Inserisci l'username (ESC per uscire) > ";
	private static final String MSG_ASK_PASSWORD = "Inserisci la password (ESC per uscire) > ";

	private static final String MSG_ASK_USER_PREDEF = "Inserisci l'username predefinito > ";
	private static final String MSG_ASK_PSW_PREDEF = "Inserisci la password predefinita > ";
	
	private static final String UTENTE_NON_PRESENTE = "Questo utente non è presente nel sistema. \nTornando indietro...";
	private static final String PSW_ERRATA = "Password errata. (ESC per uscire) ";

	private static final String MSG_NEW_CREDENZIALI = "Inserisci le nuove credenziali di seguito\n\n";
	private static final String MSG_NEW_USERNAME = "Inserisci un nuovo username > ";
	private static final String MSG_NEW_PASSWORD = "Inserisci una nuova password > ";
	
	private static final String MSG_RILEVA_PRIMO_ACCESSO = "\nSembra che tu non sia ancora registrato!\nREGISTRATI >";
	private static final String MSG_RICHIESTA_AUTENTICAZIONE = "Inserire le credenziali di autenticazione: ";

	private static final String MSG_ACCESSO_RIUSCITO = "\nAccesso effettuato con successo -- ";
	private static final String MSG_SUCC_REGIST = "\n\nRegistrazione avvenuta con successo.\n";

	private static final String MSG_NON_VALIDO = "Username non valido, utente già registrato nel sistema. Riprova. ";
	
	private static final String RILEVATA_RICHIESTA_DI_USCITA = "Rilevata richiesta di uscita.";
	private static final String ESC = "ESC";
	
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
	 * Metodo che permette di accedere alle funzionalita' di un configuratore.
	 * Se rileva a un primo accesso rimanda alla pagina di registrazione.
	 * @return Configuratore
	 */
	public Configuratore accessoConfiguratore() {
		String username = InputDati.leggiStringaNonVuota(MSG_ASK_USERNAME);
		String password;
		
		if(richiedeUscita(username)) {
			System.out.println(RILEVATA_RICHIESTA_DI_USCITA);
			return null;
		}
		
		if(username.equals(USERNAME_PREDEFINITO)) {
			System.out.println(MSG_RILEVA_PRIMO_ACCESSO);
			primoAccessoConfig();
		} 
		
		for(Configuratore c: logica.getConfiguratori()) {
			if(c.getUsername().equals(username)) {
				do{
					password = InputDati.leggiStringaNonVuota(MSG_ASK_PASSWORD);
					if(c.getPassword().equals(password)) {
						System.out.println(MSG_ACCESSO_RIUSCITO + username +"\n");
						return c;
					} else {
						System.out.println(PSW_ERRATA);
					}
				}while(!richiedeUscita(password));
			}
		}
		System.out.println(UTENTE_NON_PRESENTE);
		return null;
	}
	
	/***
	 * METODO PER REGISTRARSI PER LA PRIMA VOLTA COME CONFIGUTATORE
	 * 1. Controlla che abbia i permessi.
	 * 2. Inserimento nuove credenziali.
	 * 3. Salvaraggio.
	 */
	public void primoAccessoConfig() {
		
		controllaPredefinite();
			
		System.out.println(MSG_NEW_CREDENZIALI);
		String newUsername = inserisciUsername();
		String newPassword = InputDati.leggiStringaNonVuota(MSG_NEW_PASSWORD);
		
		logica.addConfiguratore(new Configuratore(newUsername, newPassword));
		GestorePersistenza.salvaConfiguratori(logica.getConfiguratori());
		
		System.out.println(MSG_SUCC_REGIST);
	}
	
	/***
	 * Metodo che controlla se l'username inserito è già presente nel sistema.
	 * @param username da controllare
	 * @return true se trova corrispondenza nei configuratori salvati
	 */
	public boolean ePresenteConfiguratore(String username) {
		for(Configuratore c: logica.getConfiguratori()) {
			if(c.getUsername().equals(username)) {
				System.out.println(MSG_NON_VALIDO);
				return true;
			}
		}
		return false;
	}
	
	/***
	 * Metodo che si assicura che l'utente, che cerca di accedere come configuratore, inserisca l'username e la password predefinita 
	 * (controlla quindi che abbia l'autorizzazione per accedere).
	 */
	public void controllaPredefinite() {
		boolean predefinito;
		do {
			System.out.println(MSG_RICHIESTA_AUTENTICAZIONE);
			String username = InputDati.leggiStringaNonVuota(MSG_ASK_USER_PREDEF);
			String password = InputDati.leggiStringaNonVuota(MSG_ASK_PSW_PREDEF);
			predefinito = rilevaPrimoAccesso(username, password);
		}while(!predefinito);
	}
	
	/***
	 * Metodo che controlla se username e password inseriti corrispondono a quelli predefiniti.
	 * @param username
	 * @param password
	 * @return true se la corrispondenza è vera
	 */
	public boolean rilevaPrimoAccesso(String username, String password) {
		return (username.equals(USERNAME_PREDEFINITO) && password.equals(PASSWORD_PREDEFINITA)) ? true : false;
	}
	
	/***
	 * Metodo per ricezione stringa di input del nuovo username.
	 * Controlla che la stringa non sia vuota e che non coincida con l'username predefinito del configuratore.
	 * @return stringa username
	 */
	public String inserisciUsername() {
		boolean corretto = false;
		String newUsername = "";
		do {
			newUsername = InputDati.leggiStringaNonVuota(MSG_NEW_USERNAME);
			if(newUsername.equals(USERNAME_PREDEFINITO)) {
				System.out.println(MSG_NON_VALIDO);
			} if (ePresenteConfiguratore(newUsername)) {
				System.out.println(MSG_NON_VALIDO);
			}
			else break;
		} while(!corretto);
		return newUsername;
	}
	/**
	 * Metodo che verifica se l'input corrisponde alla parola chiave di uscita.
	 * @param input
	 * @return true se corrispondono
	 */
	public boolean richiedeUscita(String in) {
		return in.equalsIgnoreCase(ESC) ? true : false;
	}
	

}
