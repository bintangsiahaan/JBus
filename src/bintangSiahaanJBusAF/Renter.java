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

    public static final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{4,20}$";
    public static final String REGEX_PHONE = "^[0-9]{9,12}$";
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

    public boolean validate(){
        String numberPhone = String.valueOf(phoneNumber);
        return companyName.matches(REGEX_NAME)&& numberPhone.matches(REGEX_PHONE);
    }
}
