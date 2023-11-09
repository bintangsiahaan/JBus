package com.bintangSiahaanJBusAF;
import java.util.ArrayList;

/**
 * Write a description of class Validate here.
 *
 * @author BintangSiahaan
 * @version (a version number or a date)
 */
public class Validate
{
    public Validate(){
    }
    public static ArrayList<Double> filter(Price[] list, int value, boolean less){
        ArrayList<Double> result = new ArrayList<>();
        for (Price price : list) {
            if (less == true) {
                if (price.price < value) {
                    result.add(price.price);
                }
            } else {
                if (less == false && price.price > value) {
                    result.add(price.price);
                }
            }
        }
        return result;
    }
}
