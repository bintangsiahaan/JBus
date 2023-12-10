package com.bintangSiahaanJBusAF.controller;

import com.bintangSiahaanJBusAF.*;
import com.bintangSiahaanJBusAF.dbjson.JsonAutowired;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * The {@code BusController} class handles HTTP requests related to buses, providing functionality for managing bus schedules and creating new buses.
 *
 * @see BasicGetController
 * @see Bus
 * @see JsonAutowired
 * @see JsonTable
 */
@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus> {

    /**
     * The JSON table storing bus data.
     */
    @JsonAutowired(value = Bus.class, filepath = "src\\main\\java\\com\\bintangSiahaanJBusAF\\json\\bus_db.json")
    public static JsonTable<Bus> busTable;

    /**
     * Retrieves the JSON table for buses.
     *
     * @return the JSON table for buses
     */
    @Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }

    /**
     * Adds a new schedule to a bus.
     *
     * @param busId the ID of the bus
     * @param time  the departure time in the format "YYYY-MM-DD HH:MM:SS"
     * @return a {@code BaseResponse} containing information about the operation's success and the bus
     */
    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time) {
        try {
            if (busId <= 0 || time == null) {
                return new BaseResponse<>(false, "Failed To Add Schedule! There's Invalid Parameter!", null);
            }
            Bus newBus = Algorithm.<Bus>find(busTable, t -> t.id == busId);
            if (newBus != null) {
                newBus.addSchedule(Timestamp.valueOf(time));
                return new BaseResponse<>(true, "Add Schedule Success", newBus);
            }
            return new BaseResponse<>(false, "Failed To Create Bus! Id Isn't Exist", null);
        } catch (IllegalArgumentException e) {
            return new BaseResponse<>(false, "Invalid Time Format! Use YYYY-MM-DD HH:MM:SS Format!", null);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Failed To Create Bus! Id Isn't Exist", null);
        }
    }

    /**
     * Creates a new bus.
     *
     * @param accountId          the ID of the account associated with the bus
     * @param name               the name of the bus
     * @param capacity           the capacity of the bus
     * @param facilities         the list of facilities available on the bus
     * @param busType            the type of the bus
     * @param price              the price of the bus
     * @param stationDepartureId the ID of the departure station
     * @param stationArrivalId   the ID of the arrival station
     * @return a {@code BaseResponse} containing information about the operation's success and the created bus
     */
    @PostMapping("/create")
    public BaseResponse<Bus> create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam List<Facility> facilities,
            @RequestParam BusType busType,
            @RequestParam int price,
            @RequestParam int stationDepartureId,
            @RequestParam int stationArrivalId) {
        Price newPrice = new Price(price);
        Station newArrival = Algorithm.<Station>find(new StationController().getJsonTable(), t -> t.id == stationArrivalId);
        Station newDeparture = Algorithm.<Station>find(new StationController().getJsonTable(), t -> t.id == stationDepartureId);
        Bus newBus = new Bus(name, facilities, newPrice, capacity, busType, newDeparture, newArrival, accountId);
        try {
            Account newAccount = Algorithm.<Account>find(new AccountController().getJsonTable(), y -> y.id == accountId);
            if (newAccount == null) {
                return new BaseResponse<>(false, "Failed To Create Bus! This Account Doesn't Exist", null);
            } else if (newAccount.company == null) {
                return new BaseResponse<>(false, "Failed To Create Bus! This Account Doesn't Have Company", null);
            } else if (!Algorithm.<Station>exists(new StationController().getJsonTable(), y -> y.id == stationArrivalId ||
                    !Algorithm.<Station>exists(new StationController().getJsonTable(), z -> z.id == stationDepartureId))) {
                return new BaseResponse<>(false, "Failed To Create Bus! Station Doesn't Exist", null);
            } else if (newArrival.equals(newDeparture)) {
                return new BaseResponse<>(false, "Failed To Create Bus! Arrival & Departure Station Are Same", null);
            }
            busTable.add(newBus);
            return new BaseResponse<>(true, "Successfully Created Bus!", newBus);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Error!", null);
        }
    }

    /**
     * Retrieves a list of buses associated with a specific account.
     *
     * @param accountId the ID of the account
     * @return a list of buses associated with the specified account
     */
    @GetMapping("/getMyBus")
    public List<Bus> getMyBus(@RequestParam int accountId) {
        return Algorithm.<Bus>findList(getJsonTable(), e -> e.accountId == accountId);
    }
}
