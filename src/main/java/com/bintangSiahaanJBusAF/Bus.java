package com.bintangSiahaanJBusAF;
import com.bintangSiahaanJBusAF.dbjson.Serializable;
import com.bintangSiahaanJBusAF.Price;

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
    public int accountId;
    public int capacity; // Mengubah tipe variabel capacity menjadi int
    public List<Facility> facilities;
    public String name;
    public double price;
    public BusType busType;
    public Station departure;
    public Station arrival;
    public List<Schedule> schedules;

    public Bus(int accountId, String name, int capacity, List<Facility> facilities, BusType busType, double price, Station departure, Station arrival) {
        this.accountId = accountId;
        this.name = name;
        this.capacity = capacity;
        this.facilities = facilities;
        this.busType = busType;
        this.price = price;
        this.departure = departure;
        this.arrival = arrival;
        this.schedules = new ArrayList<>();
    }

    public String toString(){
        return "\nId : " + super.id + "\nNama : " + name + "\nFasilitas : " + facilities + "" + price + "\nCapacity : " + capacity +
                "\nBus Type : " + busType + "" + departure + "" + arrival;
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
                Schedule newSchedule = new Schedule(calendar, this.capacity); // Menggunakan this.capacity karena variabel capacity telah diubah ke tipe int
                schedules.add(newSchedule);
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambahkan jadwal: " + e.getMessage());
        }

        Schedule newSchedule = new Schedule(calendar, this.capacity); // Menggunakan this.capacity karena variabel capacity telah diubah ke tipe int
        schedules.add(newSchedule);
    }
}
