import java.util.LinkedList;

public class ProcessSaleController {
	Sale sale;
	SaleFactory sf;
	PricingStrategyFactory psFactory;
	
	public static ProcessSaleController psc;


	void makeNewSale() {
		sale = new Sale();
		sf = new SaleFactory();
		psFactory = new PricingStrategyFactory();
	}

	LinkedList<SaleLineItem> getSaleLineItemList() {
		return sale.sli;
	}

	ProductSpecification getProductSpec(int id) {

		if (sf.isFound(id)) {
			return sf.getProductSpec(id);
		} else
			return null;

	}

	void addItem(int id, int quantity) {
		sale.addSaleLineItem(id, quantity);
	}
	
	void selectedPricingStrategy(String s){
		psFactory.setPSname(s);
	}
	public static synchronized ProcessSaleController getPscInstance(){
		if(psc==null)
			psc = new ProcessSaleController();	
		return psc;
	}


}
