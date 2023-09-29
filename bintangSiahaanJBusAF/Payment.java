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
    SimpleDateFormat SDFormat = new SimpleDateFormat("dd MMMM yyyy");
    
    public Payment(int id, int buyerId, int renterId, String time, int busId, String busSeat){
        super(id, buyerId, renterId, time);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
        this.busSeat = busSeat;
        this.departureDate.add(Calendar.DATE, 2);

    }
    
    public Payment(int id, Account buyer, Renter renter, String time, int busId, String busSeat){
        super(id, buyer.id, renter.id, time);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
        this.busSeat = busSeat;
        this.departureDate.add(Calendar.DATE, 2);
    }
    
    public String toString(){
        return ("id:" + super.id + "\nbuyerId:" + buyerId + "\nrenterId:" + renterId +  "\nbusId:" + busId + "\ndepartureDate:" + departureDate + "\nbusSeat:" + busSeat);
    }
    
    public int getBusId(){
        return busId;
    }
    
    public String getDepartureInfo(){
        
        return ("id:" + super.id + "\nbuyerId:" + buyerId + "\nrenterId:" + renterId + "\ntime:" + time + "\nbusId:" + busId + "\ndepartureDate:" + departureDate + "\nbusSeat:" + busSeat);
    }
    
    public String getTime(){
        return SDFormat.format(this.time.getTime());
    }
}
