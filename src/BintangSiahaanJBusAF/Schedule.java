package bintangSiahaanJBusAF;
//import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
/**
 * Write a description of class Schedule here.
 *
 * @author BintangSiahaan
 * @version (a version number or a date)
 */
public class Schedule
{
    //public Calendar departureSchedule;
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;
    
    public Schedule(Timestamp departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }
    
    private void initializeSeatAvailability(int numberOfSeats) {
        seatAvailability = new LinkedHashMap<>();

        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            String sn = seatNumber < 10 ? "0" + seatNumber : "" + seatNumber;
            seatAvailability.put("AF" + sn, true);
        }
    }
    
    public void printSchedule(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        //print tanggal keberangkatan
        System.out.println("Tanggal Keberangkatan: " + formattedDepartureSchedule);
        //print daftar kursi dan ketersediaan kursinya
        System.out.println("Daftar kursi dan ketersediaan kursi: ");
        
        int maxSeatsPerRow = 4;
        int currentSeat = 1;
        
        for(String seat : this.seatAvailability.keySet()){
            String symbol = this.seatAvailability.get(seat)? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }
    
    public boolean isSeatAvailable(String seat){
        Boolean availability = seatAvailability.get(seat);
        if (availability != null) {
            return availability;
        } else {
            System.out.println("Kursi " + seat + " tidak ditemukan.");
            return false;
        }
    }
    
    public void bookSeat(String seat){
        if (isSeatAvailable(seat)) {
            seatAvailability.put(seat, false);
            System.out.println("Kursi " + seat + " berhasil dipesan.");
        } else {
            System.out.println("Kursi " + seat + " tidak tersedia untuk dipesan.");
        }
    }
}