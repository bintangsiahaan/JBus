package com.bintangSiahaanJBusAF.controller;

import com.bintangSiahaanJBusAF.Account;
import com.bintangSiahaanJBusAF.Algorithm;
import com.bintangSiahaanJBusAF.Predicate;
import com.bintangSiahaanJBusAF.Renter;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import com.bintangSiahaanJBusAF.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.bintangSiahaanJBusAF.Account.REGEX_EMAIL;
import static com.bintangSiahaanJBusAF.Account.REGEX_PASSWORD;


@RestController
@RequestMapping("/account")

public class AccountController implements BasicGetController<Account> {
    @JsonAutowired(value = Account.class, filepath = "D:\\KULIAH\\SEMESTER 3\\OOP\\praktikum\\src\\main\\java\\com\\bintangSiahaanJBusAF\\json\\account.json")
    public static JsonTable<Account> accountTable;

    @GetMapping("/account")
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    @GetMapping
    String index() {
        return "account page";
    }

    @PostMapping("/register")
    BaseResponse<Account> register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            ) {
        boolean checkBlankName = name.isBlank();
        boolean matchEmailAndPassword = new Account(name, email, password).validate();
        boolean checkUniqueEmail = Algorithm.exists(accountTable, (Predicate<Account>) i -> true);

        if (checkBlankName && !matchEmailAndPassword && !checkUniqueEmail) {
            accountTable.add(new Account(name, email, password));
            return new BaseResponse<>(false, "Gagal Register", null);
        } else {
            String passwordToHash = password;
            String generatePassword = null;

            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(passwordToHash.getBytes());
                byte[] bytes = md.digest();
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                generatePassword = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            Account account = new Account(name, email, generatePassword);
            accountTable.add(account);
            return new BaseResponse<>(true, "Berhasil Register", account);
        }
    }

    @PostMapping("/login")
    BaseResponse<Account> login(
            @RequestParam String email,
            @RequestParam String password
    ) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        final String finalPassword = generatedPassword;
        Account temp = Algorithm.<Account>find(accountTable, pred -> email.equals(pred.email) && finalPassword.equals(pred.password));
        if (temp != null) {
            return new BaseResponse<>(true, "Berhasil Login", temp);
        } else {
            return new BaseResponse<>(false, "Gagal Login", null);
        }
    }

    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter
            (
                    @PathVariable int id,
                    @RequestParam String companyName,
                    @RequestParam String address,
                    @RequestParam String phoneNumber
            ) {
        Account account = Algorithm.<Account>find(accountTable, i -> i.id == id);
        if (account != null) {
            Renter renter = new Renter(companyName, address, phoneNumber);
            return new BaseResponse<>(true, "Berhasil", renter);
        }
        return new BaseResponse<>(false, "Gagal", null);
    }

    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(@PathVariable int id, @RequestParam double amount){
        Account account = Algorithm.<Account>find(accountTable, i -> i.id == i.id);
        if (account != null){
            boolean isTrue = account.topUp(amount);
            if(isTrue){
                return new BaseResponse<>(true, "Berhasil Top Uo", amount);
            }
        }
        return  new BaseResponse<>(false, "Gagal", null);
    }
}
