package com.bintangSiahaanJBusAF;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

/**
 * The Payment class represents a payment entity in the JBus application, extending the Invoice class.
 * It includes information about the buyer, renter, bus, departure date, and booked seats.
 * This class provides constructors for creating payment instances, along with methods for managing seat bookings.
 * @author Bintang Siahaan
 */
public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public List<String> busSeat;
    /**
     * Constructs a Payment instance with the specified buyer, renter, bus ID, booked seats, and departure date.
     *
     * @param buyerId The ID of the buyer.
     * @param renterId The ID of the renter.
     * @param busId The ID of the bus.
     * @param busSeat The list of booked seats.
     * @param departureDate The departure date of the booked seats.
     */
    public Payment(int buyerId, int renterId, int busId, List<String> busSeat, Timestamp departureDate){
        super(buyerId, renterId);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    /**
     * Constructs a Payment instance with the specified buyer, renter, bus ID, booked seats, and departure date.
     *
     * @param buyer The buyer account associated with the payment.
     * @param renter The renter associated with the payment.
     * @param busId The ID of the bus.
     * @param busSeat The list of booked seats.
     * @param departureDate The departure date of the booked seats.
     */
    public Payment(Account buyer, Renter renter, int busId, List<String> busSeat, Timestamp departureDate){
        super(buyer.id, renter.id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;;
    }
    /**
     * Returns a string representation of the Payment object.
     *
     * @return A string containing information about the payment.
     */
    public String toString(){
        return ("id:" + super.id + "\nbuyerId:" + buyerId + "\nrenterId:" + renterId +  "\nbusId:" + busId + "\ndepartureDate:" + departureDate + "\nbusSeat:" + busSeat);
    }
    /**
     * Retrieves the ID of the bus associated with the payment.
     *
     * @return The bus ID.
     */
    public int getBusId(){
        return busId;
    }
    /**
     * Provides a formatted string representation of the departure date.
     *
     * @return A formatted string representing the departure date.
     */
    public String getDepartureInfo(){
        SimpleDateFormat SDFormat = new SimpleDateFormat("M dd, yyyy");
        return ("id:" + super.id + "\nbuyerId:" + buyerId + "\nrenterId:" + renterId + "\nbusId:" + busId + "\ndepartureDate:" + SDFormat.format(departureDate.getTime())+ "\nbusSeat:" + busSeat);
    }
    /**
     * Provides a formatted string representation of the transaction time.
     *
     * @return A formatted string representing the transaction time.
     */
    public String getTime(){
        SimpleDateFormat SDFormat = new SimpleDateFormat("M dd, yyyy hh:mm:ss");
        return SDFormat.format(this.time.getTime());
    }
    /**
     * Checks if a seat is available on the specified departure schedule for the given bus.
     *
     * @param departureSchedule The timestamp of the departure schedule.
     * @param seat The seat to check for availability.
     * @param bus The bus to check for seat availability.
     * @return True if the seat is available; false otherwise.
     */
    public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus){
        for (Schedule schedule : bus.schedules){
            if(schedule.isSeatAvailable(seat) && schedule.departureSchedule.equals(departureSchedule)){
                return true;
            }
        }
        return false;
    }
    /**
     * Attempts to make a booking for a seat on the specified departure schedule for the given bus.
     *
     * @param departureSchedule The timestamp of the departure schedule.
     * @param seat The seat to book.
     * @param bus The bus for which the booking is made.
     * @return True if the booking is successful; false otherwise.
     */
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus){
        for (Schedule schedule : bus.schedules){
            if(schedule.isSeatAvailable(seat) && schedule.departureSchedule.equals(departureSchedule)){
                schedule.bookSeat(seat);
                return true;
            }
        }
        return false;
    }
    /**
     * Finds an available schedule for a specific seat on the specified departure schedule for the given bus.
     *
     * @param departureSchedule The timestamp of the departure schedule.
     * @param seat The seat to find an available schedule for.
     * @param bus The bus to check for available schedules.
     * @return The schedule if available; otherwise, returns null.
     */
    public static Schedule availableSchedule(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureSchedule) && schedule.isSeatAvailable(seat)) {
                return schedule;
            }
        }
        return null;
    }
    /**
     * Finds available schedules for a list of seats on the specified departure schedule for the given bus.
     *
     * @param departureSchedule The timestamp of the departure schedule.
     * @param seats The list of seats to find available schedules for.
     * @param bus The bus to check for available schedules.
     * @return A list of available schedules.
     */
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
    /**
     * Attempts to make bookings for a list of seats on the specified departure schedule for the given bus.
     *
     * @param departureSchedule The timestamp of the departure schedule.
     * @param seats The list of seats to book.
     * @param bus The bus for which the bookings are made.
     * @return True if all bookings are successful; false otherwise.
     */
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