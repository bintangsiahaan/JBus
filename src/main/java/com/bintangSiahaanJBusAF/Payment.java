package com.bintangSiahaanJBusAF;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

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

    public Payment(int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate){
        super(buyerId, renterId);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }

    public Payment(Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate){
        super(buyer.id, renter.id);
        this.buyerId = buyerId;
        this.renterId = renterId;
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

    public static Schedule availableSchedule(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureSchedule) && schedule.isSeatAvailable(seat)) {
                return schedule;
            }
        }
        return null;
    }

    public static List<Schedule> availableSchedule(Timestamp departureSchedule, List<String> seats, Bus bus) {
        List<Schedule> availableSchedules = new ArrayList<>();
        for (String seat : seats) {
            Schedule schedule = availableSchedule(departureSchedule, seat, bus);
            if (schedule != null) {
                availableSchedules.add(schedule);
            }
        }
        return availableSchedules;
    }

    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus) {
        boolean bookingSuccessful = true;
        for (String seat : seats) {
            boolean booked = false;
            for (Schedule schedule : bus.schedules) {
                if (schedule.isSeatAvailable(seat) && schedule.departureSchedule.equals(departureSchedule)) {
                    schedule.bookSeat(seat);
                    booked = true;
                    break;
                }
            }
            if (!booked) {
                bookingSuccessful = false;
                break;
            }
        }
        return bookingSuccessful;
    }

}