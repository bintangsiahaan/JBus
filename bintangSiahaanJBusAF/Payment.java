package bintangSiahaanJBusAF;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Payment here.
 *
 * @author BintangSiahaan
 * @version (a version number or a date)
 */
public class Payment extends Invoice
{
    private int busId;
    public Calendar departureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat){
        super(id, buyerId, renterId);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
        this.busSeat = busSeat;
        this.departureDate.add(Calendar.DAY_OF_MONTH, 2);

    }
    
    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat){
        super(id, buyer.id, renter.id);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
        this.busSeat = busSeat;
        this.departureDate.add(Calendar.DAY_OF_MONTH, 2);
    }
    
    public String toString(){
        return ("id:" + super.id + "\nbuyerId:" + buyerId + "\nrenterId:" + renterId +  "\nbusId:" + busId + "\ndepartureDate:" + departureDate + "\nbusSeat:" + busSeat);
    }
    
    public int getBusId(){
        return busId;
    }
    
    public String getDepartureInfo(){
        SimpleDateFormat SDFormat = new SimpleDateFormat("M dd, yyyy");
        return ("id:" + super.id + "\nbuyerId:" + buyerId + "\nrenterId:" + renterId + "\nbusId:" + busId + "\ndepartureDate:" + SDFormat.format(departureDate.getTime())+ "\nbusSeat:" + busSeat);
    }
    
    public String getTime(){
        SimpleDateFormat SDFormat = new SimpleDateFormat("M dd, yyyy hh:mm:ss");
        return SDFormat.format(this.time.getTime());
    }
}
