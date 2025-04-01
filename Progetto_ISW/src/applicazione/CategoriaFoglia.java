package applicazione;

/**
 * Classe per andare a identificare una classe foglia di una gerarchia
 * @author diego
 *
 */
public class CategoriaFoglia extends Categoria{
	
	private static int conta = 0;
	private int id;

	public CategoriaFoglia(String nome) {
		super(nome, null, null, null, true);
		this.id = ++conta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
