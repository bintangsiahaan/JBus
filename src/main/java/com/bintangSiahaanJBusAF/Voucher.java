package com.bintangSiahaanJBusAF;


import com.bintangSiahaanJBusAF.dbjson.Serializable;

/**
 * The {@code Voucher} class represents a voucher that can be applied to a {@code Price} object.
 * It extends the {@code Serializable} class.
 *
 * @author Bintang Siahaan
 */
public class Voucher extends Serializable
{
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;

    /**
     * Constructs a new voucher with the specified parameters.
     *
     * @param name     The name of the voucher.
     * @param code     The unique code associated with the voucher.
     * @param type     The type of the voucher, either {@code Type.REBATE} or {@code Type.DISCOUNT}.
     * @param minimum  The minimum amount required for the voucher to be applicable.
     * @param cut      The discount or cut value applied by the voucher.
     */
    public Voucher(String name, int code, Type type, double minimum, double cut){
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }
    /**
     * Checks if the voucher has been used.
     *
     * @return {@code true} if the voucher has been used, {@code false} otherwise.
     */
    public boolean isUsed(){
        return this.used;
    }
    /**
     * Checks if the voucher can be applied to the given price.
     *
     * @param price The price to check against the voucher.
     * @return {@code true} if the voucher can be applied, {@code false} otherwise.
     */
    public boolean canApply(Price price){
        if (price.price > this.minimum && this.used == false){
            return true;
        }
        return false;
    }
    /**
     * Applies the voucher to the given price and returns the discounted amount.
     * Marks the voucher as used after applying.
     *
     * @param price The price to apply the voucher to.
     * @return The discounted amount after applying the voucher.
     */
    public double apply(Price price){
        this.used = true;
        if (this.type == Type.DISCOUNT){
            if(this.cut > 100.0){
                this.cut = 100.0;
                return 0;
            }
            return price.price - (price.price * this.cut / 100);
        }
        else {
            if (this.cut > price.price){
                this.cut = price.price;
            }
            return price.price - this.cut;
        }
    }
    /**
     * Writes the voucher data. This method is not implemented and always returns {@code null}.
     *
     * @return Always {@code null}.
     */
    public Object write() {
        return null;
    }
    /**
     * Reads the voucher data from a string. This method is not implemented and always returns {@code false}.
     *
     * @param string The string to read the voucher data from.
     * @return Always {@code false}.
     */
    public boolean read(String string) {
        return false;
    }
}
