package com.bintangSiahaanJBusAF.controller;

import com.bintangSiahaanJBusAF.Account;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import com.bintangSiahaanJBusAF.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/account")

public class AccountController implements BasicGetController<Account>
{
    @JsonAutowired(value = Account.class, filepath = "D:\\KULIAH\\SEMESTER 3\\OOP\\praktikum\\src\\main\\java\\com\\bintangSiahaanJBusAF\\dbjson")
    public static JsonTable<Account> accountTable;

    @GetMapping("/account")
    public JsonTable<Account> getJsonTable(){
        return accountTable;
    }
    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    Account register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        return new Account(name, email, password);
    }

    @GetMapping("/{id}")
    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}
