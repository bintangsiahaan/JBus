package com.bintangSiahaanJBusAF;


import com.bintangSiahaanJBusAF.dbjson.Serializable;

/**
 * The Review class represents a review associated with a specific date and description.
 * It extends the Serializable class to support serialization.
 *
 * @author Bintang Siahaan
 */
public class Review extends Serializable
{
    public String date;
    public String desc;
    /**
     * Constructs a Review instance with the specified date and description.
     *
     * @param date The date of the review.
     * @param desc The description or content of the review.
     */
    public Review(String date, String desc){
        this.date = date;
        this.desc = desc;
    }
    /**
     * Returns a string representation of the Review object.
     *
     * @return A string containing information about the review's ID, date, and description.
     */
    public String toString(){
        return ("Id:" + super.id + "\nDate:" + date + "\nDesc:" + desc);
    }
}
