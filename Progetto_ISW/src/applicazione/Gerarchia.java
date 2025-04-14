package applicazione;

import java.util.ArrayList;

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
		return String.format("\nGerarchia: %s -> %s\n"
				+ "Sottocategorie > \n\t\t%s"
				+ "\n", 
					getCatRadice().getNome(), 
					catRadice.getValoriCampo().toString(),
					catRadice.stampaCateg());
	}
	
}
/*
 *   {
    "catRadice": {
      "nome": "Sistemi Operativi ed Impianti Informatici",
      "campCaratt": {
        "nomeCampo": "campo",
        "valoriCampo": {
          "Sistemi Operativi": "",
          "Impianti Informatici": ""
        }
      },
      "sottoCateg": [
        {
          "nome": "Lezioni SO",
          "campCaratt": {
            "nomeCampo": "campo",
            "valoriCampo": {
              "Esercizi SO": "",
              "Teoria SO": ""
            }
          },
          "sottoCateg": [
            {
              "nome": "Lezioni Teoria SO",
              "sottoCateg": [],
              "foglia": false
            },
            {
              "nome": "Lezioni Esercizi SO",
              "sottoCateg": [],
              "foglia": false
            }
          ],
          "foglia": false
        },
        {
          "nome": "Lezioni ImpInf",
          "campCaratt": {
            "nomeCampo": "campo",
            "valoriCampo": {
              "Teoria ImpInf": "",
              "Esercizi ImpInf": ""
            }
          },
          "sottoCateg": [
            {
              "nome": "Lezioni Teoria ImpInf",
              "sottoCateg": [],
              "foglia": false
            },
            {
              "nome": "Lezioni Esercizi ImpInf",
              "sottoCateg": [],
              "foglia": false
            }
          ],
          "foglia": false
        }
      ],
      "foglia": false
    },
    "proprietario": {
      "username": "DiegoP",
      "password": "ProgettoISW"
    }
  }
 * */
