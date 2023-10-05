package bintangSiahaanJBusAF;


/**
 * Write a description of class Station here.
 *
 * @author BintangSiahaan
 * @version (a version number or a date)
 */
public class Station extends Serializable
{
    public String stationName;
    public City city;
    public String address;
    
    public Station(int id, String stationName, City city, String address){
        super(id);
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }
    
    public String toString(){
        return ("\nId:" + super.id + "\nStation Name:" + stationName + "\nCity:" + city + "\nAddress : " + address);
    }
}
