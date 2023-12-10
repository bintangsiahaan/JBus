package com.bintangSiahaanJBusAF;
import java.util.ArrayList;

/**
 * The Validate class provides utility methods for validating and filtering Price objects.
 * It contains a static method to filter an array of Price objects based on a specified condition.
 * The class is designed to assist in data validation and extraction based on specific criteria.
 *
 * @author Bintang Siahaan
 * @version (a version number or a date)
 */
public class Validate
{
    /**
     * Default constructor for the Validate class.
     */
    public Validate(){
    }
    /**
     * Filters an array of Price objects based on a specified condition.
     * Returns a list of prices that meet the filtering criteria.
     *
     * @param list  The array of Price objects to be filtered.
     * @param value The threshold value for filtering.
     * @param less  A boolean flag indicating whether to filter values less than the threshold (true) or greater (false).
     * @return An ArrayList of Double values representing the filtered prices.
     */
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
