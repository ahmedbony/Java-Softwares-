public class MyVATCalculator implements IVATCalculator {
    
    public int getVatAmount(double saleTotal) {
        return (int) Math.round(saleTotal * 0.05);
    }
}
