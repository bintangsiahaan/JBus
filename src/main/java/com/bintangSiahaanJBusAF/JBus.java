package com.bintangSiahaanJBusAF;
import com.bintangSiahaanJBusAF.dbjson.JsonDBEngine;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

/**
 * Write a description of class JBus here.
 *
 * @author Bintang Siahaan
 * @version (a version number or a date)
 */

@SpringBootApplication
public class JBus {
    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25,
                BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK,
                "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        bus.addSchedule(timestamp);
        return bus;
    }
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(JBus.class, args);
        JsonDBEngine.Run(JBus.class);
        Runtime.getRuntime().addShutdownHook(new Thread(()-> JsonDBEngine.join()));
        /*try {
            String filepath =
                    "D:\\KULIAH\\SEMESTER 3\\OOP\\praktikum\\data\\buses_CS.json";
            JsonTable<Bus> busList = new JsonTable<>(Bus.class,filepath);
            List<Bus> filteredBus = filterByDeparture(busList,City.JAKARTA,1,10);
            filteredBus.forEach(bus -> System.out.println(bus.toString()));
        }
        catch (Throwable t){
            t.printStackTrace();
        }*/
        try{
            String filepath = "D:\\\\KULIAH\\\\SEMESTER 3\\\\OOP\\\\praktikum\\\\data\\\\accountDatabase.json";
            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);
            tableAccount.add(new Account("Dio", "dio@gmail.com", "NgikNgok"));
            tableAccount.writeJson();
            System.out.println(tableAccount);
            Bus bus = createBus();
            bus.schedules.forEach(Schedule::printSchedule);
            for(int i =0; i < 10; i++){
                BookingThread thread = new BookingThread("Thread " + i,bus,
                        Timestamp.valueOf("2023-07-27 19:00:00"));
            }
            Thread.sleep(1000);
            bus.schedules.forEach(Schedule::printSchedule);
        }catch(Throwable t){
            t.printStackTrace();
        }
    }

    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize) {
        Predicate<Bus> cityExist = (bus) -> bus.city.equals(departure);
        return Algorithm.paginate(buses, page, pageSize, cityExist);
    }

    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max){
        List<Bus> filteredBuses = new ArrayList<>();
        for (Bus bus : buses){
            if (bus.price.price >= min && bus.price.price <= max){
                filteredBuses.add(bus);
            }
        }
        return filteredBuses;
    }

    public static Bus filterBusId(List<Bus> buses, int id) {
        for (Bus bus : buses){
            if(bus.id == id){
                return bus;
            }
        }
        return null;
    }

    public static List<Bus> filterByDepartureAndArrival(List<Bus> buses, City departure, City arrival, int page, int pageSize){
        Predicate<Bus> cityExist = (bus) -> bus.city.equals(departure) && bus.city.equals(arrival);
        return Algorithm.paginate(buses, page, pageSize, cityExist);
    }

}

