package bintangSiahaanJBusAF;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Map;

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
    
    public void addSchedule(Calendar calendar){
        this.schedules.add(new Schedule(calendar, this.capacity));
    }
    
    public void printSchedule(Schedule schedule){
        int row = 4;
        int seatNum = 1;
        SimpleDateFormat SDFormat = new SimpleDateFormat("M dd, yyyy hh:mm:ss");
        
        System.out.println("Tanggal Keberangkatan: " + SDFormat.format(schedule.departureSchedule.getTime()));
        System.out.println("Daftar kursi dan ketersediaan kursi: ");
        for(Map.Entry<String, Boolean> entry : schedule.seatAvailability.entrySet()){
            System.out.println(entry.getKey() + ":" + (entry.getValue()? "true" : "false"));
            if(seatNum % row == 0){
                System.out.println();
            }
            seatNum++;
        }
}
}
