package applicazione;

public class FatConversione {
	
	//array[categorieFogli.length()][categorieFogli.length()]
	//array[0][0]
	/*
	 * aggancio(nuova, categoria)
	
	aggancio(nuova, categoria)
	    calcMAX(nuova, categoria)
	    calcMIN(nuova, categoria)
	   k=fdc(min,max)
	     array[nuova][nuova-1] = k
	     array[nuova-1][nuova] = 1/k
	     array[1][3]= array[1][2]*array[2][3]
	per i che va da 1 a nuova-1
	array[i][nuova] = array[i][i+1]*array[i+1]*array[nuova]

	POI gli inversi  (?) inserisco(1/fdc[i][j], in M[j][i])

	private double recuperaMIN() {
		for()
			for()
				if(valore < min)
					min = valore
					else if(valore < 0.5)
						errore
		return 0.5;
	}
	private double recuperaMAX() {
		return 2;
	}
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
