package com.bintangSiahaanJBusAF;


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
    //public int phoneNumber = 0;
    public String phoneNumber = "";

    public static final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{4,20}$";
    public static final String REGEX_PHONE = "^[0-9]{9,12}$";
    public Renter(String companyName){
        this.companyName = companyName;
    }
    
    public Renter(String companyName, String phoneNumber){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }
    
    public Renter(String companyName, String phoneNumber, String address){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean validate(){
        String numberPhone = String.valueOf(phoneNumber);
        return companyName.matches(REGEX_NAME)&& numberPhone.matches(REGEX_PHONE);
    }
}
