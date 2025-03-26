package applicazione;

import utenti.Configuratore;

/**
 * Classe per identificare le proprieta' di una gerarchia
 * @author diego
 *
 */
public class Gerarchia {
	
	private Categoria catRadice;
	private Configuratore proprietario;
	
	/**
	 * Costruttore della classe gerarchia
	 * @param catRadice
	 * @param prorpietario
	 */
	public Gerarchia (Categoria catRadice, Configuratore prorpietario) {
		this.catRadice = catRadice;
		this.proprietario = prorpietario;
	}

	public Categoria getCatRadice() {
		return catRadice;
	}

	public Configuratore getProprietario() {
		return proprietario;
	}
	
	/**
	 * Metodo per verificare se una gerarchia ha lo stesso nome di un'altra
	 * @param nomeGerarchia
	 * @return
	 */
	public boolean eNomeUguale(String nomeGerarchia) {
		return catRadice.eUguale(nomeGerarchia);
	}
	
	public String toString() {
		return String.format("Gerarchia: %s\n"
				+ "Campo Caratteristico: %s"
				+ "\n\t Sottocategorie > \n\t\t%s", 
					getCatRadice().getNome(), 
					catRadice.getValoriCampo().keySet(),
					catRadice.stampaCateg());
	}
}
