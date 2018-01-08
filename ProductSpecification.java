
public class ProductSpecification {

	private int id;
	private String name;
	private String description;
	double price;

	public ProductSpecification() {
		// TODO Auto-generated constructor stub
	}

	/*ProductSpecification(String description) {
		this.description = description;
	}*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/*public String toString() {
		return "Product ID: " + this.id + ", Name: " + this.name + " Price: " + this.price;
	}*/

}
