package com.bintangSiahaanJBusAF;
import com.bintangSiahaanJBusAF.dbjson.JsonDBEngine;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.lang.reflect.*;

/**
 * Write a description of class JBus here.
 *
 * @author Bintang Siahaan
 * @version (a version number or a date)
 */

@SpringBootApplication
public class JBus {
    /*public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25,
                BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK,
                "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        bus.addSchedule(timestamp);
        return bus;
    }*/
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(JBus.class, args);
        JsonDBEngine.Run(JBus.class);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));

    }
}

