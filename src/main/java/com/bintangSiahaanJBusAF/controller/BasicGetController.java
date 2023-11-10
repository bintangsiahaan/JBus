package com.bintangSiahaanJBusAF.controller;
import com.bintangSiahaanJBusAF.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;
import com.bintangSiahaanJBusAF.Algorithm;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

@RestController
public interface BasicGetController <T extends Serializable> {
    public abstract JsonTable<T> getJsonTable();
    @GetMapping("/{id}")
    public default T getById(@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(),i->i.id == id);
    }
    @RequestMapping(value = "/page", method = RequestMethod.GET)

    @GetMapping("/page")
    public default List<T> getPage(
            @RequestParam(value = "page", defaultValue = "0") int page ,
            @RequestParam(value = "page", defaultValue = "5") int pageSize)
    {
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, a -> true);
    }
}
