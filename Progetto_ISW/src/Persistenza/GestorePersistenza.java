package Persistenza;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import applicazione.Comprensorio;
import applicazione.FatConversione;
import applicazione.Gerarchia;
import utenti.Configuratore;


/**
 * Classe per la gestione della persistenza dei dati
 * 
 * @author Diego Pioli 736160
 */
public class GestorePersistenza {
	
	private static final String FILE_CONFIGURATORI = "../Progetto_ISW/src/dati/configuratori.json";
	private static final String FILE_GERARCHIE = "../Progetto_ISW/src/dati/gerarchie.json";
	private static final String FILE_COMPRENSORI = "../Progetto_ISW/src/dati/comprensori.json";
	private static final String FILE_FATT_CONVERSIONE = "../Progetto_ISW/src/dati/fattConversione.json";
	private static Gson gson;
	
	/**
	 * Costruttore della classe GestorePersistenza
	 */
	public GestorePersistenza() {
		GestorePersistenza.gson = new GsonBuilder().setPrettyPrinting().create();
	}
	
	/**
	 * Metdo generico per salvare i dati su un file json
	 * @param <T>
	 * @param oggetto
	 * @param fpath
	 */
	private static <T> void salva(T oggetto, String fpath) {
		try(FileWriter wr = new FileWriter(fpath)){
			gson.toJson(oggetto, wr);
			wr.close();
		} catch (IOException e) {
			System.err.println("Errore durante il salvataggio: " + e.getMessage());
		}
	}
	
	/**
	 * Metodo generico per cericare i dati salvati su un file json
	 * @param <T>
	 * @param obj
	 * @param fpath
	 * @return oggetto generico salvato sul file
	 */
	private static <T> T carica(Type typeOfT, String fpath) {
	    T oggetto = null;
	    try (FileReader rd = new FileReader(fpath)){
	        oggetto = gson.fromJson(rd, typeOfT);
	    } catch (IOException e) {
	        System.err.println("Errore durante il caricamento: " + e.getMessage());
	    }
	    return oggetto != null ? oggetto : null; // per collezioni non creiamo nuovi oggetti vuoti
	}
	
	/**
	 * Metodo generico per andare a creare l'oggetto se questo non è inizializzato
	 * @param <T>
	 * @param classe
	 * @return oggetto inizializzato
	 */
	private static <T> T creaOggettoVuoto(Class<T> classe) {
        try {
            return classe.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/*
	 * 
	 * 
	 * METODI PER SALVARE SU FILE TRAMITE JSON
	 * 
	 *  
	 */
	
	/**
	 * Metodo per salvare le gerarchie
	 * @param gerarchie
	 */
	public static void salvaGerarchie(ArrayList<Gerarchia> gerarchie) {
		salva(gerarchie, FILE_GERARCHIE);
	}
	
	/**
	 * Metodo per salvare i comprensori
	 * @param comprensori
	 */
	public static void salvaComprensori(ArrayList<Comprensorio> comprensori) {
		salva(comprensori, FILE_COMPRENSORI);
	}
	
	/**
	 * Metodo per salvare i configuratori
	 * @param configuratori
	 */
	public static void salvaConfiguratori(ArrayList<Configuratore> configuratori) {
		salva(configuratori, FILE_CONFIGURATORI);
	}
	
	/**
	 * Metodo per salvare i fattori di conversione
	 * @param fatConversione
	 */
	public static void salvaFatConversione(ArrayList<FatConversione> fatConversione) {
		salva(fatConversione, FILE_FATT_CONVERSIONE);
	}
	
	/*
	 * 
	 * 
	 * METODI PER CARICARE I FILE DA JSON
	 * 
	 * 
	 */
	
	/**
	 * Metodo per caricare le gerarchie
	 * @return insieme delle gerarchie
	 */
	public static ArrayList<Gerarchia> caricaGerarchie(){
	    Type listType = new TypeToken<ArrayList<Gerarchia>>() {}.getType();
	    return carica(listType, FILE_GERARCHIE);
	}

	
	/**
	 * Metodo per caricare i comprensori
	 * @return insieme dei comprensori
	 */
	public static ArrayList<Comprensorio> caricaComprensorio(){
	    Type listType = new TypeToken<ArrayList<Comprensorio>>() {}.getType();
	    return carica(listType, FILE_COMPRENSORI);
	}
	
	/**
	 * Metodo per caricare i configuratori
	 * @return insieme dei configuratori
	 */
	public static ArrayList<Configuratore> caricaConfiguratori(){
	    Type listType = new TypeToken<ArrayList<Configuratore>>() {}.getType();
	    return carica(listType, FILE_CONFIGURATORI);
	}
	
	/**
	 * Metodo per caricare i fattori di conversione
	 * @return insieme dei fattori di conversione
	 */
	public static ArrayList<FatConversione> caricaFatConversione(){
	    Type listType = new TypeToken<ArrayList<FatConversione>>() {}.getType();
	    return carica(listType, FILE_FATT_CONVERSIONE);
	}
}
