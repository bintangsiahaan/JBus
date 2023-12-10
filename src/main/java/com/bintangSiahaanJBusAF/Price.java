package com.bintangSiahaanJBusAF;

/**
 * The Price class represents the price and rebate information for a product or service.
 * It includes methods to calculate the discounted price and provides constructors for creating Price instances.
 *
 * @author Bintang Siahaan
 */
public class Price {
    public double price;
    public double rebate;
    /**
     * Returns a string representation of the Price object.
     *
     * @return A string containing information about the price and rebate.
     */
    public String toString(){
        return "\nPrice : " + this.price + "\nRebate : " + this.rebate;
    }
    /**
     * Constructs a Price instance with the specified original price.
     *
     * @param price The original price.
     */
    public Price(double price) {
        this.price = price;
        this.rebate = 0;
    }
    /**
     * Constructs a Price instance with the specified original price and discount.
     *
     * @param price The original price.
     * @param discount The discount to be applied.
     */
    public Price(double price, int discount) {
        this.price = price;
        this.rebate = 0;
    }
    /**
     * Constructs a Price instance with the specified original price and rebate.
     *
     * @param price The original price.
     * @param rebate The rebate amount.
     */
    public Price(double price, double rebate) {
        this.price = price;
        this.rebate = rebate;
    }
    /**
     * Calculates the discounted price after applying the rebate.
     *
     * @return The discounted price.
     */
    private double getRebatedPrice() {
        if (rebate > price) {
            return 0;
        } else {
            return price - rebate;
        }
    }
    
    
}