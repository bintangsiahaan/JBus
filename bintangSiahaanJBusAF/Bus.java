package bintangSiahaanJBusAF;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.sql.Timestamp;

/**
 * Write a description of class Bus here.
 *
 * @author Bintang Siahaan
 * @version (a version number or a date)
 */
public class Bus extends Serializable implements FileParser
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
    
    public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival){
       super(id);
       this.name = name;
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
    
    public void addSchedule(Timestamp calendar, int capacity){
        Schedule newSchedule = new Schedule(calendar, capacity);
        schedules.add(newSchedule);
    }
    
    
}
