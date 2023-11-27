package com.bintangSiahaanJBusAF;

import com.bintangSiahaanJBusAF.dbjson.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class Bus extends Serializable
{
    public String name;
    public Station departure;
    public Station arrival;
    public Price price;
    public List<Facility> facilities;
    public int capacity;
    public BusType busType;
    public List<Schedule> schedules;
    public int accountId;

    public Bus(String name, List<Facility> facilities, Price price, int capacity,  BusType busType,
                Station departure, Station arrival){
        this.name = name;
        this.facilities = facilities;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.departure = departure;
        this.arrival = arrival;
        this.schedules = new ArrayList<>();
    }
    public String toString(){
        return "ID: " + super.id + "; Name: " + name + "; Facility: " + facilities + "; " + price + "; Capacity: " + capacity + "; Bus Type: " + busType + "\n" + departure + "\n" + arrival;
    }
    public boolean read(String file){
        return false;
    }
    public Object write(){
        return null;
    }
    public void addSchedule(Timestamp departureSchedule) {
        try {
            boolean isDuplicate = false;
            for (Schedule scheduleTest : schedules) {
                if (scheduleTest.departureSchedule.equals(departureSchedule)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate) {
                System.out.println("Ditemukan jadwal duplikat");
            }
            else {
                Schedule newSchedule = new Schedule(departureSchedule, capacity);
                schedules.add(newSchedule);
            }
        }
        catch (RuntimeException  e) {
            System.out.println(e.getMessage());
        }
    }
}