package bintangSiahaanJBusAF;

import java.util.Scanner;

public class JBus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
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
    }

    public int getBusId() {
        return 0;
    }

    public String getBusName() {
        return "Bus";
    }

    public boolean isDiscount() {
        return true;
    }

    public float getDiscountPercentage(int beforeDiscount, int afterDiscount) {
        if (beforeDiscount > afterDiscount) {
            return ((beforeDiscount - afterDiscount) * 100.0f) / beforeDiscount;
        } else {
            return 0.0f;
        }
    }

    public int getDiscountedPrice(int price, float discountPercentage) {
        if (discountPercentage > 100.0f) {
            discountPercentage = 100.0f;
        }
        return (int) (price - (price * discountPercentage / 100.0f));
    }

    public int getOriginalPrice(int discountedPrice, float discountPercentage) {
        return (int) (discountedPrice / (1 - discountPercentage / 100.0f));
    }

    public int getAdminFee(int price) {
        float adminFeePercentage = getAdminFeePercentage();
        return (int) (price * adminFeePercentage);
    }

    public int getTotalPrice(int price, int numberOfSeat) {
        int adminFee = getAdminFee(price);
        return price * numberOfSeat + adminFee;
    }

    public float getAdminFeePercentage() {
        return 0.05f;
    }
}
