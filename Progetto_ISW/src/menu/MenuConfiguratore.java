package menu;

import java.util.ArrayList;
import java.util.Map.Entry;

import Persistenza.GestorePersistenza;
import Persistenza.LogicaPersistenza;
import applicazione.CampoCaratteristico;
import applicazione.Categoria;
import applicazione.CategoriaFoglia;
import applicazione.CategoriaNonFoglia;
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
	private final static String MSG_P_PRECEDENTE = "Ritorna alla pagina di autenticazione";
	
	/**
	 * CREAZIONE COMPRENSORIO
	 */
	private static final String MSG_CREAZIONE_COMPRENSORIO = "Stai creando un nuovo comprensorio, inserisci" 
								+ " le informazioni necessarie";
	private static final String MSG_NOME_COMPRENSORIO = "Inserisci il nome del comprensorio > ";
	private static final String MSG_NOME_COMP_NON_VALIDO = "Il nome inserito non è valido in quanto gia presente, riprova";
	private static final String MSG_INSERISCI_COMUNI = "Inserisci il nome del comune (o 'fine' per terminare) > ";
	private static final String MSG_TERMINAZIONE = "fine";
	private static final String MSG_ERRORE_INSERIMENTO_COMUNI = "Errore: Il comprensorio deve contenere almeno un comune.";
	private static final String MSG_SUCCESSO_COMPRENSORIO = "Comprensorio creato con successo!";
	
	/**
	 * CREAZIONE GERARCHIA
	 */
	private static final String MSG_CREAZIONE_GERARCHIA = "Stai creando una nuova gerarchie, inserisci"
			+ " le informazioni necessarie";
	private static final String MSG_NOME_GERARCHIA = "Inserisci il nome della nuova gerarchia >";
	private static final String MSG_NOME_GERARCHIA_NON_VALIDO = "E' già presente una gerarchia con questo nome.";
	private static final String MSG_NOME_CAMPOCARATT = "Inserisci il nome del campo caratteristico > ";
	private static final String MSG_VALORE_CAMPOCARATT = "Inserisci il valore ('fine' per terminare) > ";
	private static final String MSG_INSERISCI_SOTTOCATEG = "Inserisci le sottocategorie della gerarchia appena creata:\n ";
	private static final String MSG_GERARCHIA_CREATA_CON_SUCCESSO = "La gerarchia è stata creata con successo!";
	
	private static final String MSG_CERAZIONE_NODI = "Vuoi creare una categoria intermedia (1) o una foglia (2)? > ";
	
	private static final String MSG_CATEGORIA_NON_FOGLIA = "Stai creando una categroia intermedia:";
	private static final String MSG_CATEGORIA_FOGLIA = "Stai creando una categoria foglia: ";
	private static final String MSG_NOME_CATEGORIA = "Inserisci il nome della categoria >";
	private static final String MSG_NOME_CATEGORIA_NON_VALIDO = "E' già presente una categoria con questo nome.";

	/**
	 * VISUALIZZA COMPRENSORI
	 */
	private static final String NESSUN_COMPRENSORIO = "Non è ancora presente nessun comprensorio";
	
	/**
	 * VISUALIZZA GERARCHIE
	 */
	private static final String NESSUNA_GERARCHIA = "Non è ancora presente nessuna gerarchia";
	
	/**
	 * VISUALIZZA FATTORI DI CONVERSIONE
	 */
	private static final String NESSUN_FAT_CONVERSIONE = "Non è ancora presente nessun fattore di conversione";
	
	/**
	 * SALVATAGGIO DATI
	 */
	private static final String MSG_SALVATAGGIO = "Salvataggio effettuato con successo!";
	
	private static String[] vociConfig = {MSG_NUOVO_COMPRENSORIO,
			MSG_NUOVA_GERARCHIA,
			MSG_VISUALIZZA_COMPRENSORI,
			MSG_VISUALIZZA_GERARCHIE,
			MSG_VISUALIZZA_FAT_CONV,
			MSG_SALVA,
			MSG_P_PRECEDENTE
	};

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
		String nomeGerarchia = InputDati.leggiStringaNonVuota(MSG_NOME_GERARCHIA);
	
		for(Gerarchia g: logica.getGerarchie()) {
			if(g.eNomeUguale(nomeGerarchia)) {
				System.out.println(MSG_NOME_GERARCHIA_NON_VALIDO);
				return;
			}
		}
		
		String nomeCampo = InputDati.leggiStringaNonVuota(MSG_NOME_CAMPOCARATT);
		ArrayList<String> valoriCampo = new ArrayList<>();
		
		boolean continua = true;
		while(continua) {
			String valore = InputDati.leggiStringaNonVuota(MSG_VALORE_CAMPOCARATT);
			if(valore.equalsIgnoreCase(MSG_TERMINAZIONE)) {
				continua = false;
			} else {
				valoriCampo.add(valore);
			}
		}
		int dimensioneDominio = valoriCampo.size();
		Gerarchia nuovaGerarchia = addGerarchia(nomeGerarchia, nomeCampo, valoriCampo, dimensioneDominio);
		System.out.println(MSG_INSERISCI_SOTTOCATEG);
		addSottoCategorie(nuovaGerarchia.getCatRadice());
		logica.addGerarchia(nuovaGerarchia);
		System.out.println(MSG_GERARCHIA_CREATA_CON_SUCCESSO);
		GestorePersistenza.salvaGerarchie(logica.getGerarchie());

	}


	public void visualizzaComprensori() {
		ArrayList<Comprensorio> comprensori = logica.getComprensori();
		if(comprensori == null) {
			System.out.println(NESSUN_COMPRENSORIO);
		} else {
			for (Comprensorio c : comprensori) {
				System.out.println(c.toString());
			}
		}
			
		
	}

	public void visualizzaGerarchie() {
		ArrayList<Gerarchia> gerarchie = logica.getGerarchie();
		if(gerarchie == null) {
			System.out.println(NESSUNA_GERARCHIA);
		} else {
			for (Gerarchia g : gerarchie) {
				System.out.println(g.toString());
			}
		}
	}

	public void visualizzaFatConv() {
		FatConversione fdc = logica.getFatConversione();
		/*ArrayList<FatConversione> fatConvers = logica.getFatConversione();
		if(fatConvers == null) {
			System.out.println(NESSUN_FAT_CONVERSIONE);
		}
		for (FatConversione fc : fatConvers) {
			System.out.println(fc.toString());
		}*/
		fdc.stampaFDC();
	}

	public void salva() {
		GestorePersistenza.salvaComprensori(logica.getComprensori());
		GestorePersistenza.salvaGerarchie(logica.getGerarchie());
		GestorePersistenza.salvaFatConversione(logica.getFatConversione());
		GestorePersistenza.salvaConfiguratori(logica.getConfiguratori());
		System.out.println(MSG_SALVATAGGIO);
	}
	
	
	/*
	 * 			ELABORAZIONE DATI CREAZIONE GERARCHIA
	 */
	
	private Gerarchia addGerarchia(String nomeGerarchia, String nomeCampo, ArrayList<String> valoriCampo,
			Integer dimensioneDominio) {
		CampoCaratteristico campoCaratt = new CampoCaratteristico(nomeCampo);
		campoCaratt.aggiungiValori(valoriCampo);
		CategoriaNonFoglia radice = new CategoriaNonFoglia(nomeGerarchia, campoCaratt, dimensioneDominio);
		Gerarchia nuovaGerarchia = new Gerarchia(radice, config);
		logica.addGerarchia(nuovaGerarchia);
		return nuovaGerarchia;
	}
	
	private void addSottoCategorie(Categoria categoria) {
		for(Entry<String, String> v: categoria.getValoriCampo().entrySet()) {
			creaCategoria(categoria);
		}
		
	}
	
	private void creaCategoria(Categoria radice) {
		int scelta = InputDati.leggiIntero(MSG_CERAZIONE_NODI, 1, 2);
		
		switch(scelta) {
		case 1:
			creaCategoriaNonFoglia(radice);
			break;
		case 2:
			creaCategoriaFoglia(radice);
			break;
		default:
			return;
		}
	}

	private void creaCategoriaNonFoglia(Categoria radice) {
		System.out.println(MSG_CATEGORIA_NON_FOGLIA);
		String nomeCatNonFl = InputDati.leggiStringaNonVuota(MSG_NOME_CATEGORIA);
		for(Categoria c: radice.getSottoCateg()) {
			if(c.eUguale(nomeCatNonFl)) {
				System.out.println(MSG_NOME_CATEGORIA_NON_VALIDO);
				return;
			}
		}
		String nomeCampo = InputDati.leggiStringaNonVuota(MSG_NOME_CAMPOCARATT);
		ArrayList<String> valoriCampo = new ArrayList<>();
		
		boolean continua = true;
		while(continua) {
			String valore = InputDati.leggiStringaNonVuota(MSG_VALORE_CAMPOCARATT);
			if(valore.equalsIgnoreCase(MSG_TERMINAZIONE)) {
				continua = false;
			} else {
				valoriCampo.add(valore);
			}
		}
		int dimensioneDominio = valoriCampo.size();
		CampoCaratteristico cC = new CampoCaratteristico(nomeCampo);
		cC.aggiungiValori(valoriCampo);
		CategoriaNonFoglia catNnF1 = new CategoriaNonFoglia(nomeCatNonFl, cC, dimensioneDominio);
		radice.getSottoCateg().add(catNnF1);
		addSottoCategorie(catNnF1);
		
	}

	private void creaCategoriaFoglia(Categoria radice) {
		
		System.out.println(MSG_CATEGORIA_FOGLIA);
		String nomeFoglia = InputDati.leggiStringaNonVuota(MSG_NOME_CATEGORIA);
		for(Categoria c: radice.getSottoCateg()) {
			if(c.eUguale(nomeFoglia)) {
				System.out.println(MSG_NOME_CATEGORIA_NON_VALIDO);
				return;
			}
		}
		int ultimoID = logica.recuperaUltimoID();
		
		CategoriaFoglia nuovaCategFoglia = new CategoriaFoglia(nomeFoglia, ultimoID);
		
		radice.getSottoCateg().add(nuovaCategFoglia);
		logica.addCategoriaFoglia(nuovaCategFoglia);
		GestorePersistenza.salvaCategorieFoglia(logica.getCategorieFoglia());
		
		aggiungiFDC(nuovaCategFoglia.getId());
		
	}
	private void aggiungiFDC(Integer nuova) {
		logica.aggiungiFDC(nuova);
	}
}
