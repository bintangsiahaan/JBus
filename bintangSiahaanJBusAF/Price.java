package bintangSiahaanJBusAF;

/**
 * Write a description of class Price here.
 *
 * @author Bintang Siahaan
 * @version (a version number or a date)
 */
public class Price {
    public double price;
    public double rebate;
    /*public int discount;*/

    public String toString(){
        return "\nPrice : " + this.price + "\nRebate : " + this.rebate;
    }
    
    public Price(double price) {
        this.price = price;
        /*this.discount = 0;*/
        this.rebate = 0;
    }

    public Price(double price, int discount) {
        this.price = price;
        /*this.discount = discount;*/
        this.rebate = 0;
    }

    public Price(double price, double rebate) {
        this.price = price;
        this.rebate = rebate;
        /*this.discount = 0;*/
    }
    
    /*private double getDiscountedPrice() {
        if (discount > 100.0) {
            discount = (int) 100.0;
            return 0;
        } else if (discount == 100.0) {
            return 0.0;
        } else {
            return price - (discount / 100.0) * price;
        }
    }*/

    private double getRebatedPrice() {
        if (rebate > price) {
            return 0;
        } else {
            return price - rebate;
        }
    }
    
    
}