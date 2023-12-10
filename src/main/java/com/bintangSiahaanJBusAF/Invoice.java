package com.bintangSiahaanJBusAF;
import com.bintangSiahaanJBusAF.dbjson.Serializable;

import java.sql.Timestamp;

/**
 * The Invoice class represents an invoice entity for a bus rental transaction.
 * It extends the com.bintangSiahaanJBusAF.dbjson.Serializable class for serialization purposes.
 * This class includes information about the transaction time, buyer ID, renter ID, bus rating, and payment status.
 * Instances of this class can be created using different constructors, and it provides a string representation for debugging.
 * @author Bintang Siahaan
 */
public class Invoice extends Serializable
{
    public Timestamp time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;
    /**
     * Protected constructor for creating an invoice with buyer and renter IDs.
     *
     * @param buyerId The ID of the buyer.
     * @param renterId The ID of the renter.
     */
    protected Invoice(int buyerId, int renterId){
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    /**
     * Public constructor for creating an invoice with buyer and renter objects.
     *
     * @param buyer The buyer account associated with the invoice.
     * @param renter The renter associated with the invoice.
     */
    public Invoice(Account buyer, Renter renter){
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    /**
     * Returns a string representation of the Invoice object.
     *
     * @return A string containing information about the invoice.
     */
    public String toString(){
        return ("\nId:" + super.id + "\nbuyerId:" + buyerId + "\nrenterId:" + renterId + "\ntime:" + time.getTime());
    }
    /**
     * Enumeration representing different ratings that can be assigned to the bus in the invoice.
     */
    public enum BusRating{
        NONE,
        NEUTRAL,
        GOOD,
        BAD
    }
    /**
     * Enumeration representing different payment statuses of the invoice.
     */
    public enum PaymentStatus{
        FAILED,
        WAITING,
        SUCCESS
    }
}
