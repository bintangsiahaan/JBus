package com.bintangSiahaanJBusAF;


import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

/**
 * The Bus class represents a bus entity with various attributes such as name, facilities, capacity, price, and schedules.
 * It extends the com.bintangSiahaanJBusAF.dbjson.Serializable class to support serialization.
 * @author Bintang Siahaan
 */
public class Bus extends com.bintangSiahaanJBusAF.dbjson.Serializable implements Serializable {
    public BusType busType;
    public List<Facility> facilities;
    public int capacity;
    public Price price;
    public Station departure;
    public Station arrival;
    public String name;
    public List<Schedule> schedules = new ArrayList <Schedule>();
    public int accountId;
    /**
     * Constructs a Bus object with the specified attributes.
     *
     * @param name The name of the bus.
     * @param facility The list of facilities available on the bus.
     * @param price The price information for the bus.
     * @param capacity The seating capacity of the bus.
     * @param busType The type of the bus.
     * @param departure The departure station of the bus.
     * @param arrival The arrival station of the bus.
     */
    public Bus(String name, List<Facility> facility, Price price , int capacity, BusType busType, Station departure, Station arrival, int accountId){
        this.name = name;
        this.facilities = facility;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.departure = departure;
        this.arrival = arrival;
        this.accountId = accountId;
    }
    /**
     * Default constructor for the Bus class.
     */
    public Bus(){}
    /**
     * Retrieves the list of schedules associated with the bus.
     *
     * @return The list of schedules.
     */
    public List<Schedule> getSchedules (){
        return this.schedules;
    }
    /**
     * Returns a string representation of the Bus object.
     *
     * @return A string containing information about the bus.
     */
    public String toString(){
        return "\n===Bus Class===\n"  + "\nName = " + this.name + "\nFacility = " + this.facilities + this.price + "\nCapacity = " + this.capacity + "\nBus Type = " + this.busType + "\n==Departure==" + this.departure + "\n==Arrival==" + this.arrival ;
    }
    /**
     * Adds a new schedule to the list of schedules associated with the bus.
     *
     * @param schedule The timestamp of the new schedule.
     */
    public void addSchedule(Timestamp schedule){
        Schedule newSchedule = new Schedule (schedule, this.capacity);
        try {
            if(!schedules.contains(newSchedule)) {
                schedules.add(newSchedule);
            }
        }
        catch (Exception e){
            System.out.println("Double Schedule");
        }

    }
    /**
     * Reads information related to the bus from a source with the specified name.
     *
     * @param name The name of the source.
     * @return Always returns true (dummy implementation).
     */
    public boolean read(String  name) {
        return true;

    }
    /**
     * Writes information related to the bus.
     *
     * @return Always returns Object.class (dummy implementation).
     */
    public Object write(){
        return Object.class;
    }
    /**
     * Formats the given calendar into a readable string representation.
     *
     * @param calendar The calendar to be formatted.
     * @return A formatted string representing the calendar.
     */
    private String formatCalendar(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }
}