package uniandes.algorithms.coinchange;

public class DynamicProgrammingCoinChangeAlgorithm implements CoinChangeAlgorithm {

	@Override
	public int[] calculateOptimalChange(int totalValue, int[] denominations) {
		// TODO Auto-generated method stub
		int[][] matriz;
		int[] monedas;
		
		//	Llenar matriz
		matriz = matrizDenomTotalvalue(totalValue, denominations);
		
		//	Calcular solucion
		monedas = arregloMonedas(totalValue, denominations, matriz);
		
		/*
		printMatrix(matriz);	
		System.out.println("\n");
		printArray(monedas);
		*/
		
		return monedas;
	}
	
	private int[][] matrizDenomTotalvalue(int totalValue, int[] denominations) {
		int[][] matriz = new int[denominations.length][totalValue + 1];
	
		for (int indDenom = 0; indDenom < matriz.length; indDenom++) {
			for (int t = 0; t < matriz[0].length; t++) {
				/*
				 * Casos base
				 */
				
				//	Si el valor total es cero (t = 0); entonces la cantidad de monedas a tomar para cada denominacion es cero (0)
				if ( t == 0 )
					matriz[indDenom][t] = 0;
				
				//	Si se toma la primera denominacion (d_1 = 1), (indDenom = 0); entonces la cantidad de monedas a tomar para cada
				//	es igual al valor total en dicho punto de la iteración (t).
				if ( indDenom == 0 )
					matriz[indDenom][t] = t;
				
				/*
				 * Casos recursivos
				 */
				
				if ( (t > 0) && (indDenom > 0) ) {
					if ( t >= denominations[indDenom] ) {
						matriz[indDenom][t] = Math.min( ( matriz[indDenom - 1][t] ), ( matriz[indDenom][t - denominations[indDenom]] + 1 ) );
					}
					else {
						matriz[indDenom][t] = matriz[indDenom - 1][t];
					}
				}
			}
		}
		
		return matriz;
	}
	
	private int[] arregloMonedas(int totalValue, int[] denominations, int[][] matriz) {
		int[] monedas = new int[denominations.length];
		
		for (int j = 0; j < monedas.length; j++) {
			monedas[j] = 0;
		}
		
		int tV = totalValue;
		int indDenom = ( matriz.length - 1 );
		
		while (indDenom > 0) {
			if ( matriz[indDenom][tV] == matriz[indDenom - 1][tV] ) {
				indDenom = indDenom - 1;
			}
			else {
				if ( ( tV - denominations[indDenom] ) >= 0 ) {
					if ( matriz[indDenom][tV] != matriz[indDenom][tV - denominations[indDenom]] ) {
						monedas[indDenom] = monedas[indDenom] + 1;
						tV = tV - denominations[indDenom];
					}
				}
			}
		}

		if (tV > 0) {
			monedas[0] = tV;
		}
		
		return monedas;
	}
	
	private void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
		    for (int j = 0; j < matrix[0].length; j++) {
		        System.out.print(matrix[i][j] + "\t");
		    }
		    System.out.println();
		}
	}
	
	private void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
	        System.out.print(array[i] + "\t");
		}
	}
	
	/*
	public static void main(String[] args) {
		DynamicProgrammingCoinChangeAlgorithm dp = new DynamicProgrammingCoinChangeAlgorithm();
		int[] denom = {1,5,6};
		dp.calculateOptimalChange(22, denom);
	}
	*/

}
