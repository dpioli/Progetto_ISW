package Persistenza;

import java.util.ArrayList;

import applicazione.CategoriaFoglia;
import applicazione.Comprensorio;
import applicazione.FatConversione;
import applicazione.Gerarchia;
import utenti.Configuratore;

/**
 * Classe che permette la gestione logistica dell'aggiornamento e modifica dei dati salvaati sui file
 * @author Diego Pioli 736160
 *
 */
public class LogicaPersistenza {
	
	private ArrayList<Gerarchia> gerarchie = new ArrayList<Gerarchia>();
	private ArrayList<Comprensorio> comprensori = new ArrayList<Comprensorio>();
	private ArrayList<Configuratore> configuratori = new ArrayList<Configuratore>();
	private ArrayList<CategoriaFoglia> categorieFoglia = new ArrayList<CategoriaFoglia>();
	private FatConversione fatConversione = new FatConversione();
	
	public LogicaPersistenza() {
		this.gerarchie = GestorePersistenza.caricaGerarchie();
		this.comprensori = GestorePersistenza.caricaComprensorio();
		this.configuratori = GestorePersistenza.caricaConfiguratori();
		this.fatConversione = GestorePersistenza.caricaFatConversione();
		this.categorieFoglia = GestorePersistenza.caricaCategorieFoglia();
	}
	
	/*
	 * 
	 * 
	 * GETTER E SETTER
	 * 
	 * 
	 */
	
	/**
	 * Metodo per ottenere l'insieme delle Gerarchie
	 * @return ArrayList<Gerarchia>
	 */
	public ArrayList<Gerarchia> getGerarchie() {
		return gerarchie;
	}
	
	/**
	 * Metodo per modificare l'insieme delle gerarchie
	 * @param gerarchie
	 */
	public void setGerarchie(ArrayList<Gerarchia> gerarchie) {
		this.gerarchie = gerarchie;
	}
	
	/**
	 * Metodo per ottenere l'insieme dei comprensori
	 * @return ArrayList<Comprensorio>
	 */
	public ArrayList<Comprensorio> getComprensori() {
		return comprensori;
	}
	
	/**
	 * Metodo per modificare l'insieme dei comprensori
	 * @param comprensori
	 */
	public void setComprensori(ArrayList<Comprensorio> comprensori) {
		this.comprensori = comprensori;
	}
	
	/**
	 * Metodo per ottenere l'insieme dei configuratori
	 * @return ArrayList<Configuratore>
	 */
	public ArrayList<Configuratore> getConfiguratori() {
		return configuratori;
	}
	
	/**
	 * Metodo per modificare l'insieme dei configuratori
	 * @param configuratori
	 */
	public void setConfiguratori(ArrayList<Configuratore> configuratori) {
		this.configuratori = configuratori;
	}
	
	/**
	 * Metodo per ottenere l'insieme dei fattori di conversione
	 * @return ArrayList<FatConversione>
	 */
	public FatConversione getFatConversione() {
		return fatConversione;
	}
	
	/**
	 * Metodo per modificare l'insieme dei fattori di conversione
	 * @param fatConversione
	 */
	public void setFatConversione(FatConversione fatConversione) {
		this.fatConversione = fatConversione;
	}
	
	public ArrayList<CategoriaFoglia> getCategorieFoglia() {
		return categorieFoglia;
	}

	public void setCategorieFoglia(ArrayList<CategoriaFoglia> categorieFoglia) {
		this.categorieFoglia = categorieFoglia;
	}

	/*
	 * 
	 * 
	 * FUNZIONALITA'
	 * 
	 * 
	 */
	

	/**
	 * Metodo per aggiungere un configuratore all'insieme dei configuratori
	 * @param configuratore
	 */
	public void addConfiguratore(Configuratore configuratore) {
		configuratori.add(configuratore);
	}

	public void addComprensorio(Comprensorio comprensorio) {
		comprensori.add(comprensorio);
	}

	public void addGerarchia(Gerarchia gerarchia) {
		gerarchie.add(gerarchia);
		
	}

	public void addCategoriaFoglia(CategoriaFoglia nuovaCategFoglia) {
		categorieFoglia.add(nuovaCategFoglia);
		
	}
	public void aggiungiFDC(Integer nuova) {
		fatConversione.aggancia(nuova);	
	}
	

}
