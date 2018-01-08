
public class CompositeBestForStorePS extends CompositePricingStrategy {

	public int getTotal(Sale s) {
		int highestTotal = Integer.MIN_VALUE;
		
		for(ISalePricingStrategy strat:pricingStrategies){
			if(strat.getTotal(s)>highestTotal){
				highestTotal = strat.getTotal(s);
			}
		}
		
		return highestTotal;
	}
}
