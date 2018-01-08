
public class PricingStrategyFactory {

	static String psname;
	ISalePricingStrategy pstrat;

	public void setPSname(String s) {
		PricingStrategyFactory.psname = s;
	}

	public ISalePricingStrategy getPricingStrategy() {

		
			try {
				pstrat = (ISalePricingStrategy) Class.forName(psname).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		return pstrat;

	}
}
