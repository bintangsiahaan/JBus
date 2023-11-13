package com.bintangSiahaanJBusAF.controller;
import com.bintangSiahaanJBusAF.*;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import com.bintangSiahaanJBusAF.dbjson.JsonAutowired;
import java.util.List;
import java.sql.Timestamp;

public class BusController implements BasicGetController<Bus> {
    @JsonAutowired(value = Bus.class, filepath = "D:\\KULIAH\\SEMESTER 3\\OOP\\praktikum\\src\\main\\java\\com\\bintangSiahaanJBusAF\\json\\bus_db.json")
    public static JsonTable<Bus> busTable;

    @Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }

    @PostMapping("/create")
    public BaseResponse<Bus> create (
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity, // Ubah tipe data menjadi int untuk kapasitas
            @RequestParam List<Facility> facilities,
            @RequestParam BusType busType,
            @RequestParam double price,
            @RequestParam int stationDepartureId,
            @RequestParam int stationArrivalId
    ) {
        // Validate Account
        Account account = Algorithm.<Account>find(AccountController.accountTable, pred -> pred.id == accountId);
        if (account == null) {
            return new BaseResponse<>(false, "Account not found or not a renter.", null);
        }

        // Validate StationDeparture and StationArrival
        Station stationDeparture = Algorithm.<Station>find(StationController.stationTable, pred -> pred.id == stationDepartureId);
        if (stationDeparture == null) {
            return new BaseResponse<>(false, "StationDeparture not found.", null);
        }

        Station stationArrival = Algorithm.<Station>find(StationController.stationTable, pred -> pred.id == stationArrivalId);
        if (stationArrival == null) {
            return new BaseResponse<>(false, "StationArrival not found.", null);
        }

        // Objek stasiun telah ditemukan, sekarang buat objek Bus.
        Bus bus = new Bus(accountId, name, capacity, facilities, busType, price, stationDeparture, stationArrival); // Perubahan parameter di sini
        busTable.add(bus);
        return new BaseResponse<>(true, "Bus created successfully.", bus);
    }


    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time
    ){
        try {
            Timestamp calendar = Timestamp.valueOf(time);

            // Find the bus with the given ID.
            Bus bus = busTable.get(busId);
            if (bus == null) {
                return new BaseResponse<>(false, "Bus not found.", null);
            }

            // Add the schedule to the bus.
            bus.addSchedule(calendar);

            try {
                JsonTable.writeJson(busTable, busTable.filepath);
            } catch (Exception e) {
                // Handle the exception - print a message or log it
                return new BaseResponse<>(false, "Failed to update the database.", null);
            }

            // Return a successful response.
            return new BaseResponse<>(true, "Schedule added successfully.", bus);
        } catch (Exception e) {
            // Handle the error and return an appropriate response.
            if (e.getMessage().contains("Duplicate")) {
                return new BaseResponse<>(false, "Schedule with the same time already exists.", null);
            } else {
                return new BaseResponse<>(false, "Failed to add schedule: " + e.getMessage(), null);
            }
        }
    }
}
