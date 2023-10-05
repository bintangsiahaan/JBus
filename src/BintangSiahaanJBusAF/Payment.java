package bintangSiahaanJBusAF;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
/**
 * Write a description of class Payment here.
 *
 * @author BintangSiahaan
 * @version (a version number or a date)
 */
public class Payment extends Invoice
{
    private int busId;
    //public Calendar departureDate;
    public Timestamp departureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate){
        super(id, buyerId, renterId);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate){
        super(id, buyer.id, renter.id);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;;
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
    
    public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus){
        for (Schedule schedule : bus.schedules){
            if(schedule.isSeatAvailable(seat) && schedule.departureSchedule.equals(departureSchedule)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus){
         for (Schedule schedule : bus.schedules){
             if(schedule.isSeatAvailable(seat) && schedule.departureSchedule.equals(departureSchedule)){
                 schedule.bookSeat(seat);
                 return true;
             }
         }
         return false;
    }
}
