package bintangSiahaanJBusAF;
import java.util.Calendar;

/**
 * Write a description of class Invoice here.
 *
 * @author BintangSiahaan
 * @version (a version number or a date)
 */
public class Invoice extends Serializable
{
    public Calendar time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;
    
    protected Invoice(int id, int buyerId, int renterId, String time){
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = Calendar.getInstance();
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public Invoice(int id, Account buyer, Renter renter, String time){
        super(id);
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = Calendar.getInstance();
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public String toString(){
        return ("\nId:" + super.id + "\nbuyerId:" + buyerId + "\nrenterId:" + renterId + "\ntime:" + time);
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
