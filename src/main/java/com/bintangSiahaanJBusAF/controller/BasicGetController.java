package com.bintangSiahaanJBusAF.controller;

import com.bintangSiahaanJBusAF.Algorithm;
import com.bintangSiahaanJBusAF.dbjson.JsonTable;
import com.bintangSiahaanJBusAF.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * The {@code BasicGetController} interface provides a set of basic HTTP GET operations for entities that implement the {@code Serializable} interface.
 *
 * @param <T> the type of entity that extends {@code Serializable}
 */
@RestController
public interface BasicGetController<T extends Serializable> {
    /**
     * Retrieves the JSON table for the specified entity type.
     *
     * @return the JSON table for the specified entity type
     */
    public abstract JsonTable<T> getJsonTable ();
    /**
     * Gets an entity by its ID.
     *
     * @param id the ID of the entity
     * @return the entity with the specified ID
     */
    @GetMapping("/{id}")
    public default T getById(@PathVariable int id) {
        return Algorithm.<T>find(getJsonTable(), e -> e.id == id);
    }
    /**
     * Retrieves a paginated list of entities.
     *
     * @param page     the page number
     * @param pageSize the size of each page
     * @return a paginated list of entities
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public default List<T> getPage(
            @RequestParam (value = "page", defaultValue = "0") int page,
            @RequestParam (value = "size", defaultValue = "5") int pageSize){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, t->true);
    }
    /**
     * Retrieves a list of all entities.
     *
     * @return a list of all entities
     */
    @GetMapping("/getAll")
    public default List<T> getAll() {
        return Algorithm.<T>findAll(getJsonTable());
    }
    /**
     * Retrieves the total count of entities.
     *
     * @return the total count of entities
     */
    @GetMapping("/total")
    public default int toal() {
        return Algorithm.<T>count(getJsonTable(), t->true);
    }

}
