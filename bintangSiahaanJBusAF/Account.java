package bintangSiahaanJBusAF;


/**
 * Write a description of class Account here.
 *
 * @author Bintang Siahaan
 * @version (a version number or a date)
 */
public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    
    public Account(int id, String name, String email, String password){
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
