package bintangSiahaanJBusAF;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

/**
 * Write a description of class JBus here.
 *
 * @author Bintang Siahaan
 * @version (a version number or a date)
 */
public class JBus {
    public static void main(String[] args) {
        try {
            String filepath =
                    "D:\\KULIAH\\SEMESTER 3\\OOP\\praktikum\\data\\buses_CS.json";
            JsonTable<Bus> busList = new JsonTable<>(Bus.class,filepath);
            List<Bus> filteredBus = filterByDeparture(busList,City.JAKARTA,1,10);
            filteredBus.forEach(bus -> System.out.println(bus.toString()));
        }
        catch (Throwable t){
            t.printStackTrace();
        }
    }

    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize) {
        String departureString = departure.toString();

        return Algorithm.<Bus>paginate(buses, page, pageSize, i -> i.departure.toString().equalsIgnoreCase(departureString));
    }

    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max){
        return Algorithm.<Bus>collect(buses, i -> i.price.price >= min && i.price.price <= max);
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

