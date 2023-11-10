package com.bintangSiahaanJBusAF;


import com.bintangSiahaanJBusAF.dbjson.Serializable;

/**
 * Write a description of class Voucher here.
 *
 * @author Bintang Siahaan
 * @version (a version number or a date)
 */
public class Voucher extends Serializable
{
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;
    
    public Voucher(String name, int code, Type type, double minimum, double cut){
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }
    
    public boolean isUsed(){
        return this.used;
    }
    
    public boolean canApply(Price price){
        if (price.price > this.minimum && this.used == false){
            return true;
        }
        return false;
    }
    
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
    
    public Object write() {
        return null;
    }

    public boolean read(String string) {
        return false;
    }
}
