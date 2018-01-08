
public class CompositeBestForCustomerPS extends CompositePricingStrategy {

	public CompositeBestForCustomerPS() {
		super();
	}
	
	public int getTotal(Sale s) {
		int lowestTotal = Integer.MAX_VALUE;
		
		
		for(ISalePricingStrategy strat:pricingStrategies){
			if(strat.getTotal(s)< lowestTotal){
				lowestTotal = strat.getTotal(s);
			}
		}
		
		return lowestTotal;
	}
	
}
