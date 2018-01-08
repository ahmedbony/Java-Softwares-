
public class EidDiscount implements ISalePricingStrategy{
	int discount = 100;
	int threshold = 1000;
	
	
	public int getTotal(Sale s){
		int preDiscountTotal= s.getPreDiscountTotal();
		if(s.getPreDiscountTotal()>=1000){
			//int multiplier = preDiscountTotal/1000;
			return s.getPreDiscountTotal()-100; 
		}
		else 
			return s.getPreDiscountTotal();
	}
}
