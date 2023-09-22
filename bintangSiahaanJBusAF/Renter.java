package bintangSiahaanJBusAF;


/**
 * Write a description of class Renter here.
 *
 * @author BintangSiahaan
 * @version (a version number or a date)
 */
public class Renter extends Serializable
{
    public String companyName = "";
    public String address = "";
    public int phoneNumber = 0;
    
    public Renter(int id, String companyName){
        super(id);
        this.companyName = companyName;
    }
    
    public Renter(int id,String companyName, String address){
        super(id);
        this.companyName = companyName;
        this.address = address;
    }
    
    public Renter(int id, String companyName, int phoneNumber){
        super(id);
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }
    
    public Renter(int id, String companyName, int phoneNumber, String address){
        super(id);
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
