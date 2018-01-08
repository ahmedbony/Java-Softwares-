
import java.util.LinkedList;

public abstract class CompositePricingStrategy implements ISalePricingStrategy {
	
	LinkedList<ISalePricingStrategy> pricingStrategies;
	ISalePricingStrategy strat = null;
	
	public void add(ISalePricingStrategy strat){		
		
		pricingStrategies.add(strat);
	}
	
	public CompositePricingStrategy() {	
		
		pricingStrategies = new LinkedList<ISalePricingStrategy>();
		
		strat = new SeniorCitizenDiscount();
		this.add(strat);
		
		strat = new EidDiscount();
		this.add(strat);
		
	}
	

	public abstract int getTotal(Sale s);

	
	
}

