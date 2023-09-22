package bintangSiahaanJBusAF;


/**
 * Write a description of class Payment here.
 *
 * @author BintangSiahaan
 * @version (a version number or a date)
 */
public class Payment extends Invoice
{
    private int busId;
    public String departureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, String time, int busId, String departureDate, String busSeat){
        super(id, buyerId, renterId, time);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, String time, int busId, String departureDate, String busSeat){
        super(id, buyer.id, renter.id, time);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public String print(){
        return ("id:" + super.id + " buyerId:" + buyerId + " renterId:" + renterId + " time:" + time + " busId:" + busId + " departureDate:" + departureDate + " busSeat:" + busSeat);
    }
    
    public int getBusId(){
        return busId;
    }
}
