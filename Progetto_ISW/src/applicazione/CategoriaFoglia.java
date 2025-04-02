package applicazione;

import Persistenza.LogicaPersistenza;

/**
 * Classe per andare a identificare una classe foglia di una gerarchia
 * @author diego
 *
 */
public class CategoriaFoglia extends Categoria{
	
	private int id;

	public CategoriaFoglia(String nome, Integer ultimoID) {
		super(nome, null, null, null, true);
		this.id = ++ultimoID;
		System.out.println("id della foglia " + id);
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
