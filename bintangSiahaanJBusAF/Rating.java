package bintangSiahaanJBusAF;

/**
 * Write a description of class Rating here.
 *
 * @author Bintang Siahaan
 * @version (a version number or a date)
 */
public class Rating {
    private long count;
    private long total;

    public Rating() {
        this.count = 0;
        this.total = 0;
    }

    public void insert(int rating) {
        total += rating;
        count++;
    }

    public double getAverage() {
        if (count == 0) {
            return 0.0;
        }
        return (double) total / count;
    }

    public long getCount() {
        return count;
    }

    public long getTotal() {
        return total;
    }
    
    public String toString(){
        return "\nTotal : " + total + "\nCount : " + count;
    }
}