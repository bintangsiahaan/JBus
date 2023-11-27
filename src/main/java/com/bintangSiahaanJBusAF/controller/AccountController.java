package com.bintangSiahaanJBusAF.controller;

import com.bintangSiahaanJBusAF.Account;
import com.bintangSiahaanJBusAF.Algorithm;
import com.bintangSiahaanJBusAF.Renter;
import com.bintangSiahaanJBusAF.dbjson.JsonAutowired;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;





@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{

    @JsonAutowired(value = Account.class, filepath = "src\\main\\java\\com\\raihanMuhammadIhsanJBusAF\\json\\account.json")
    public static JsonTable<Account> accountTable;

    @GetMapping
    String index() { return "account page"; }
    @PostMapping("/register")
    BaseResponse<Account> register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        String encyptedPassword = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            encyptedPassword = sb.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Account acc = new Account(name, email, password);
        Account responseAcc = new Account(name, email, encyptedPassword);
        if(!acc.name.isBlank() && acc.validate() && !Algorithm.<Account>exists(getJsonTable(), t -> t.email == acc.email)){
            accountTable.add(responseAcc);
            return new BaseResponse<Account>(true, "Register Berhasil!", responseAcc);
        }
        return new BaseResponse<Account>(false, "Gagal register", null);
    }

    @PostMapping("/login")
    BaseResponse<Account> login(
            @RequestParam String email,
            @RequestParam String password
    ){
        String encyptedPassword = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++){
                sb.append((Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1)));
            }
            encyptedPassword = sb.toString();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        Account acc = new Account(null, email, encyptedPassword);
        if (
                Algorithm.<Account>exists(getJsonTable(), akun -> akun.email.equals(acc.email)
                        && akun.password.equals(acc.password))
        ) {
            return new BaseResponse<Account>(true, "Berhasil Login Cuyy!!", acc);
        }
        return new BaseResponse<Account>(false, "Gagal Login", null);
    }


    @Override
    public JsonTable<Account> getJsonTable() {
        return this.accountTable;
    }

    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter(
            @PathVariable int id,
            @RequestParam String companyName,
            @RequestParam String address,
            @RequestParam String phoneNumber
    ){
        Account account = Algorithm.<Account>find(accountTable, acc -> acc.id == id);
        if (account != null && account.company == null) {
            Renter renter = new Renter(companyName, phoneNumber, address);
            account.company = renter;
            return new BaseResponse<>(true, "Renter berhasil dibuat", renter);
        }
        return new BaseResponse<>(false, "Renter Gagal dibuat", null);
    }

    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(
            @PathVariable int id,
            @RequestParam double amount
    ){
        Account accTopUp = Algorithm.<Account>find(accountTable, t->t.id == id);
        if(accTopUp == null || amount <= 0) {
            return new BaseResponse<>(false, "TopUp Gagal", null);
        }
        accTopUp.topUp(amount);
        return new BaseResponse<>(true, "Berhasil TopUp", amount);
    }


    //@GetMapping("/{id}")
    //String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}
