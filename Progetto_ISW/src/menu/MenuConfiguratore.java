package menu;

import java.util.ArrayList;

import Persistenza.GestorePersistenza;
import Persistenza.LogicaPersistenza;
import applicazione.Comprensorio;
import applicazione.FatConversione;
import applicazione.Gerarchia;
import utenti.Configuratore;
import util.InputDati;
import util.Menu;

/**
 * Classe per definite il menu delle funzionalità del configuratore
 * 
 * @author diego
 *
 */
public class MenuConfiguratore extends Menu {

	private Configuratore config;
	private LogicaPersistenza logica;

	private final static String titolo = "\tMENU CONFIGURATORE";

	private static final String MSG_NUOVO_COMPRENSORIO = "Aggiungi un nuovo comprensorio";
	private static final String MSG_NUOVA_GERARCHIA = "Inserisci una nuova gerarchia";
	private static final String MSG_VISUALIZZA_COMPRENSORI = "Visualizza tutti i comprensori presenti";
	private static final String MSG_VISUALIZZA_GERARCHIE = "Visualizza tutte le gerarchie";
	private static final String MSG_VISUALIZZA_FAT_CONV = "Visualizza tutti i fattori di conversione";
	private static final String MSG_SALVA = "Salva tutte le modifiche effettuate";

	private static final String MSG_CREAZIONE_COMPRENSORIO = "Stai creando un nuovo comprensorio, inserisci"
			+ " le informazioni necessarie";
	private static final String MSG_NOME_COMPRENSORIO = "Inserisci il nome del comprensorio > ";
	private static final String MSG_NOME_COMP_NON_VALIDO = "Il nome inserito non è valido in quanto gia presente, riprova";
	private static final String MSG_INSERISCI_COMUNI = "Inserisci il nome del comune (o 'fine' per terminare) > ";
	private static final String MSG_TERMINAZIONE = "fine";
	private static final String MSG_ERRORE_INSERIMENTO_COMUNI = "Errore: Il comprensorio deve contenere almeno un comune.";
	private static final String MSG_SUCCESSO_COMPRENSORIO = "Comprensorio creato con successo!";
	
	private static final String MSG_CREAZIONE_GERARCHIA = "Stai creando una nuova gerarchie, inserisci"
			+ " le informazioni necessarie";

	
	private static final String MSG_SALVATAGGIO = "Salvataggio effettuato con successo!";
	
	private static String[] vociConfig = { MSG_NUOVO_COMPRENSORIO, MSG_NUOVA_GERARCHIA, MSG_VISUALIZZA_COMPRENSORI,
			MSG_VISUALIZZA_GERARCHIE, MSG_VISUALIZZA_FAT_CONV, MSG_SALVA };

	/**
	 * Construttore di MenuConfiguratore
	 * 
	 * @param config
	 * @param logica
	 */
	public MenuConfiguratore(Configuratore config, LogicaPersistenza logica) {
		super(titolo, vociConfig);
		this.config = config;
		this.logica = logica;
	}

	public void creaComprensorio() {
		System.out.println(MSG_CREAZIONE_COMPRENSORIO);
		String nomeComprensorio = InputDati.leggiStringaNonVuota(MSG_NOME_COMPRENSORIO);
		for (Comprensorio c : logica.getComprensori()) {
			if (c.getNome().equals(nomeComprensorio)) {
				System.out.println(MSG_NOME_COMP_NON_VALIDO);
				return;
			}
		}

		ArrayList<String> comuni = new ArrayList<>();
		boolean continua = true;

		while (continua) {
			String comune = InputDati.leggiStringaNonVuota(MSG_INSERISCI_COMUNI);
			if (comune.equalsIgnoreCase(MSG_TERMINAZIONE)) {
				continua = false;
			} else {
				comuni.add(comune);
			}
		}

		if (comuni.isEmpty()) {
			System.out.println(MSG_ERRORE_INSERIMENTO_COMUNI);
			return;
		}

		Comprensorio nuovoComprensorio = new Comprensorio(nomeComprensorio, comuni);
		logica.addComprensorio(nuovoComprensorio);
		System.out.println(MSG_SUCCESSO_COMPRENSORIO);
		GestorePersistenza.salvaComprensori(logica.getComprensori());

	}

	public void creaGerarchia() {
		System.out.println(MSG_CREAZIONE_GERARCHIA);

	}

	public void visualizzaComprensori() {
		ArrayList<Comprensorio> comprensori = logica.getComprensori();
		for (Comprensorio c : comprensori) {
			c.toString();
		}
	}

	public void visualizzaGerarchie() {
		ArrayList<Gerarchia> gerarchie = logica.getGerarchie();
		for (Gerarchia g : gerarchie) {
			g.toString();
		}

	}

	public void visualizzaFatConv() {
		ArrayList<FatConversione> fatConvers = logica.getFatConversione();
		for (FatConversione fc : fatConvers) {
			fc.toString();
		}
	}

	public void salva() {
		GestorePersistenza.salvaComprensori(logica.getComprensori());
		GestorePersistenza.salvaGerarchie(logica.getGerarchie());
		GestorePersistenza.salvaFatConversione(logica.getFatConversione());
		GestorePersistenza.salvaConfiguratori(logica.getConfiguratori());
		System.out.println(MSG_SALVATAGGIO);
	}

}
