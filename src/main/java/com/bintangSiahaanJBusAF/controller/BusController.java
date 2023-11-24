package com.bintangSiahaanJBusAF.controller;

import com.bintangSiahaanJBusAF.*;
import com.bintangSiahaanJBusAF.dbjson.JsonAutowired;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus>{
    @JsonAutowired(value = Bus.class, filepath = "D:\\KULIAH\\SEMESTER 3\\OOP\\praktikum\\src\\main\\java\\com\\bintangSiahaanJBusAF\\json\\bus_db.json")
    public static JsonTable<Bus> busTable;

    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }
    @PostMapping("/create")
    public BaseResponse<Bus> create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam List<Facility> facilities,
            @RequestParam BusType busType,
            @RequestParam int price,
            @RequestParam int stationDepartureId,
            @RequestParam int stationArrivalId
    )
    {
        try {
            // Validate parameters
            Account acc = Algorithm.<Account>find(new AccountController().getJsonTable(), t -> t.id == accountId);
            if (acc == null || acc.company == null ||
                    !Algorithm.<Station>exists(new StationController().getJsonTable(), t -> t.id == stationArrivalId) ||
                    !Algorithm.<Station>exists(new StationController().getJsonTable(), t -> t.id == stationDepartureId)) {
                return new BaseResponse<>(false, "Ada kesalahan dalam input parameter", null);
            }
            Bus bus = new Bus(name, facilities, new Price(price), capacity, busType,
                    Algorithm.<Station>find(new StationController().getJsonTable(), t->t.id == stationDepartureId),
                    Algorithm.<Station>find(new StationController().getJsonTable(), t->t.id == stationArrivalId));
            busTable.add(bus);
            return new BaseResponse<>(true, "Berhasil membuat bus!! keren.", bus);
        }catch (Exception e) {
            return new BaseResponse<>(false, "Ada kesalahan saat membuat bus baru!", null);
        }
    }

    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time
    )
    {
        try {
            Bus bus = Algorithm.<Bus>find(busTable, t->t.id == busId);
            bus.addSchedule(Timestamp.valueOf(time));
            return new BaseResponse<>(true, "Berhasil buat schedule men!!", bus);
        }catch (Exception e) {
            return new BaseResponse<>(false, "Ada kesalahan saat menambah schedule!", null);
        }
    }


}

