package bintangSiahaanJBusAF;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Write a description of class Schedule here.
 *
 * @author BintangSiahaan
 * @version (a version number or a date)
 */
public class Schedule
{
    public Calendar departureSchedule;
    public Map<String, Boolean> seatAvailability;
    
    public Schedule(Calendar departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }
    
    private void initializeSeatAvailability(int numberOfSeats) {
        seatAvailability = new LinkedHashMap<>();

        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            String seatCode = "RS" + seatNumber;
            seatAvailability.put(seatCode, true);
        }
    }

}
