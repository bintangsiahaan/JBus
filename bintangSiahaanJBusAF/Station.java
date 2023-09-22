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
    
    public Station(int id, String stationName, City city){
        super(id);
        this.stationName = stationName;
        this.city = city;
    }
    
    public String print(){
        return ("id:" + super.id + " stationName:" + stationName + " city:" + city);
    }
}
