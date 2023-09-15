package bintangSiahaanJBusAF;

/**
 * Write a description of class Price here.
 *
 * @author Bintang Siahaan
 * @version (a version number or a date)
 */
public class Price {
    double rebate, price;
    int discount;

    public Price(double price) {
        this.price = price;
        this.discount = 0;
        this.rebate = 0;
    }

    public Price(double price, int discount) {
        this.price = price;
        this.discount = discount;
        this.rebate = 0;
    }

    public Price(double price, double rebate) {
        this.price = price;
        this.rebate = rebate;
        this.discount = 0;
    }

    public int getDiscountedPrice() {
        if (discount > 100.0) {
            discount = 100;
            return discount;
        } else if (discount == 100.0) {
            return 0;
        } else {
            return (int) (price - (discount / 100.0) * price);
        }
    }

    public double getRebatedPrice() {
        if (rebate > price) {
            System.out.println("Invalid, input tidak bisa negatif");
            return 0;
        } else {
            return price - rebate;
        }
    }
}