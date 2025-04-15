package applicazione;

/**
 * Classe per andare a identificare una caetgoria intermedia all'interno di una gerarchia
 * @author diego
 *
 */
public class CategoriaNonFoglia extends Categoria{
	
	public CategoriaNonFoglia(String nome, CampoCaratteristico campCaratt, Boolean completo, Integer dominio) {
		super(nome, campCaratt, completo, dominio, false);
	}
	
	public CategoriaNonFoglia(String nome, CampoCaratteristico campCaratt, Integer dominio) {
		super(nome, campCaratt, dominio, false);
	}
}
