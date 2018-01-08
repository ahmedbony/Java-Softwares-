import java.awt.Toolkit;

public class Beeper implements PropertyListener {
	
	ProcessSaleController psc;
	
	public void register(ProcessSaleController psc){
		psc.sale.addPropertyListener(this);
	}
	
	
	
	public void onPropertyEvent(Sale source, String name, int value) {
		if(name.equals("sale.total")){
			Toolkit.getDefaultToolkit().beep();
		}
		
	}

}
