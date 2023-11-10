package com.bintangSiahaanJBusAF;
import com.bintangSiahaanJBusAF.dbjson.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

/**
 * Write a description of class Bus here.
 *
 * @author Bintang Siahaan
 * @version (a version number or a date)
 */
public class Bus extends Serializable
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public BusType busType;
    public City city;
    public Station departure;
    public Station arrival;
    public List<Schedule> schedules;
    
    public Bus(String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival){this.name = name;
       this.facility = facility;
       this.price = price;
       this.capacity = capacity;
       this.busType = busType;
       this.city = city;
       this.departure = departure;
       this.arrival = arrival;
       this.schedules = new ArrayList<>();
    }
    
    public String toString(){
        return "\nId : " + super.id + "\nNama : " + name + "\nFasilitas : " + facility + "" + price + "\nCapacity : " + capacity +
               "\nBus Type : " + busType + "\nCity : " + city + "" + departure + "" + arrival;
    }
    
    public Object write() {
        return null;
    }

    public boolean read(String string) {
        return false;
    }
    
    public void addSchedule(Timestamp calendar){
        try {
            boolean isDuplicate = false;
            for (Schedule existingSchedule : schedules) {
                if (existingSchedule.departureSchedule.equals(calendar)) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                System.out.println("Jadwal dengan waktu yang sama sudah ada.");
            } else {
                Schedule newSchedule = new Schedule(calendar, capacity);
                schedules.add(newSchedule);
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambahkan jadwal: " + e.getMessage());
        }

        Schedule newSchedule = new Schedule(calendar, capacity);
        schedules.add(newSchedule);

    }
    
}
