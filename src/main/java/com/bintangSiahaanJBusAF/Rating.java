package com.bintangSiahaanJBusAF;

/**
 * The Rating class represents a rating system that keeps track of the total count and sum of ratings.
 * It provides methods for inserting new ratings, calculating the average rating, and retrieving count and total.
 *
 * @author Bintang Siahaan
 */
public class Rating {
    private long count;
    private long total;
    /**
     * Constructs a Rating instance with an initial count of 0 and total sum of 0.
     */
    public Rating() {
        this.count = 0;
        this.total = 0;
    }
    /**
     * Inserts a new rating into the rating system, updating the total and count.
     *
     * @param rating The rating to be inserted.
     */
    public void insert(int rating) {
        total += rating;
        count++;
    }
    /**
     * Calculates and returns the average rating.
     *
     * @return The average rating as a double value.
     */
    public double getAverage() {
        if (count == 0) {
            return 0.0;
        }
        return (double) total / count;
    }
    /**
     * Retrieves the count of ratings.
     *
     * @return The count of ratings.
     */
    public long getCount() {
        return count;
    }
    /**
     * Retrieves the total sum of ratings.
     *
     * @return The total sum of ratings.
     */
    public long getTotal() {
        return total;
    }
    /**
     * Returns a string representation of the Rating object.
     *
     * @return A string containing information about the total and count of ratings.
     */
    public String toString(){
        return "\nTotal : " + total + "\nCount : " + count;
    }
}