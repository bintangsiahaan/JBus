package com.bintangSiahaanJBusAF.controller;

import com.bintangSiahaanJBusAF.*;
import com.bintangSiahaanJBusAF.dbjson.JsonAutowired;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    @JsonAutowired(value = Station.class, filepath = "src\\main\\java\\com\\raihanMuhammadIhsanJBusAF\\json\\payment.json")
    public static JsonTable<Payment> paymentTable;

    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @RequestMapping(value = "/makeBooking", method = RequestMethod.POST)
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate
    )
    {
        try {
            Account buyer = Algorithm.<Account>find(new AccountController().getJsonTable(), t->t.id == buyerId);
            Bus bus = Algorithm.<Bus>find(new BusController().getJsonTable(), t->t.id == busId);
            if (buyer == null || bus == null){
                return new BaseResponse<>(false, "Akun atau Bus bernilai null!!", null);
            }
            if (buyer.balance < bus.price.price) {
                return new BaseResponse<>(false, "Uang gk cukup bro :'(!!", null);
            }
            if (!Algorithm.<Schedule>exists(bus.schedules, t->t.departureSchedule.equals(Timestamp.valueOf(departureDate)))) {
                return new BaseResponse<>(false, "Gk ada jadwal cuy!!", null);
            }
            if(!Payment.makeBooking(Timestamp.valueOf(departureDate), busSeats, bus)){
                return new BaseResponse<>(false, "Gagal make booking cuy!!", null);
            }
            Payment paymentTemp = new Payment(buyerId, renterId, busId, busSeats, Timestamp.valueOf(departureDate));
            paymentTemp.status = Invoice.PaymentStatus.WAITING;
            paymentTable.add(paymentTemp);
            return new BaseResponse<>(true, "Berhasil make booking!!", paymentTemp);
        }catch (Exception e){
            return new BaseResponse<>(false, "Kesalahan dalam berfikir!!", null);
        }
    }
    @RequestMapping(value = "/{id}/accept", method = RequestMethod.POST)
    public BaseResponse<Payment> accept(
            @PathVariable int id
    )
    {
        try {
            Payment paymentTemp = getById(id);
            paymentTemp.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Berhasil mengubah status payment menjadi Success!!", paymentTemp);
        }catch (Exception e) {
            return new BaseResponse<>(false, "Terjadi kesalahan saat mengubah status payment!!", null);
        }
    }

    @RequestMapping(value = "/{id}/cancel")
    public BaseResponse<Payment> cancel(
            @PathVariable int id
    )
    {
        try {
            Payment paymentTemp = getById(id);
            paymentTemp.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<>(true, "Berhasil mengcancel payment!!", paymentTemp);
        }catch (Exception e) {
            return new BaseResponse<>(false, "Terjadi kesalahan saat mengubah status payment!!", null);
        }
    }
}
