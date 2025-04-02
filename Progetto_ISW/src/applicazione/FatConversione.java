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
	}
	
	private void aggiungiRiga(Double id) {
		ArrayList<Double> nuovaRiga = new ArrayList<>();
		nuovaRiga.add(id);
		for(int i = 1; i < fdc.size(); i++) {
			nuovaRiga.add(0.0);
		}
		fdc.add(nuovaRiga);
	}
	
	private void aggiungiColonna(Double id) {
		fdc.get(0).add(id);
		for(int i = 1; i < fdc.size(); i++) {
			fdc.get(i).add(0.0);
		}
	}
	/**
	 * per calcolare fdc(Gerarchia nuova, Gerarchia bersaglio, fdc)
	 * se ho solo due foglie
	 * fdc12 = k;
	 * fdc21 = 1/k;
	 * se ho tre foglie
	 * fdc13 = k;
	 * fdc31 = 1/k;
	 * fdc 23 = fdc21 * fdc 13 COMPRESO TRA 0.5 e 2
	 * SE
	 * fdc12
	 * fdc13 = fdc23/fdc21
	 * max fdc13 = 2 / fdc21
	 * min fdc13 = 0.5 / fdc21
	 * 
	 * se avessi 4 foglie => i rami uscenti da 1 sono 3:
	 * uno lo scelgo io, gli altri due sono x,y
	 * fdc14 ?
	 * max fdc14 = 2*min(1,x,y)
	 * min fdc14 = 0.5max(1,x,y)
	 * 
	 * se avessi n foglie
	 * n-1 rami da n
	 * fdc n
	 * max = 2*min(1,x)
	 * min = 0.5*max(1,x)
	 * @return
	 */
	/*
private Double calcolaLimiteMax(ArrayList<Tupla<Gerarchia, Double>> tuttiIFattoriUscentiDaFoglia1, Double maxFdc) {
Double max=maxFdc;
for(Tupla<Gerarchia,Double> x:tuttiIFattoriUscentiDaFoglia1) {
max=Math.min(max, maxFdc/x.getSecond());
}
return max;
}*/
	private double calcolaMAX(ArrayList<Double> ramiUscenti) {
		Double max = MAX_FDC;
		for(Double d : ramiUscenti) {
			if( d != 0.0) //0.0 solo se diagonale oppure appena riempito con valori 0.0 filler
				max = Math.min(max, MAX_FDC / d);
		}
		return max;		
	}
	/*
	private Double calcolaLimiteMin(ArrayList<Tupla<Gerarchia, Double>> tuttiIFattoriUscentiDaFoglia1, Double minFdc) {
	Double min=minFdc;
	for(Tupla<Gerarchia,Double> x:tuttiIFattoriUscentiDaFoglia1) {
	min=Math.max(min, minFdc/x.getSecond());
	}
	return min;

	}*/
	private double calcolaMIN(ArrayList<Double> ramiUscenti) {
		Double min = MIN_FDC;
		for(Double d: ramiUscenti) {
			if( d != 0.0)
				min = Math.max(min, MIN_FDC / d);
		}
		return min;
	}
	
	public void aggancia(Integer nuova) {
		Double id = nuova.doubleValue();	
		System.out.println("Id vale    " + id);
		aggiungiRiga(id);
		aggiungiColonna(id);
		aggiornaValori();
		
	}

	private void aggiornaValori() {
		int cont = fdc.size() - 1;
		System.out.println("cont vale   " + cont);
		//GGIUNGO COUNTER numero = ++numero ogni volta che devo agganciare una Integer nuova
		//hash map tra id Integer nuova e counter del numero che sto aggiungendo
		
		ArrayList<Double> ramiUscenti = fdc.get(cont);
		
		double max = calcolaMAX(ramiUscenti);
		double min = calcolaMIN(ramiUscenti);
		double k = InputDati.leggiDoubleConMINeMAX("Inserire valore fattore di conversione associato a 1 or whateva", min, max);
		//LAVORO SU RIGA
		//aggancio la prima quindi posizione 0
		fdc.get(cont).set(0, k);
		fdc.get(0).set(cont, 1 / k);

		//LAVORO SU COLONNA
		for(int i = 0; i < cont; i++) {
			//fdc[nuova][i] = fdc[nuova][1]*fdc[1][i];
			double valore = fdc.get(cont).get(0) * fdc.get(0).get(i);
			fdc.get(cont).set(i, valore);
			//fdc[i][nuova] = 1/fdc[nuova][i];
			double invertito = 1 / valore;
			fdc.get(i).set(cont, invertito);
		}
		completaVuote();
	}
	
	private void completaVuote() {
		
		for(int i = 1; i < fdc.size(); i++) {
			for(int j = 1; j < fdc.size(); j++) {
				if (fdc.get(i).get(j) == null)
						fdc.get(i).set(j, 0.0);
			}
		}
		
	}

	public void stampaFDC() {
		if(fdc.equals(null))
			System.out.println("Nessun fattore di conversione presente");
		else
			System.out.println(this.toString());
	}
	/**
	 
	//array[categorieFogli.length()][categorieFogli.length()]
	
	     array[1][3]= array[1][2]*array[2][3]
	per i che va da 1 a nuova-1
	array[i][nuova] = array[i][i+1]*array[i+1][nuova]

	private void recupera() {
		//inverso
		inserisci(1/k, rispetto, d);
		//estraggo array[di][]
		for(int i = 1; i < array[di].length(); i++) {
			
		}
	}
	*/
	//HashMap<CategoriaFoglia, double>();
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < fdc.size(); i++) {
			sb.append(fdc.get(i));
			sb.append("\n");
		}
		return String.format("STAMPA FAT CONVERSIONE > \n%s", sb.toString());
	}

}
