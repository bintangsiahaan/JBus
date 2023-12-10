package com.bintangSiahaanJBusAF;


import com.bintangSiahaanJBusAF.dbjson.Serializable;

/**
 * The Renter class represents a renter or tenant in the system.
 * It includes information such as the company name, address, and phone number.
 * The class provides methods for creating renters and validating their information.
 *
 * @author Bintang Siahaan
 */
public class Renter extends Serializable
{
    public String companyName = "";
    public String address = "";
    public String phoneNumber = "";

    public static final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{4,20}$";
    public static final String REGEX_PHONE = "^[0-9]{9,12}$";
    /**
     * Constructs a Renter instance with the specified company name.
     *
     * @param companyName The name of the company associated with the renter.
     */
    public Renter(String companyName){
        this.companyName = companyName;
    }
    /**
     * Constructs a Renter instance with the specified company name and phone number.
     *
     * @param companyName The name of the company associated with the renter.
     * @param phoneNumber The phone number of the renter.
     */
    public Renter(String companyName, String phoneNumber){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }
    /**
     * Constructs a Renter instance with the specified company name, phone number, and address.
     *
     * @param companyName The name of the company associated with the renter.
     * @param phoneNumber The phone number of the renter.
     * @param address The address of the renter.
     */
    public Renter(String companyName, String phoneNumber, String address){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    /**
     * Validates the renter's information based on predefined regular expression patterns.
     *
     * @return True if the information is valid; false otherwise.
     */
    public boolean validate(){
        String numberPhone = String.valueOf(phoneNumber);
        return companyName.matches(REGEX_NAME)&& numberPhone.matches(REGEX_PHONE);
    }
}
