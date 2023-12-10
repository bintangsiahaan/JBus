package com.bintangSiahaanJBusAF;

import com.bintangSiahaanJBusAF.dbjson.Serializable;
/**
 * The Station class represents a bus station, containing information about its name, associated city, and address.
 * It extends the Serializable class to support JSON serialization.
 *
 * @author Bintang Siahaan
 */
public class Station extends Serializable implements java.io.Serializable {
    public City city;
    public String address;
    public String stationName;
    /**
     * Constructs a Station instance with the specified station name, associated city, and address.
     *
     * @param stationName The name of the station.
     * @param city The associated city of the station.
     * @param address The address of the station.
     */
    public Station (String stationName, City city, String address){
        super();
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }
    /**
     * Returns a string representation of the Station object, including the station's ID, name, associated city, and address.
     *
     * @return A string containing information about the station.
     */
    public String toString(){
        return  "\nId = "+ id +"\nStation Name = " + this.stationName +"\nCity = " + this.city + "\nAddress = " + this.address;
    }

}
