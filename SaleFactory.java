import java.io.IOException;

import java.util.LinkedList;

public class SaleFactory {
	private static SaleFactory instance;
    private IVATCalculator vatCalculator;
	
	LinkedList<ProductSpecification> psList = new LinkedList<ProductSpecification>();
	ProductSpecification ps;

	SaleFactory() {
		ps = new ProductSpecification();
		ps.setId(1);
		ps.setName("Chocolate");
		ps.setDescription("Brand- Milka");
		ps.setPrice(300);

		psList.add(ps);
		ps = new ProductSpecification();
		ps.setId(2);
		ps.setName("Gummy Bear");
		ps.setDescription("Brand- Nestle");
		ps.setPrice(200);

		psList.add(ps);
		ps = new ProductSpecification();
		ps.setId(3);
		ps.setName("Chewing Gum");
		ps.setDescription("Brand- Big Bubble");
		ps.setPrice(150);

		psList.add(ps);

		ps = new ProductSpecification();
		ps.setId(4);
		ps.setName("Ice Cream");
		ps.setDescription("Brand- Igloo");
		ps.setPrice(250);

		psList.add(ps);
		ps = new ProductSpecification();
		ps.setId(5);
		ps.setName("Cotton Candy");
		ps.setDescription("Brand- Nestle");
		ps.setPrice(100);

		psList.add(ps);
		ps = new ProductSpecification();
		ps.setId(6);
		ps.setName("Brownie");
		ps.setDescription("Brand- North End");
		ps.setPrice(4500);

		psList.add(ps);

	}

	boolean isFound(int id) {
		boolean found = false;

		for (ProductSpecification a : psList) {
			if (a.getId() == id)
				found = true;
		}
		if (found)
			return true;
		else
			return false;

	}

	ProductSpecification getProductSpec(int id) {
		ProductSpecification ps = null;

		if (this.isFound(id)) {
			for (ProductSpecification a : psList) {
				if (a.getId() == id)
					ps = a;
			}
			return ps;
		} else
			return null;

	}
	
	public static synchronized SaleFactory getInstance()
    {
        if(instance == null){
            instance = new SaleFactory();
        }
   
        return instance;
    }
	
	public IVATCalculator getVatCalculator() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
	    
        if(vatCalculator == null)
        {
            Config config = new Config();
            //String className = System.getProperty("vatcalculator.class.name");
            String className = System.getProperty("vatcalculator.class.name");
            vatCalculator = (IVATCalculator) Class.forName(className).newInstance(); 
        }
        return vatCalculator;
    }
}



