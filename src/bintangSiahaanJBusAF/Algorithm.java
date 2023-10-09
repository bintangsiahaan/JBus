package bintangSiahaanJBusAF;
import java.util.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.stream.Collector;

public class Algorithm {
    private Algorithm(){

    }

    //Method collect
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred){
        List<T> result = new ArrayList<>();
        for (T current : iterable) {
            if (pred.predicate(current)) {
                result.add(current);
            }
        }
        return result;
    }
    public static <T> List<T> collect(Iterable<T> iterable, T value){
        for(T current : iterable){
            if(current == value){
                return (List<T>) current;
            }
        }
        return null;
    }
    public static <T> List<T> collect(T[] array, T value){
        return collect(array, item -> item.equals(value));
    }
    public static <T> List<T> collect(T[] array, Predicate<T> pred){
        List<T> result = new ArrayList<>();
        for (T current : array) {
            if (pred.predicate(current)) {
                result.add(current);
            }
        }
        return result;
    }
    public static <T> List<T> collect(Iterator<T> iterator, T value){
        List<T> result = new ArrayList<>();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (value.equals(current)) {
                result.add(current);
            }
        }
        return result;
    }
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred){
        List<T> result = new ArrayList<>();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (pred.predicate(current)) {
                result.add(current);
            }
        }
        return result;
    }


    //Method count
    public static <T> int count(Iterator<T> iterator, T value){
        Predicate<T> predicate = value::equals;
        return count(iterator, predicate);
    }
    public static <T> int count(T[] array, T value){
        Iterator<T> iterator = Arrays.stream(array).iterator();
        Predicate<T> predicate = value::equals;
        return count(iterator, value);
    }
    public static <T> int count(Iterable<T> iterable, Predicate<T> predicate){
        Iterator <T> iterator = iterable.iterator();
        return count(iterator, predicate);
    }
    public static <T> int count(T[] array, Predicate<T> predicate){
        Iterator<T> iterator = Arrays.stream(array).iterator();
        return count(iterator, predicate);
    }
    public static <T> int count(Iterator<T> iterator, Predicate<T> predicate){
        int counter = 0;
        while(iterator.hasNext()){
            if(predicate.predicate(iterator.next())) {
                counter++;
            }
        }
        return counter;
    }
    public static <T> int count(Iterable<T> iterable, T value){
        Predicate<T> predicate = value::equals;
        return count(iterable, predicate);
    }

    //Method exists
    public static <T> boolean exists(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(Iterator<T> iterator, T value){
        final Predicate<T> pred = value::equals;
        return exists(iterator, pred);
    }

    public static <T> boolean exists(T[] array, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, pred);
    }

    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }

    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T current = iterator.next();
            if(pred.predicate(current))
                return true;
        }
        return false;
    }

    //Method find
    public static <T> T find(T[] array, T value) {
        for(T i : array){ //For each i in array T compared with value
            if(i == value){
                return i;
            }
        }
        return null;
    }
    public static <T> T find(Iterable<T> iterable, T value) {
        for(T i : iterable){//For each i in iterable T compared with value
            if(i == value){
                return i;
            }
        }
        return null;
    }
    public static <T> T find(Iterator<T> iterator, T value) {
        while (iterator.hasNext()){
            if(iterator.next() == value){
                return iterator.next();
            }
        }
        return null;
    }
    public static <T> T find(T[] array, Predicate<T> pred) {
        for(T i : array){ //For each i in array T, predicate i
            if(pred.predicate(i)){
                return i;
            }
        }
        return null;
    }
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
        for(T i : iterable){//For each i in iterable T, predicate i
            if(pred.predicate(i)){
                return i;
            }
        }
        return null;
    }
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
        while (iterator.hasNext()){
            if(pred.predicate(iterator.next())){
                return iterator.next();
            }
        }
        return null;
    }

    //method paginate
    public <T> List<T> paginate(T[] array, int page, int pageSize, Predicate<T> pred){
        Iterator<T> iterator = Arrays.stream(array).iterator();
        return paginate(iterator, page, pageSize, pred);
    }
    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pageSize, Predicate<T> pred){
        Iterator<T> iterator = iterable.iterator();
        return paginate(iterator, page, pageSize, pred);
    }
    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pageSize, Predicate<T> pred){
        List<T> list = new ArrayList<T>();
        int counter = page > 1 ? (page-1)*pageSize : 0;
        while(iterator.hasNext()){
            if(pred.predicate(iterator.next())){
                if(counter < pageSize){
                    list.add(iterator.next());
                    counter++;
                }
            }
        }
        return list;
    }
}
