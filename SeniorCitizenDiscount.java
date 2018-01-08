
public class SeniorCitizenDiscount implements ISalePricingStrategy {
	double percentage = 0.10;
	
	public int getTotal(Sale s){
		return (int)(s.getPreDiscountTotal()-s.getPreDiscountTotal()*percentage);
	}
}
