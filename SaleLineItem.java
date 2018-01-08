

public class SaleLineItem {

	ProductSpecification ps;
	SaleFactory sf = new SaleFactory();

	int quantity;
	//Date date;

	public SaleLineItem() {
		// TODO Auto-generated constructor stub
	}

	SaleLineItem(int id, int quantity) {

		this.ps = sf.getProductSpec(id);
		this.quantity = quantity;
		//date = new Date();
	}

	double getSubtotal() {

		return this.quantity * this.ps.price;
	}

	public ProductSpecification getPs() {
		return ps;
	}

	public void setPs(ProductSpecification ps) {
		this.ps = ps;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
