package com.bintangSiahaanJBusAF;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

/**
 * The Schedule class represents the schedule of a bus departure, including information about
 * the departure timestamp and seat availability.
 *
 * <p>The seat availability is represented as a map where the key is the seat identifier,
 * and the value is a boolean indicating whether the seat is available or not.
 *
 * @author Bintang Siahaan
 */
public class Schedule {
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;
    /**
     * Constructs a Schedule instance with the specified departure schedule timestamp
     * and the number of seats for initialization.
     *
     * @param departureSchedule The timestamp indicating the departure schedule.
     * @param numberOfSeats The number of seats to initialize in the schedule.
     */
    public Schedule(Timestamp departureSchedule, int numberOfSeats) {
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }
    /**
     * Initializes the seat availability map based on the given number of seats.
     *
     * @param numberOfSeats The number of seats to initialize in the schedule.
     */
    private void initializeSeatAvailability(int numberOfSeats) {
        seatAvailability = new LinkedHashMap<>();

        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            String sn = seatNumber < 10 ? "0" + seatNumber : "" + seatNumber;
            seatAvailability.put("AF" + sn, true);
        }
    }
    /**
     * Prints the schedule, including the departure date, seat identifiers, and their availability.
     */
    public void printSchedule() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        System.out.println("Tanggal Keberangkatan: " + formattedDepartureSchedule);
        System.out.println("Daftar kursi dan ketersediaan kursi: ");

        int maxSeatsPerRow = 4;
        int currentSeat = 1;

        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat) ? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");

            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }
    /**
     * Checks if a specific seat is available.
     *
     * @param seat The seat identifier to check.
     * @return True if the seat is available; false otherwise.
     */
    public boolean isSeatAvailable(String seat) {
        Boolean availability = seatAvailability.get(seat);
        if (availability != null) {
            return availability;
        } else {
            System.out.println("Kursi " + seat + " tidak ditemukan.");
            return false;
        }
    }
    /**
     * Checks if a list of seats is available.
     *
     * @param seats The list of seat identifiers to check.
     * @return True if all seats are available; false if at least one seat is not available or not found.
     */
    public boolean isSeatAvailable(List<String> seats) {
        boolean allAvailable = true;
        for (String seat : seats) {
            Boolean availability = seatAvailability.get(seat);
            if (availability == null || !availability) {
                allAvailable = false;
                break;
            }
        }
        return allAvailable;
    }
    /**
     * Books a specific seat, marking it as unavailable.
     *
     * @param seat The seat identifier to book.
     */
    public void bookSeat(String seat) {
        if (isSeatAvailable(seat)) {
            seatAvailability.put(seat, false);
            System.out.println("Kursi " + seat + " berhasil dipesan.");
        } else {
            System.out.println("Kursi " + seat + " tidak tersedia untuk dipesan.");
        }
    }
    /**
     * Books a list of seats, marking them as unavailable.
     *
     * @param seats The list of seat identifiers to book.
     */
    public void bookSeat(List<String> seats) {
        for (String seat : seats) {
            bookSeat(seat);
        }
    }
    /**
     * Returns a string representation of the Schedule object, including the formatted departure schedule
     * and the number of occupied seats out of the total seats.
     *
     * @return A string containing information about the departure schedule and seat occupancy.
     */
    @Override
    public String toString() {
        SimpleDateFormat sdFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = sdFormat.format(this.departureSchedule.getTime());

        int totalSeats = seatAvailability.size();
        int occupiedSeats = (int) seatAvailability.values().stream().filter(avail -> !avail).count();

        return "Departure Schedule: " + formattedDepartureSchedule +
                "\nOccupied Seats: " + occupiedSeats + "/" + totalSeats;
    }
}
