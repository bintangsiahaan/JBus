package com.bintangSiahaanJBusAF.controller;

import org.springframework.web.bind.annotation.*;
import com.bintangSiahaanJBusAF.dbjson.JsonAutowired;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import com.bintangSiahaanJBusAF.Payment;
import com.bintangSiahaanJBusAF.Bus;
import com.bintangSiahaanJBusAF.Account;
import com.bintangSiahaanJBusAF.Algorithm;
import com.bintangSiahaanJBusAF.Invoice;
import com.bintangSiahaanJBusAF.Schedule;
import com.bintangSiahaanJBusAF.Invoice.PaymentStatus;
import java.sql.Timestamp;
import java.util.List;
import java.lang.reflect.Field;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>
{
    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }
    @JsonAutowired(value= Payment.class,filepath = "D:\\KULIAH\\SEMESTER 3\\OOP\\praktikum\\src\\main\\java\\com\\bintangSiahaanJBusAF\\json\\payment.json")
    public static JsonTable<Payment> paymentTable;

    @PostMapping("/makeBooking")
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam Timestamp departureDate
    ){
        // Validate Buyer and Bus
        Account buyer = AccountController.accountTable.get(buyerId);
        if (buyer == null) {
            return new BaseResponse<>(false, "Buyer not found.", null);
        }

        Bus bus = BusController.busTable.get(busId);
        if (bus == null) {
            return new BaseResponse<>(false, "Bus not found.", null);
        }

        // Get the bus price
        double price = bus.price;

        // Check if buyer has enough balance to pay for the tickets
        if (buyer.balance < price * busSeats.size()) {
            return new BaseResponse<>(false, "Insufficient balance.", null);
        }

        // Check if there is a schedule on the desired departure date
        List<Schedule> availableSchedules = Payment.availableSchedule(departureDate, busSeats, bus);
        if (availableSchedules.isEmpty()) {
            return new BaseResponse<>(false, "No available schedules on the desired departure date.", null);
        }

        // Make the booking
        boolean bookingSuccessful = Payment.makeBooking(departureDate, busSeats, bus);
        if (!bookingSuccessful) {
            return new BaseResponse<>(false, "Failed to make booking.", null);
        }

        // Create a new payment object
        Payment payment = new Payment(buyerId, renterId, busId, busSeats, departureDate);

        // Get the current status of the payment
        PaymentStatus currentStatus = payment.status;

        // If the current status is not WAITING, create a new Payment object with the WAITING status and add it to the payment table
        if (currentStatus != PaymentStatus.WAITING) {
            Payment newPayment = new Payment(buyerId, renterId, busId, busSeats, departureDate);
            newPayment.status = PaymentStatus.WAITING;

            paymentTable.add(newPayment);
            payment = newPayment;
        }

        // Save the payment to the database
        try {
            JsonTable.writeJson(paymentTable, paymentTable.filepath);
        } catch (Exception e) {
            // Handle the exception
            return new BaseResponse<>(false, "Failed to save payment to the database.", null);
        }

        // Get the buyer's balance
        double balance = buyer.balance;

        // Return a successful response
        return new BaseResponse<>(true, "Booking successful.", payment);
    }

    @RequestMapping(value = "{id}/accept", method = RequestMethod.POST)
    public BaseResponse<Payment> accept( @PathVariable int id ){

        Payment payment = Algorithm.<Payment>find(paymentTable,payment1 -> payment1.id == id);
        if(payment == null || payment.status != Invoice.PaymentStatus.WAITING){
            return new BaseResponse<>(false, "Payment not found or not in WAITING status.", null);
        }
        else{
            payment.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Payment accepted.", payment);
        }

    }

    @RequestMapping(value = "{id}/cancel", method = RequestMethod.POST)
    public BaseResponse<Payment> cancel(@PathVariable int id ){
        Payment payment = Algorithm.<Payment>find(paymentTable,payment1 -> payment1.id == id);
        if(payment == null || payment.status != Invoice.PaymentStatus.WAITING){
            return new BaseResponse<>(false, "Payment not found or not in WAITING status.", null);
        }
        Account buyer = Algorithm.<Account>find(AccountController.accountTable,account -> account.id == payment.buyerId);
        Bus bus = Algorithm.<Bus>find(BusController.busTable,i -> i.id == payment.getBusId());

        // Use Java Reflection to get the price of the bus
        Field priceField = null;
        try {
            priceField = bus.getClass().getDeclaredField("price");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        priceField.setAccessible(true);
        double price = 0;
        try {
            price = (double) priceField.get(bus);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        payment.status = Invoice.PaymentStatus.FAILED;
        buyer.balance += price;
        return new BaseResponse<>(true, "Payment cancelled.", payment);

    }



}
