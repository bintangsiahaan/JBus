package com.bintangSiahaanJBusAF;

import java.sql.Timestamp;

/**
 * The BookingThread class represents a thread responsible for making bookings for a specific bus at a given timestamp.
 * It extends the Thread class and implements the booking logic within its run method.
 * @author Bintang Siahaan
 */
public class BookingThread extends Thread{

    private Bus bus;
    private Timestamp timestamp;
    /**
     * Constructs a BookingThread with the specified name, Bus object, and timestamp.
     * The thread is automatically started upon construction.
     *
     * @param name The name of the thread.
     * @param bus The Bus object to be booked.
     * @param timestamp The timestamp indicating the time of the booking.
     */
    public BookingThread(String name, Bus bus, Timestamp timestamp){
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }
    /**
     * The main execution logic of the booking thread.
     * It attempts to make a booking for the associated bus at the specified timestamp
     * and prints a success or failure message accordingly.
     */
    public void run(){
        System.out.println(
                "Thread " + Thread.currentThread().getId() + " Id : " + Thread.currentThread().getName() + " is running"
        );

        synchronized (this)
        {
            if(Payment.makeBooking(this.timestamp,"AF01", this.bus))
            {
                System.out.println("Thread " + Thread.currentThread().getId() + " Id : " + Thread.currentThread().getName() + " Thread berhasil");
            }
            else {
                System.out.println("Thread " + Thread.currentThread().getId() + " Id : " + Thread.currentThread().getName() + " Thread Tidak Berhasil");
            }
        }

        synchronized(this) {
            Payment.makeBooking(this.timestamp, "AF01", this.bus);
        }
    }
}
