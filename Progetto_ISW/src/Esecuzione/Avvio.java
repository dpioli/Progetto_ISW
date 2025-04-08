package Esecuzione;

import Persistenza.GestorePersistenza;
import Persistenza.LogicaPersistenza;
import menu.MenuPrincipale;

/**
 * Classe Main del programma
 * @author Erjona Maxhaku 735766      Diego Pioli 736160
 *
 */
public class Avvio {

	public static void main(String[] args) {
		GestorePersistenza gestore= new GestorePersistenza();
		LogicaPersistenza logica = new LogicaPersistenza();
		MenuPrincipale menu = new MenuPrincipale(logica);
		menu.azioniMenuPrincipale();
	}

}
