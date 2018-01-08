import java.io.IOException;
import java.util.LinkedList;

public class Sale {

	private IVATCalculator ivac;
	ISalePricingStrategy pricingStrategy;
	PricingStrategyFactory psFactory;
	int saleTotal;
	int vatAmount;

	LinkedList<SaleLineItem> sli;
	LinkedList<PropertyListener> listeners;	

	Sale() {
		sli = new LinkedList<SaleLineItem>();
		psFactory = new PricingStrategyFactory();
		
		
	}
	
	int getPreDiscountTotal() {
		int total = 0;

		for (SaleLineItem f : sli) {
			total = (int) (total + f.getSubtotal());
		}
		return total;

	}

	void addSaleLineItem(int id, int quantity) {
		SaleLineItem item = new SaleLineItem(id, quantity);

		sli.add(item);

	}
	public void addPropertyListener(PropertyListener lis){
		listeners.add(lis);
	}	
	
	
	public void publishPropertyEvent(String name, int value){
		for(PropertyListener lis:listeners){
			lis.onPropertyEvent(this, name, value);
		}
	}


	double getTotal() {
		
		pricingStrategy = psFactory.getPricingStrategy();
		return pricingStrategy.getTotal(this);

	}
	
	public void setTotal(){
		this.saleTotal = this.getPreDiscountTotal();
		publishPropertyEvent("sale.total", this.saleTotal);
	}
	
	public void setVatAmount() throws Exception, InstantiationException, IllegalAccessException, IOException{
		this.vatAmount = this.getVATAmount();
		publishPropertyEvent("vat.Amount", this.vatAmount);
	}
	
	public int getVATAmount() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
		this.saleTotal = this.getPreDiscountTotal();
		ivac = SaleFactory.getInstance().getVatCalculator();
		return ivac.getVatAmount(saleTotal);
    }
    public double getGrandTotal() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
    
    	return this.getVATAmount()+this.getTotal();
    }

	/*public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}*/
}
