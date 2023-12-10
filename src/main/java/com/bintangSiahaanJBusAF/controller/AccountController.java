package com.bintangSiahaanJBusAF.controller;

import com.bintangSiahaanJBusAF.Account;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import com.bintangSiahaanJBusAF.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;
import com.bintangSiahaanJBusAF.Algorithm;
import com.bintangSiahaanJBusAF.Renter;
import javax.websocket.server.PathParam;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The {@code AccountController} class handles HTTP requests related to user accounts.
 * It includes methods for user registration, login, and actions related to renter information and account balance.
 *
 * @author Bintang Siahaan
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    /**
     * Displays a message on the account page.
     *
     * @return a message indicating the account page
     */
    @GetMapping
    String index() {
        return "account page";
    }
    /**
     * Autowired JsonTable for the Account class.
     */
    @JsonAutowired(
            value = Account.class,
            filepath = "src\\main\\java\\com\\bintangSiahaanJBusAF\\json\\account.json")
    public static JsonTable<Account> accountTable;

    /**
     * Retrieves the JsonTable for the Account class.
     *
     * @return the JsonTable for the Account class
     */
    @Override
    public JsonTable<Account> getJsonTable(){
        return accountTable;
    }
    /**
     * Handles the user registration process.
     *
     * @param name     the user's name
     * @param email    the user's email address
     * @param password the user's password
     * @return a BaseResponse containing the registration status and the new account information
     */
    @PostMapping("/register")
    BaseResponse<Account> register (
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ){
        String generatedPass = null;
        try {
            int i;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (i = 0; i < bytes.length; i++){
                sb.append((Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1)));
            }
            generatedPass = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        Account notEncryptedAccount = new Account(name, email, generatedPass, password); //
        Account newAccount = new Account(name, email, generatedPass);
        if (name.isBlank() ) {
            return new BaseResponse<>(false, "Failed to Register! The Name Field is Blank", null);
        }
        else if (!notEncryptedAccount.validate()) {
            return new BaseResponse<>(false, "Failed to Register! Email or Password Doesn't Meet The Requirements", null);
        }
        else if (Algorithm.<Account>exists(getJsonTable(), t -> t.email.equals(newAccount.email))){
            return new BaseResponse<>(false, "Failed to Register! Email Already Exists", null);
        }
        else if (Algorithm.<Account>exists(getJsonTable(), t -> t.name.equals(newAccount.name))){
            return new BaseResponse<>(false, "Failed to Register! Name Already Exists", null);
        }
        else if (!name.isBlank() && notEncryptedAccount.validate() && !Algorithm.<Account>exists(getJsonTable(), t -> t.email.equals(newAccount.email))){
            accountTable.add(notEncryptedAccount);
            return new BaseResponse<>(true, "Success to Register Your Account", newAccount);
        }
        else {
            return new BaseResponse<>(false, "Failed to Register Because Unknown Problem", newAccount);

        }
    }
    /**
     * Handles the user login process.
     *
     * @param email    the user's email address
     * @param password the user's password
     * @return a BaseResponse containing the login status and the account information
     */
    @PostMapping("/login")
    BaseResponse<Account> login(
            @RequestParam String email,
            @RequestParam String password)
    {
        String generatedPass = null;
        try {
            int i;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (i = 0; i < bytes.length; i++){
                sb.append((Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1)));
            }
            generatedPass = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        Account notEncryptedAccount = new Account(null, email, generatedPass, password); //
        Account newAccount = new Account(null, email, generatedPass);
        if (Algorithm.<Account>exists(getJsonTable(), t -> t.email.equals(newAccount.email) && t.password.equals(newAccount.password))){
            return new BaseResponse<Account>(true, "Success to Login",
                    Algorithm.<Account>find(getJsonTable(), t->t.email.equals(newAccount.email)));
        }
        else if (Algorithm.<Account>exists(getJsonTable(), t -> t.email.equals(newAccount.email) && !t.password.equals(newAccount.password))){
            return new BaseResponse<Account>(false, "Failed to Login! Wrong Password", null);
        }
        return new BaseResponse<Account>(false, "Failed to Login! Your Account isn't Exist!", null);
    }
    /**
     * Handles the registration of a renter for a specific account.
     *
     * @param id           the account ID
     * @param companyName  the name of the renter's company
     * @param address      the address of the renter's company
     * @param phoneNumber  the phone number of the renter's company
     * @return a BaseResponse containing the registration status and the new renter information
     */
    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenterr(
            @PathVariable int id,
            @RequestParam String companyName,
            @RequestParam String address,
            @RequestParam String phoneNumber){

        Account newAccount = Algorithm.<Account>find(accountTable, t -> t.id == id);
        Renter newRenter = new Renter (companyName, phoneNumber, address);
        if (Algorithm.<Account>exists(getJsonTable(), t -> t.id == id && t.company==null)){
            newAccount.company = newRenter;
            return new BaseResponse<>(true, "Success to Make Renter !", newRenter);
        }
        else if (companyName.isBlank() && address.isBlank() && phoneNumber.isBlank()){
            return new BaseResponse<>(false, "Failed To Make Renter! There's Empty Parameter", null);
        }
        else if (!newRenter.validate()){
            return new BaseResponse<>(false, "Failed To Make Renter! Company Name or Phone Number Doesn't Meet Requirement", null);
        }
        else if (Algorithm.<Account>exists(getJsonTable(), t -> t.id == id && t.company!=null)){
            return new BaseResponse<>(false, "Failed to Make Renter, This Id Already Has Company", null);
        }
        return new BaseResponse<>(false, "Failed to Make Renter", null);
    }
    /**
     * Handles the top-up of an account's balance.
     *
     * @param id      the account ID
     * @param amount  the amount to be added to the account balance
     * @return a BaseResponse containing the top-up status and the updated account balance
     */
    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(
            @PathVariable int id,
            @RequestParam Double amount
    ){
        Account newAccount = Algorithm.<Account>find(accountTable, t -> t.id == id);
        if (Algorithm.<Account>exists(getJsonTable(), t -> t.id == id && t.countTopUp(amount))== true){
            return new BaseResponse<>(true, "Success to Top Up", newAccount.balance);
        }
        else if (Algorithm.<Account>exists(getJsonTable(), t -> t.countTopUp(amount))!= true){
            return new BaseResponse<>(false, "Failed To Top Up! Your Top Up Amount Is 0 Or Below 0", null);
        }
        if (Algorithm.<Account>exists(getJsonTable(), t -> t.id != id)== true){
            return new BaseResponse<>(false, "Failed To Top Up! Your Id Isn't Exist", null);
        }
        return new BaseResponse<>(false, "Failed to Topg Up", null);
    }
    /**
     * Handles the registration of a renter without associating it with a specific account.
     *
     * @param companyName  the name of the renter's company
     * @param address      the address of the renter's company
     * @param phoneNumber  the phone number of the renter's company
     * @return a BaseResponse containing the registration status and the new renter information
     */
    @PostMapping("/registerRenter")
    BaseResponse<Renter> registerRenter(
            @RequestParam String companyName,
            @RequestParam String address,
            @RequestParam String phoneNumber){

        Renter newRenter = new Renter(companyName, phoneNumber, address);

        if (companyName.isBlank() || address.isBlank() || phoneNumber.isBlank()){
            return new BaseResponse<>(false, "Please Fill The Field", null);
        }
        else if (!companyName.isBlank() && newRenter.validate()){
            return new BaseResponse<>(true, "Success to Register as Renter", newRenter);
        }
        else if (!newRenter.validate()){
            return new BaseResponse<>(false, "Address or Company Name Is Invalid", null);
        }
        return new BaseResponse<>(false, "Failed to Register as Renter", null);
    }
}
