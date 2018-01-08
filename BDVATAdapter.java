public class BDVATAdapter implements IVATCalculator{
    private BDVATCalculator b;
    
    public BDVATAdapter(){
        b = new BDVATCalculator();
    }
    
    public int getVatAmount(double saleTotal) {
    	
        int total =   (int) b.calculateVatAmount(saleTotal);
     return total;
    }   
}
