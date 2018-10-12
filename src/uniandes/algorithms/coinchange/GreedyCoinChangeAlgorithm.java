package uniandes.algorithms.coinchange;

public class GreedyCoinChangeAlgorithm implements CoinChangeAlgorithm {

	@Override
	public int[] calculateOptimalChange(int totalValue, int[] denominations) {
		// TODO Auto-generated method stub
		int[] monedas = new int[denominations.length];
		int restante = totalValue;
		
		for (int i = (denominations.length - 1); i >= 0; i--) {
			int nMonedas = (int) (restante/denominations[i]);
			
			if ( nMonedas > 0) {
				monedas[i] = nMonedas;
				restante = restante - (denominations[i] * nMonedas);
			}
			else {
				monedas[i] = 0;
			}
		}
		
		return monedas;
	}

}
