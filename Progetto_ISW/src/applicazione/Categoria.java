package applicazione;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe per andare a identificare le prorpieta' di una categoria
 * @author diego
 *
 */
public class Categoria {
	
	private String nome;
	private CampoCaratteristico campCaratt;
	private ArrayList<Categoria> sottoCateg;
	private Boolean completo;
	private Integer dominio;
	
	/**
	 * Costruttore della classe categoria
	 * @param nome
	 * @param campCaratt
	 * @param completo
	 * @param dominio
	 */
	public Categoria (String nome, CampoCaratteristico campCaratt, Boolean completo, Integer dominio) {
		this.nome = nome;
		this.campCaratt = campCaratt;
		this.completo = completo;
		this.sottoCateg = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Categoria> getSottoCateg() {
		return sottoCateg;
	}

	public void setSottoCateg(ArrayList<Categoria> sottoCateg) {
		this.sottoCateg = sottoCateg;
	}

	public CampoCaratteristico getCampCaratt() {
		return campCaratt;
	}

	public void setCampCaratt(CampoCaratteristico campCaratt) {
		this.campCaratt = campCaratt;
	}

	public Boolean getCompleto() {
		return completo;
	}

	public void setCompleto(Boolean completo) {
		this.completo = completo;
	}

	public Integer getDominio() {
		return dominio;
	}

	public void setDominio(Integer dominio) {
		this.dominio = dominio;
	}
	
	/**
	 * Metodo per verificare se una categoria ha lo stesso nome di un'altra
	 * @param nomeCat
	 * @return true o false
	 */
	public boolean eUguale(String nomeCat) {
		return nome.equalsIgnoreCase(nomeCat);
	}
	
	/**
	 * Metodo per verificare se e' presente una categoria con quel nome all'intenro di una gerarchia
	 * @param nomeCat
	 * @return true o false
	 */
	public boolean ePresenteCatgoria(String nomeCat) {
		for(Categoria c: sottoCateg) {
			if(c.eUguale(nomeCat))
				return true;
		}
		return false;
	}
	
	public HashMap<String, String> getValoriCampo(){
		return campCaratt.getValori();
	}
	
	public String stampaCateg() {
		StringBuffer sb = new StringBuffer();
		for(Categoria c: this.sottoCateg) {
			sb.append(c.toString());
		}
		return sb.toString();
	}
	
	/**
	 * Metodo che permette la visualizzazione di una categoria
	 * (DA AGGIUSTARE)
	 * @return descrizione categoria
	 */
	public String toString() {
		return String.format("Categoria: %s\n"
							+ "\t\tSottocategorie > \n\t\t\t%s", 
							this.getNome(),
							this.stampaCateg());
	}

}
