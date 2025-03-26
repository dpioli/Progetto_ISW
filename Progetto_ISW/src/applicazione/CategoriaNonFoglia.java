package applicazione;

import java.util.ArrayList;

/**
 * Classe per andare a identificare una caetgoria intermedia all'interno di una gerarchia
 * @author diego
 *
 */
public class CategoriaNonFoglia extends Categoria{
	
	//private ArrayList<Categoria> sottoCategorie; 

	public CategoriaNonFoglia(String nome, CampoCaratteristico campCaratt, Boolean completo, Integer dominio) {
		super(nome, campCaratt, completo, dominio);
		//this.sottoCategorie = new ArrayList<>();
	}
	
	/*
	public ArrayList<Categoria> getSottoCategorie() {
		return sottoCategorie;
	}

	public void setSottoCategorie(ArrayList<Categoria> sottoCategorie) {
		this.sottoCategorie = sottoCategorie;
	}
	*/
	
}
