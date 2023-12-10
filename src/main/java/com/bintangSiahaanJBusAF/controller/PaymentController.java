package com.bintangSiahaanJBusAF.controller;

import com.bintangSiahaanJBusAF.*;
import com.bintangSiahaanJBusAF.dbjson.JsonAutowired;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * The {@code PaymentController} class handles HTTP requests related to payments, providing functionality for making bookings, accepting, and canceling payments.
 *
 * @see BasicGetController
 * @see Payment
 * @see JsonAutowired
 * @see JsonTable
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    /**
     * The JSON table storing payment data.
     */
    @JsonAutowired(value = Bus.class, filepath = "src\\main\\java\\com\\bintangSiahaanJBusAF\\json\\payment.json")
    public static JsonTable<Payment> paymentTable;
    /**
     * Retrieves the JSON table for payments.
     *
     * @return the JSON table for payments
     */
    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }
    /**
     * Makes a booking for a bus.
     *
     * @param buyerId        the ID of the buyer's account
     * @param renterId       the ID of the renter's account
     * @param busId          the ID of the bus
     * @param busSeats       the list of booked bus seats
     * @param departureDate  the departure date in the format "YYYY-MM-DD HH:MM:SS"
     * @return a {@code BaseResponse} containing information about the operation's success and the created payment
     */
    @RequestMapping(value = "/makeBooking", method= RequestMethod.POST)
    public BaseResponse <Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate) {
        try {
            Account buyer = Algorithm.<Account>find(new AccountController().getJsonTable(), s-> s.id == buyerId);
            Bus newBus = Algorithm.<Bus>find(new BusController().getJsonTable(), t -> t.id== busId);
            Timestamp newDate = Timestamp.valueOf(departureDate);
            if (buyerId < 0 || renterId < 0 || busId < 0 || busSeats == null || departureDate == null) {
                return new BaseResponse<>(false, "Failed To Make Booking! There's Empty Parameter", null);
            }
            else if (buyer == null || newBus == null) {
                return new BaseResponse<>(false, "Failed To Make Booking! Account Doesn't Exist", null);
            }
            else if (buyer.balance < newBus.price.price) {
                return new BaseResponse<>(false, "Failed To Make Booking! Your Balance Isn't Enough", null);
            }
            else if (!Algorithm.<Schedule>exists(newBus.schedules, s -> s.departureSchedule.equals(newDate))) {
                return new BaseResponse<>(false, "Failed To Make Booking! Schedule Not Available", null);
            }
            Payment newPayment = new Payment(buyerId, renterId, busId, busSeats, newDate);
            newPayment.status = Invoice.PaymentStatus.WAITING;
            paymentTable.add(newPayment);
            return new BaseResponse<>(true, "Make Booking Success", newPayment);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Unknown Error!", null);
        }
    }
    /**
     * Accepts a payment with the specified ID.
     *
     * @param id the ID of the payment to accept
     * @return a {@code BaseResponse} containing information about the operation's success and the updated payment
     */
    @RequestMapping(value = "/{id}/accept", method = RequestMethod.POST)
    public BaseResponse<Payment> accept (
            @PathVariable int id){
        try {
            Payment newPayment = getById(id);
            if (newPayment == null){
                return new BaseResponse<>(false, "Gagal Bjir", null);
            }
            newPayment.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Payment Success!", newPayment);
        }
        catch (Exception e){
            e.printStackTrace();
            return new BaseResponse<>(false, "Failed Payment!", null);
        }}
    /**
     * Cancels a payment with the specified ID.
     *
     * @param id the ID of the payment to cancel
     * @return a {@code BaseResponse} containing information about the operation's success and the updated payment
     */
    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.POST)
    public BaseResponse<Payment> cancel (
            @PathVariable int id){
        try {
            Payment newPayment = getById(id);
            newPayment.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<>(true, "Cancel Payment Success!", newPayment);
        }
        catch (Exception e){
            return new BaseResponse<>(false, "Failed Payment!", null);

        }}
}
