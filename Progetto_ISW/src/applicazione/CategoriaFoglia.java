package applicazione;

/**
 * Classe per andare a identificare una classe foglia di una gerarchia
 * @author diego
 *
 */
public class CategoriaFoglia extends Categoria{
	
	private int id = 1;

	public CategoriaFoglia(String nome) {
		super(nome, null, null, null);
		this.id ++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
