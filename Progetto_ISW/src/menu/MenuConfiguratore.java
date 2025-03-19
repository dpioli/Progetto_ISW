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
 * @author diego
 *
 */
public class MenuConfiguratore extends Menu{
	
	private Configuratore config;
	private LogicaPersistenza logica;
	
	private final static String titolo = "\tMENU CONFIGURATORE";
	
	private final static String MSG_NUOVO_COMPRENSORIO = "Aggiungi un nuovo comprensorio";
	private final static String MSG_NUOVA_GERARCHIA = "Inserisci una nuova gerarchia";
	private final static String MSG_VISUALIZZA_COMPRENSORI = "Visualizza tutti i comprensori presenti";
	private final static String MSG_VISUALIZZA_GERARCHIE = "Visualizza tutte le gerarchie";
	private final static String MSG_VISUALIZZA_FAT_CONV = "Visualizza tutti i fattori di conversione";
	private final static String MSG_SALVA = "Salva tutte le modifiche effettuate";
	//private final static String MSG_P_PRECEDENTE = "Ritorna alla pagina precedente.";
	
	
	private static final String MSG_CREAZIONE_COMPRENSORIO = "Stai creando un nuovo comprensorio, inserisci" 
								+ " le informazioni necessarie";
	private static final String MSG_NOME_COMPRENSORIO = "Inserisci il nome del comprensorio > ";
	private static final String MSG_NOME_COMP_NON_VALIDO = "Il nome inserito non è valido in quanto gia presente, riprova";
	
	private static final String MSG_CREAZIONE_GERARCHIA = "Stai creando una nuova gerarchie, inserisci"
								+ " le informazioni necessarie";
	
	
			
	
	private static String[] vociConfig = {MSG_NUOVO_COMPRENSORIO,
			MSG_NUOVA_GERARCHIA,
			MSG_VISUALIZZA_COMPRENSORI,
			MSG_VISUALIZZA_GERARCHIE,
			MSG_VISUALIZZA_FAT_CONV,
			MSG_SALVA,
	};
	
	/**
	 * Construttore di MenuConfiguratore
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
		for(Comprensorio c: logica.getComprensori()) {
			if(c.getNome().equals(nomeComprensorio)) {
				System.out.println(MSG_NOME_COMP_NON_VALIDO);
			}
		}
		
	}

	public void creaGerarchia() {
		System.out.println(MSG_CREAZIONE_GERARCHIA);
		
	}

	public void visualizzaComprensori() {
		ArrayList<Comprensorio> comprensori = logica.getComprensori();
		for(Comprensorio c: comprensori) {
			c.toString();
		}
	}

	public void visualizzaGerarchie() {
		ArrayList<Gerarchia> gerarchie = logica.getGerarchie();
		for(Gerarchia g: gerarchie) {
			g.toString();
		}
		
	}

	public void visualizzaFatConv() {
		ArrayList<FatConversione> fatConvers = logica.getFatConversione();
		for(FatConversione fc: fatConvers) {
			fc.toString();
		}
	}

	public void salva() {
		GestorePersistenza.salvaComprensori(logica.getComprensori());
		GestorePersistenza.salvaGerarchie(logica.getGerarchie());
		GestorePersistenza.salvaFatConversione(logica.getFatConversione());
		GestorePersistenza.salvaConfiguratori(logica.getConfiguratori());
		
	}

}
