package com.bintangSiahaanJBusAF;

import com.bintangSiahaanJBusAF.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JBus {
    public static void main(String[] args) throws IOException, InterruptedException {
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
        try {
            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, "D:\\KULIAH\\SEMESTER 3\\OOP\\praktikum\\data\\accountDatabase.json");

            Account testAccount1 = new Account("Bintang Siahaan", "bintangsiahaan@gmail.com", "bintangshn");
            tableAccount.add(testAccount1);
            tableAccount.writeJson();

            System.out.println(testAccount1.toString());
            System.out.println("Berhasil menambahkan data ke Database");
        }
        catch (IOException e) {
            System.err.println("Terjadi kesalahan saat menambahkan data ke Database");
        }

        Bus bus = createBus();
        bus.schedules.forEach(Schedule::printSchedule);
        for(int i =0; i < 10; i++){
            BookingThread thread = new BookingThread("Thread " + i,bus, Timestamp.valueOf("2023-07-27 19:00:00"));
        }
        Thread.sleep(1000);
        bus.schedules.forEach(Schedule::printSchedule);
    }
    public static Bus createBus() {
        List<Facility> facilities = new ArrayList<>();
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", facilities, price, 25,
                BusType.REGULER, new Station("Depok Terminal", City.DEPOK,
                "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        bus.addSchedule(timestamp);
        return bus;
    }
}