package com.bintangSiahaanJBusAF;


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
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9][a-zA-Z0-9]+@[a-zA-Z.]+?\\.[a-zA-Z]+?$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    public Account(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString(){
        return "\nId : " + super.id + "\nName : " + name + "\nEmail : " + email +
               "\nPassword : " + password;
    }
    
    public Object write() {
        return null;
    }

    public boolean read(String string) {
        return false;
    }

    public boolean validate(){
        if (this.email.matches(REGEX_EMAIL) && this.password.matches(REGEX_PASSWORD)){
            return true;
        }
        return false;
    }
}
