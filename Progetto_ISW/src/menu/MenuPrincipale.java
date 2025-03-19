package menu;

import Persistenza.LogicaPersistenza;
import utenti.Configuratore;
import util.Menu;

public class MenuPrincipale{
	
	private LogicaPersistenza logica;
	private Menu menu;
	
	/**
	 * Menu Iniziale
	 */
	private static final String MSG_BENVENUTO = "   BENVENUTO -- PAGINA INIZIALE";
	private static final String MSG_ACC_CONFIG = "Accedi come configuratore";
	private static final int CASE_CONFIGURATORE = 1;
	private String[] vociIniziali = {MSG_ACC_CONFIG};
	
	/**
	 * Menu Autenticazione Configuratore
	 */
	private static final String MSG_AUTENT_CONFIG = "\tAUTENTICAZIONE";
	private static final String MSG_PRIMO_ACCESSO = "Registrati";
	private static final String MSG_CONFIG_REGISTRATO= "Accedi";
	private static final int CASE_PRIMO_ACCESSO = 1;
	private static final int CASE_ACCESSO = 2;
	private String[] vociConfiguratore = {MSG_PRIMO_ACCESSO, MSG_CONFIG_REGISTRATO};
	
	/**
	 * Voci Menu Configuratore
	 */
	private static final int CASE_N_COMPRENSORIO = 1;
	private static final int CASE_N_GERARCHIA = 2;
	private static final int CASE_V_COMPRENSORI = 3;
	private static final int CASE_V_GERARCHIE = 4;
	private static final int CASE_V_FAT_CONV = 5;
	private static final int CASE_SALVA = 6;
	
	/**
	 * Costruttore della pagina iniziale del programma
	 * @param logica
	 */
	public MenuPrincipale(LogicaPersistenza logica) {
		this.logica = logica;
		this.menu = new Menu(MSG_BENVENUTO, vociIniziali);
	}
	
	/**
	 * Metodo per mostrare le azioni che un utente puo' svolgere
	 */
	
	public void azioniMenuPrincipale() {
		int scelta;
		do {
			scelta = menu.scegli();
			switch(scelta) {
			case CASE_CONFIGURATORE:
				autenticazioneConfig();
				break;
				
			}
		} while (scelta != 0);
	}
	
	/**
	 * Metodo per autenticare l'utente come configuratore o registrarsi come tale
	 */
	
	private void autenticazioneConfig() {
		Autenticazione autentic = new Autenticazione(logica);
		Menu menuAccessoConfig = new Menu(MSG_AUTENT_CONFIG, vociConfiguratore);
		int scelta;
		do {
			scelta = menuAccessoConfig.scegli();
			switch(scelta) {
			case CASE_PRIMO_ACCESSO:
				autentic.primoAccessoConfig();
				break;
			case CASE_ACCESSO:
				Configuratore config = autentic.accessoConfiguratore();
				if(config != null) {
					MenuConfiguratore menuConfig = new MenuConfiguratore(config, logica);
					avviaMenuConfiguratore(menuConfig);
				}
				break;
			default:
				return;
			}
		} while (scelta != 0);
	}
	
	
	/**
	 * Metodo per mostrare le azione che un configuratore puo' svolgere
	 * @param menuConfig
	 */
	private void avviaMenuConfiguratore(MenuConfiguratore menuConfig) {
		int scelta;
		do {
			scelta = menuConfig.scegli();
			switch(scelta) {
			case CASE_N_COMPRENSORIO:
				menuConfig.creaComprensorio();
				break;
			case CASE_N_GERARCHIA:
				menuConfig.creaGerarchia();
				break;
			case CASE_V_COMPRENSORI:
				menuConfig.visualizzaComprensori();
				break;
			case CASE_V_GERARCHIE:
				menuConfig.visualizzaGerarchie();
				break;
			case CASE_V_FAT_CONV:
				menuConfig.visualizzaFatConv();
				break;
			case CASE_SALVA:
				menuConfig.salva();
				break;
			default:
				return;
			}
		} while (scelta != 0);
	}

}
