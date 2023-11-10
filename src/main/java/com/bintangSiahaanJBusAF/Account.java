package com.bintangSiahaanJBusAF;


import com.bintangSiahaanJBusAF.dbjson.Serializable;

/**
 * Write a description of class Account here.
 *
 * @author Bintang Siahaan
 * @version (a version number or a date)
 */
public class Account extends Serializable
{
    public Renter company;
    public double balance;
    public String email;
    public String name;
    public String password;
    public static final String REGEX_EMAIL =
            "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
    public static final String REGEX_PASSWORD =
            "^( =.*[a-z])( =.*[A-Z])( =.*\\d)[a-zA-Z\\d]{8,}$";
    public Account(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0;
        this.company = null;
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

    public boolean topUp(double amount){
        if(amount <= 0){
            return false;
        }
        balance += balance + amount;
        return  true;
    }
    public boolean validate(){
        if (this.email.matches(REGEX_EMAIL) && this.password.matches(REGEX_PASSWORD)){
            return true;
        }
        return false;
    }
}
