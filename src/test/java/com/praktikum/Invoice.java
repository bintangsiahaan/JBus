package bintangSiahaanJBusAF;
import java.util.Calendar;
import java.sql.Timestamp;

/**
 * Write a description of class Invoice here.
 *
 * @author BintangSiahaan
 * @version (a version number or a date)
 */
public class Invoice extends Serializable
{
    public Timestamp time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;

    protected Invoice(int buyerId, int renterId){
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    public Invoice(Account buyer, Renter renter){
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    public String toString(){
        return ("\nId:" + super.id + "\nbuyerId:" + buyerId + "\nrenterId:" + renterId + "\ntime:" + time.getTime());
    }

    public enum BusRating{
        NONE,
        NEUTRAL,
        GOOD,
        BAD
    }

    public enum PaymentStatus{
        FAILED,
        WAITING,
        SUCCESS
    }
}
