package bintangSiahaanJBusAF;
import java.util.Calendar;
import java.util.Scanner;
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
    
    Price[] unfilteredArray = new Price[5];
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
    }
}
}
