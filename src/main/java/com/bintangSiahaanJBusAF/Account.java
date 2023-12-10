package com.bintangSiahaanJBusAF;

import com.bintangSiahaanJBusAF.dbjson.Serializable;

/**
 * The Account class represents user accounts with basic information and functionalities.
 * Extends Serializable to support JSON serialization.
 * @author Bintang Siahaan
 */
public class Account extends Serializable {
    public String email;
    public String name;
    public String password;
    public String passwordNotEncrypted;
    public Renter company;
    public double balance;
    public static final String REGEX_EMAIL = "^[A-Z0-9a-z]+@[A-Za-z]+\\.[A-Za-z]+[A-Za-z]+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    /**
     * Constructs an Account object with the specified name, email, and password.
     * @param name The name associated with the account.
     * @param email The email address associated with the account.
     * @param password The encrypted password associated with the account.
     */
    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructs an Account object with the specified name, email, password, and non-encrypted password.
     * @param name The name associated with the account.
     * @param email The email address associated with the account.
     * @param password The encrypted password associated with the account.
     * @param passwordNotEncrypted The non-encrypted password associated with the account.
     */
    public Account(String name, String email, String password, String passwordNotEncrypted) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.passwordNotEncrypted = passwordNotEncrypted;
    }

    /**
     * Returns a string representation of the Account object.
     * @return A string representation of the Account object.
     */
    public String toString() {
        return "\n====Account Class====\nName = " + this.name + "\nEmail = " + this.email + "\nPassword = " + this.password;
    }

    /**
     * Validates the email and non-encrypted password using regular expressions.
     * @return True if the email and password meet the validation criteria, false otherwise.
     */
    public boolean validate() {
        return this.email.matches(REGEX_EMAIL) && this.passwordNotEncrypted.matches(REGEX_PASSWORD);
    }

    /**
     * Increases the account balance by the specified amount.
     * @param amount The amount to be added to the account balance.
     * @return True if the amount is positive, false otherwise.
     */
    public boolean countTopUp(double amount) {
        if (amount > 0.0) {
            this.balance = this.balance + amount;
            return true;
        }
        return false;
    }
}
