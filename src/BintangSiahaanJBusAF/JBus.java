package bintangSiahaanJBusAF;
import java.util.Calendar;
import java.util.Scanner;
import java.sql.Timestamp;
/**
 * Write a description of class JBus here.
 *
 * @author Bintang Siahaan
 * @version (a version number or a date)
 */
  public class JBus {
    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }
    public static void main(String[] args) {
    System.out.println("Hello from Intellij!");
    /*Price[] unfilteredArray = new Price[5];
    for(int i = 0; i < unfilteredArray.length; i++){
    
        int j = 5000;
        unfilteredArray[i] = new Price((i+1)*j);
    }
    System.out.println("Price List");
    for(Price price : unfilteredArray){
        System.out.println(price.price);
    }
    
    System.out.println("Below 12000.0");
    System.out.println(Validate.filter(unfilteredArray, 12000, true));
    System.out.println("Above 10000.0");
    System.out.println(Validate.filter(unfilteredArray, 10000, false));

    
    Bus testBus = createBus();
    
    //Payment
    Payment testPayment = new Payment(1, 1, 1, testBus.id, "S1");
    System.out.println(testPayment.getDepartureInfo());
    System.out.println(testPayment.getTime());
    
    //test Schedule
    Calendar schedule1 = Calendar.getInstance();
    testBus.addSchedule(schedule1);
    Calendar schedule2 = Calendar.getInstance();
    schedule2.add(Calendar.DAY_OF_MONTH, 3);
    testBus.addSchedule(schedule2);
    
    for(Schedule s : testBus.schedules){
        testBus.printSchedule(s);
    }*/
    Bus b = createBus();
    Timestamp schedule1 = Timestamp.valueOf("2023-7-18 15:00:00");
    Timestamp schedule2 = Timestamp.valueOf("2023-7-20 12:00:00");
    b.addSchedule(schedule1,12);
    b.addSchedule(schedule2,12);
    b.schedules.forEach(Schedule::printSchedule);
    
    //Invalid date
    Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
    System.out.println("Make booking at July 19, 2023 15:00:00 Seat AF12");
    System.out.println(Payment.makeBooking(t1, "AF12", b));
    
    //Valid date, invalid seat
    Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
    System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat AF20");
    System.out.println(Payment.makeBooking(t2, "AF07", b));
    
    Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
    System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat AF01");
    System.out.println(Payment.makeBooking(t3, "AF01", b));
    
    System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat AF01 again");
    System.out.println(Payment.makeBooking(t3, "AF01", b));
    
    //check if the data changed
    System.out.println("\nUpdated Schedule\n");
    b.schedules.forEach(Schedule::printSchedule);
}
}
