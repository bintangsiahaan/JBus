package bintangSiahaanJBusAF;


/**
 * Write a description of class JBus here.
 *
 * @author Bintang Siahaan
 * @version (a version number or a date)
 */
import java.util.Scanner;

public class JBus {
    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan harga sebelum diskon: ");
        int beforeDiscount = scanner.nextInt();

        System.out.print("Masukkan harga setelah diskon: ");
        int afterDiscount = scanner.nextInt();

        System.out.print("Masukkan jumlah kursi: ");
        int numberOfSeats = scanner.nextInt();

        scanner.close();

        JBus bus = new JBus();

        System.out.println("Bus ID: " + bus.getBusId());
        System.out.println("Bus Name: " + bus.getBusName());
        System.out.println("Is Discount: " + bus.isDiscount());
        System.out.println("Discount Percentage : " + bus.getDiscountPercentage(beforeDiscount, afterDiscount));
        System.out.println("Discounted Price : " + bus.getDiscountedPrice(beforeDiscount, 10.0f));
        System.out.println("Original Price : " + bus.getOriginalPrice(afterDiscount, 10.0f));
        System.out.println("Admin Fee: " + bus.getAdminFee(beforeDiscount));
        System.out.println("Total Price: " + bus.getTotalPrice(afterDiscount, numberOfSeats));

        Bus testBus = createBus();
        System.out.println(testBus.name);
        System.out.println(testBus.facility);
        System.out.println(testBus.price.price);
        System.out.println(testBus.capacity);
    }

    public static int getBusId() {
        return 0;
    }

    public static String getBusName() {
        return "Bus";
    }

    public static boolean isDiscount() {
        return true;
    }

    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount) {
        if (beforeDiscount > afterDiscount) {
            return ((beforeDiscount - afterDiscount) * 100.0f) / beforeDiscount;
        } else {
            return 0.0f;
        }
    }

    public static int getDiscountedPrice(int price, float discountPercentage) {
        if (discountPercentage > 100.0f) {
            discountPercentage = 100.0f;
        }
        return (int) (price - (price * discountPercentage / 100.0f));
    }

    public static int getOriginalPrice(int discountedPrice, float discountPercentage) {
        return (int) (discountedPrice / (1 - discountPercentage / 100.0f));
    }

    public static float getAdminFeePercentage() {
        return 0.05f;
    }

    public static int getAdminFee(int price) {
        return (int) (0.05f * price);
    }

    public static int getTotalPrice(int price, int numberOfSeat) {
        return ((price * numberOfSeat) + (numberOfSeat * getAdminFee(price)));
    }
    public static Bus createBus(){
            Price price = new Price(750000, 5);
            Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25);
            return bus;
    }
    Payment testPayment = new Payment(1,1,1,"A",1,"A","A");
    Invoice testInvoice = new Invoice(2,2,2,"B");
    Station testStation = new Station(3,"C", City.DEPOK);
    System.out.println(testPayment.print());
    System.out.println(testInvoice.print());
    System.out.println(testStation.print());
    }*/
    Review testReview = new Review(1, "23 August 2023", "Bad Quality");
    Price testPrice = new Price(100000.0, 20000.0);
    Station testDeparture = new Station(2, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya");
    Station testArrival = new Station(3, "Halte UI", City.JAKARTA, "Universitas Indonesia");
    Bus testBus = new Bus(1, "Busway", Facility.AC, testPrice, 50, BusType.REGULER, City.DEPOK, testDeparture, testArrival);
    Account testAccount = new Account(1, "Bob", "bob@gmail.com", "bob");
    Rating testRating = new Rating();
    System.out.println(testReview);
    System.out.println(testBus);
    System.out.println(testAccount);
    System.out.println(testPrice);
    System.out.println(testRating);
    }
}
