package applicazione;

import java.util.ArrayList;

import util.InputDati;

public class FatConversione {
	
	private ArrayList<ArrayList<Double>> fdc;
	
	private static double MAX_FDC = 2;
	private static double MIN_FDC = 0.5;
	
	public FatConversione(ArrayList<ArrayList<Double>> fdc) {
		this.fdc = fdc;
	}
	public FatConversione() {
		this.fdc = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> id = new ArrayList<>();
		id.add(00.00);
		fdc.add(id);
	}
	/**
	 * Metodo che aggancia una nuova foglia alle altre (partendo sempre da F1).
	 * @param ID nuova aggiunta
	 */
	public void aggancia(Integer nuova) {
		Double id = nuova.doubleValue();	

		aggiungiRiga(id);
		aggiungiColonna(id);
		//System.out.println("prima dell'aggiornamento di valori:\n\n " + fdc.toString() );
		aggiornaValori();
	}
	
	private void aggiungiRiga(Double id) {
		fdc.get(0).add(id);
		
		ArrayList<Double> nuovaRiga = new ArrayList<>();
		// devo aggiungere NUOVO e1 e2 e3 ... 0.0
		nuovaRiga.add(id); //aggiungo NUOVO
		for(int i = 1; i < fdc.size(); i++) { //aggiungo i valori impostati a 0.0(default) in corrispondenza di quelli già esistenti
			nuovaRiga.add(0.0);
		}
		fdc.add(nuovaRiga);
	}
	
	private void aggiungiColonna(Double id) {   //QUI fdc.size() corrisponde a fdc con la riga aggiunta
		//fdc.get(0).add(id); //alla prima riga (degli ID) aggiungo quello nuovo => nuova colonna
	//	System.out.println("aggiungo id in colonna" + fdc.toString());
		for(int i = 1; i < fdc.size(); i++) { //completo la colonna con valori impostati a zero
			fdc.get(i).add(0.0);
		}
	}
	
	/**
	 * Questo metodo permette di calcolare il massimo che può assumere il FDC.
	 * Se considero F1,F2 nodi di cui ho i fattori di conversione. OUTesistente 2->1
	 * Inserisco il nuovo nodo F3.
	 * OUTnuovo 2 -> 3, sarà un valore compreso tra 0,5 e 2
	 * Sapendo che la transizione 2 -> 3 = ( TR 2->1 ) * ( TR 1->3 )
	 * ( TR 1 -> 3 ) =   OUTnuovo/ OUTesistente
	 * Il discorso è analogo nel verso opposto.
	 * @param ramiUscenti dal primo nodo
	 * @return il valore minimo tra 2.0 e il massimo calcolato
	 */
	private double calcolaMAX(ArrayList<Double> ramiUscenti) {
		Double max = MAX_FDC;
		for(Double d : ramiUscenti) {
			if( d != 0.0) //0.0 solo se diagonale oppure appena riempito con valori 0.0 filler
				max = Math.min(max, MAX_FDC / d);
		}
		return max;		
	}
	
	/**
	 * Questo metodo permette di calcolare il minimo che può assumere il FDC.
	 * Se considero F1,F2 nodi di cui ho i fattori di conversione. OUTesistente 2->1
	 * Inserisco il nuovo nodo F3.
	 * OUTnuovo 2 -> 3, sarà un valore compreso tra 0,5 e 2
	 * Sapendo che la transizione 2 -> 3 = ( TR 2->1 ) * ( TR 1->3 )
	 * ( TR 1 -> 3 ) =   OUTnuovo/ OUTesistente
	 *  Il discorso è analogo nel verso opposto.
	 * @param ramiUscenti dal primo nodo
	 * @return il valore massimo tra 0.5 e il massimo calcolato
	 */
	private double calcolaMIN(ArrayList<Double> ramiUscenti) {
		Double min = MIN_FDC;
		for(Double d: ramiUscenti) {
			if( d != 0.0)  //ignoro i valori di default
				min = Math.max(min, MIN_FDC / d);
		}
		return min;
	}
	


	private void aggiornaValori() { //ATTENZIONE LA POSIZIONE 0 (riga e colonna) E' OCCUPATA DAGLI id
		
		int cont = fdc.size() - 1; //punto a prendere il nuovo arrivato**
		System.out.println("cont vale   " + cont);
		if(cont == 1) return;
		
		ArrayList<Double> ramiUscenti = fdc.get(1); //prendo rami uscenti dalla prima foglia che userò sempre come riferimento
		
		double max = calcolaMAX(ramiUscenti);
		double min = calcolaMIN(ramiUscenti);
		System.out.printf("\nMIN : %f \nMAX : %f\n", min, max);
		double k = InputDati.leggiDoubleConMINeMAX("Inserire valore fattore di conversione associato a 1 or whateva", min, max);
		
		//aggancio la prima quindi posizione 0
		fdc.get(cont).set(1, k); 
		//RICAVO INVERSO
		fdc.get(1).set(cont, 1 / k);
		System.out.println("\nper ora : \n" + fdc.toString());

		// COMPLETO SAPENDO CHE LE TRANSIZIONI SONO PRODOTTO DEL NUOVO INSERIMENTO (k) CON USCENTI DALLA PRIMA FOGLIA
		for(int i = 2; i < cont; i++) {
			//fdc[nuova][i] = fdc[nuova][1]*fdc[1][i];
			//double valore = fdc.get(cont).get(0) * fdc.get(0).get(i);
			double valore = k * ramiUscenti.get(i);
			fdc.get(cont).set(i, valore);
			//COMPLETO LA COLONNA CON L'INVERSO
			//fdc[i][nuova] = 1/fdc[nuova][i];
			double invertito = 1 / valore;
			fdc.get(i).set(cont, invertito);
		}
		fdc.get(cont).set(cont, 0.0); //completo la diagonale
		//completaVuote();
		System.out.println("\naggiornati i valori risulta:\n" + fdc.toString());
	}
	
/*	private void completaVuote() { //in teoria non serve wtf
		
		for(int i = 1; i < fdc.size(); i++) {
			for(int j = 1; j < fdc.size(); j++) {
				if ( i == j )
					fdc.get(i).set(j, 0.0);
				if (fdc.get(i).get(j) == null)
						fdc.get(i).set(j, 0.0);
			}
		}
		
	}
*/
	public void stampaFDC() {
		if(fdc.equals(null))
			System.out.println("Nessun fattore di conversione presente");
		else
			System.out.println(this.toString());
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < fdc.size(); i++) {
			sb.append(fdc.get(i));
			sb.append("\n");
		}
		return String.format("STAMPA FAT CONVERSIONE > \n%s", sb.toString());
	}

}
