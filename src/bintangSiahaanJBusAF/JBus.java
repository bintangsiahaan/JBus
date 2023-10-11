package bintangSiahaanJBusAF;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
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
        // TP Modul 6

        String filepath = "D:\\KULIAH\\SEMESTER 3\\OOP\\praktikum\\data\\station.json";
        Gson gson = new Gson();

        try{
            BufferedReader buffer = new BufferedReader(new FileReader(filepath));
            List<Station> stationjson = gson.fromJson(buffer, new TypeToken<List<Station>>() {}.getType());
            stationjson.forEach(e -> System.out.println(e.toString()));
            System.out.println();
            buffer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }
}
