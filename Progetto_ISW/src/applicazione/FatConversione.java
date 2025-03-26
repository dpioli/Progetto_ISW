package applicazione;

import java.util.ArrayList;

import util.InputDati;

public class FatConversione {
	
	private double[][] fdc = new double[0][0];
	private static double MAX_FDC = 2;
	private static double MIN_FDC = 0.5;
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
	private double calcolaMAX(double[] ramiUscenti) {
		Double max = MAX_FDC;
		for(Double d : ramiUscenti) {
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
	private double calcolaMIN(double[] ramiUscenti) {
		Double min = MIN_FDC;
		for(Double d: ramiUscenti) {
			min = Math.max(min, MIN_FDC / d);
		}
		return min;
	}
	public void aggancia(Integer nuova) {
		double max = calcolaMAX(fdc[nuova]);
		double min = calcolaMIN(fdc[nuova]);
		
		double k = InputDati.leggiDoubleConMINeMAX("Inserire valore fattore di conversione associato a 1 or whateva", min, max);
		//aggancio alla categoria 1
		fdc[nuova][1] = k;
		fdc[1][nuova] = 1/k;
		for(int i = 2; i < nuova; i++) {
			fdc[nuova][i] = fdc[nuova][1]*fdc[1][i];
			fdc[i][nuova] = 1/fdc[nuova][i];
		}
		
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
		return String.format("STSMPA FAT CONVERSIONE > ", null);
	}

}
