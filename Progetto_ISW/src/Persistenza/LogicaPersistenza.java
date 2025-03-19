package Persistenza;

import java.util.ArrayList;

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
	private ArrayList<FatConversione> fatConversione = new ArrayList<FatConversione>();
	
	public LogicaPersistenza() {
		this.gerarchie = GestorePersistenza.caricaGerarchie();
		this.comprensori = GestorePersistenza.caricaComprensorio();
		this.configuratori = GestorePersistenza.caricaConfiguratori();
		this.fatConversione = GestorePersistenza.caricaFatConversione();
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
	public ArrayList<FatConversione> getFatConversione() {
		return fatConversione;
	}
	
	/**
	 * Metodo per modificare l'insieme dei fattori di conversione
	 * @param fatConversione
	 */
	public void setFatConversione(ArrayList<FatConversione> fatConversione) {
		this.fatConversione = fatConversione;
	}

	/*
	 * 
	 * 
	 * FUNZIONALITA'
	 * 
	 * 
	 */
	
	/**
	 * Metoo per aggiungere un configuratore all'insieme dei configuratori
	 * @param configuratore
	 */
	public void addConfiguratore(Configuratore configuratore) {
		configuratori.add(configuratore);
	}
	
	

}
