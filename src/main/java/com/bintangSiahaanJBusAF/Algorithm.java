package com.bintangSiahaanJBusAF;

import java.util.*;

/**
 * The Algorithm class provides utility methods for working with collections and iterators.
 * It includes functions for filtering, counting, finding, and paginating elements in collections.
 * All methods are static, and the class cannot be instantiated.
 * This class is designed to work with various types of collections and iterators, providing
 * generic methods for common operations.
 * </p>
 *
 * @author Bintang Siahaan
 */
public class Algorithm {
    private Algorithm() {
    }
    /**
     * Collects elements from an iterable that are equal to the specified value.
     *
     * @param iterable The iterable to collect elements from.
     * @param value    The value to match.
     * @param <T>      The type of elements in the iterable.
     * @return A list of elements that match the specified value.
     */
    public static <T> List<T> collect(Iterable<T> iterable, T value) {
        Iterator<T> i = iterable.iterator();
        return collect(i, value);
    }
    /**
     * Collects elements from an iterable that satisfy the specified predicate.
     *
     * @param iterable  The iterable to collect elements from.
     * @param predicate The predicate to apply.
     * @param <T>       The type of elements in the iterable.
     * @return A list of elements that satisfy the specified predicate.
     */
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> predicate) {
        Iterator<T> i = iterable.iterator();
        return collect(i, predicate);
    }
    /**
     * Collects elements from an array that are equal to the specified value.
     *
     * @param array The array to collect elements from.
     * @param value The value to match.
     * @param <T>   The type of elements in the array.
     * @return A list of elements that match the specified value.
     */
    public static <T> List<T> collect(T[] array, T value) {
        Iterator<T> i = Arrays.stream(array).iterator();
        return collect(i, value);
    }
    /**
     * Collects elements from an iterator that are equal to the specified value.
     *
     * @param iterator The iterator to collect elements from.
     * @param value    The value to match.
     * @param <T>      The type of elements in the iterator.
     * @return A list of elements that match the specified value.
     */
    public static <T> List<T> collect(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> predicate = value::equals;
        return collect(iterator, predicate);
    }
    /**
     * Collects elements from an array that satisfy the specified predicate.
     *
     * @param array     The array to collect elements from.
     * @param predicate The predicate to apply.
     * @param <T>       The type of elements in the array.
     * @return A list of elements that satisfy the specified predicate.
     */
    public static <T> List<T> collect(T[] array, Predicate<T> predicate) {
        Iterator<T> i = Arrays.stream(array).iterator();
        return collect(i, predicate);
    }
    /**
     * Collects elements from an iterator that satisfy the specified predicate.
     *
     * @param iterator The iterator to collect elements from.
     * @param pred     The predicate to apply.
     * @param <T>      The type of elements in the iterator.
     * @return A list of elements that satisfy the specified predicate.
     */
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred) {
        List<T> list = new ArrayList();

        while(iterator.hasNext()) {
            T tempVar = iterator.next();
            if (pred.predicate(tempVar)) {
                list.add(tempVar);
            }
        }

        return list;
    }
    /**
     * Counts the occurrences of a specific value in an iterator.
     *
     * @param iterator The iterator to count occurrences from.
     * @param value    The value to count.
     * @param <T>      The type of elements in the iterator.
     * @return The number of occurrences of the specified value.
     */
    public static <T> int count(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(iterator, pred);
    }
    /**
     * Counts the occurrences of a specific value in an iterable.
     *
     * @param iterable The iterable to count occurrences from.
     * @param value    The value to count.
     * @param <T>      The type of elements in the iterable.
     * @return The number of occurrences of the specified value.
     */
    public static <T> int count(Iterable<T> iterable, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(iterable, pred);
    }
    /**
     * Counts the occurrences of elements satisfying a predicate in an array.
     *
     * @param array The array to count occurrences from.
     * @param pred  The predicate to apply.
     * @param <T>   The type of elements in the array.
     * @return The number of occurrences of elements satisfying the predicate.
     */
    public static <T> int count(T[] array, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, pred);
    }
    /**
     * Counts the occurrences of a specific value in an array.
     *
     * @param array The array to count occurrences from.
     * @param value The value to count.
     * @param <T>   The type of elements in the array.
     * @return The number of occurrences of the specified value.
     */
    public static <T> int count(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(it, value);
    }
    /**
     * Counts the occurrences of elements satisfying a predicate in an iterable.
     *
     * @param iterable The iterable to count occurrences from.
     * @param pred     The predicate to apply.
     * @param <T>      The type of elements in the iterable.
     * @return The number of occurrences of elements satisfying the predicate.
     */
    public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return count(it, pred);
    }
    /**
     * Counts the occurrences of elements satisfying a predicate in an iterator.
     *
     * @param iterator The iterator to count occurrences from.
     * @param pred     The predicate to apply.
     * @param <T>      The type of elements in the iterator.
     * @return The number of occurrences of elements satisfying the predicate.
     */
    public static <T> int count(Iterator<T> iterator, Predicate<T> pred) {
        int count = 0;

        while(iterator.hasNext()) {
            if (pred.predicate(iterator.next())) {
                ++count;
            }
        }

        return count;
    }
    /**
     * Finds the first occurrence of a specific value in an iterator.
     *
     * @param iterator The iterator to search.
     * @param number   The value to find.
     * @param <T>      The type of elements in the iterator.
     * @return The first occurrence of the specified value, or null if not found.
     */
    public static <T> T find(Iterator<T> iterator, T number) {
        Objects.requireNonNull(number);
        Predicate<T> pred = number::equals;
        return find(iterator, pred);
    }
    /**
     * Finds the first occurrence of a specific value in an array.
     *
     * @param array The array to search.
     * @param value The value to find.
     * @param <T>   The type of elements in the array.
     * @return The first occurrence of the specified value, or null if not found.
     */
    public static <T> T find(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }
    /**
     * Finds the first occurrence of an element satisfying a predicate in an iterable.
     *
     * @param iterable The iterable to search.
     * @param pred     The predicate to apply.
     * @param <T>      The type of elements in the iterable.
     * @return The first occurrence of an element satisfying the predicate, or null if not found.
     */
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return find(it, pred);
    }
    /**
     * Finds the first occurrence of an element satisfying a predicate in an array.
     *
     * @param arr  The array to search.
     * @param pred The predicate to apply.
     * @param <T>  The type of elements in the array.
     * @return The first occurrence of an element satisfying the predicate, or null if not found.
     */
    public static <T> T find(T[] arr, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(arr).iterator();
        return find(it, pred);
    }
    /**
     * Finds the first occurrence of an element satisfying a predicate in an iterator.
     *
     * @param iterator The iterator to search.
     * @param pred     The predicate to apply.
     * @param <T>      The type of elements in the iterator.
     * @return The first occurrence of an element satisfying the predicate, or null if not found.
     */
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
        while(true) {
            if (iterator.hasNext()) {
                T current = iterator.next();
                if (!pred.predicate(current)) {
                    continue;
                }

                return current;
            }

            return null;
        }
    }
    /**
     * Finds the first occurrence of a specific value in an iterable.
     *
     * @param iterable The iterable to search.
     * @param value    The value to find.
     * @param <T>      The type of elements in the iterable.
     * @return The first occurrence of the specified value, or null if not found.
     */
    public static <T> T find(Iterable<T> iterable, T value) {
        Iterator<T> it = iterable.iterator();
        return find(it, value);
    }
    /**
     * Finds all elements in an iterable and returns them in a list.
     *
     * @param iterable The iterable to collect elements from.
     * @param <T>      The type of elements in the iterable.
     * @return A list containing all elements in the iterable.
     */
    public static <T> List<T> findAll(Iterable<T> iterable) {
        Iterator<T> i = iterable.iterator();
        return findAll(i);
    }
    /**
     * Finds all elements in an iterator and returns them in a list.
     *
     * @param iterator The iterator to collect elements from.
     * @param <T>      The type of elements in the iterator.
     * @return A list containing all elements in the iterator.
     */
    public static <T> List<T> findAll(Iterator<T> iterator) {
        List<T> result = new ArrayList();
        while(iterator.hasNext()) {
            T obj = iterator.next();
            result.add(obj);
        }

        return result;
    }
    /**
     * Checks if a specific value exists in an array.
     *
     * @param array The array to check.
     * @param value The value to find.
     * @param <T>   The type of elements in the array.
     * @return True if the value exists in the array, false otherwise.
     */
    public static <T> boolean exists(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, value);
    }
    /**
     * Checks if a specific value exists in an iterable.
     *
     * @param iterable The iterable to check.
     * @param value    The value to find.
     * @param <T>      The type of elements in the iterable.
     * @return True if the value exists in the iterable, false otherwise.
     */
    public static <T> boolean exists(Iterable<T> iterable, T value) {
        Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }
    /**
     * Checks if a specific value exists in an iterator.
     *
     * @param iterator The iterator to check.
     * @param value    The value to find.
     * @param <T>      The type of elements in the iterator.
     * @return True if the value exists in the iterator, false otherwise.
     */
    public static <T> boolean exists(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return exists(iterator, pred);
    }
    /**
     * Checks if any element in an iterable satisfies a specified predicate.
     *
     * @param iterable The iterable to check.
     * @param <T>      The type of elements in the iterable.
     * @return True if any element satisfies the predicate, false otherwise.
     */
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }
    /**
     * Checks if any element in an array satisfies a specified predicate.
     *
     * @param array The array to check.
     * @param pred  The predicate to apply.
     * @param <T>   The type of elements in the array.
     * @return True if any element satisfies the predicate, false otherwise.
     */
    public static <T> boolean exists(T[] array, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, pred);
    }
    /**
     * Checks if an element satisfying the given predicate exists in the provided iterator.
     *
     * @param <T> The type of elements in the iterator.
     * @param iterator The iterator to check for element existence.
     * @param pred The predicate to test elements.
     * @return {@code true} if an element satisfying the predicate exists, otherwise {@code false}.
     */
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
        while(true) {
            if (iterator.hasNext()) {
                T current = iterator.next();
                if (!pred.predicate(current)) {
                    continue;
                }

                return true;
            }

            return false;
        }
    }
    /**
     * Paginates elements from an array based on a predicate.
     *
     * @param <T> The type of elements in the array.
     * @param arr The array to paginate.
     * @param page The page number (starting from 0).
     * @param pagesize The number of elements per page.
     * @param pred The predicate to filter elements.
     * @return A list containing elements that satisfy the predicate for the specified page.
     */
    public static <T> List<T> paginate(T[] arr, int page, int pagesize, Predicate<T> pred) {
        Iterator<T> i = Arrays.stream(arr).iterator();
        return paginate(i, page, pagesize, pred);
    }
    /**
     * Paginates elements from an iterable based on a predicate.
     *
     * @param <T> The type of elements in the iterable.
     * @param iterable The iterable to paginate.
     * @param page The page number (starting from 0).
     * @param pagesize The number of elements per page.
     * @param pred The predicate to filter elements.
     * @return A list containing elements that satisfy the predicate for the specified page.
     */
    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pagesize, Predicate<T> pred) {
        Iterator<T> i = iterable.iterator();
        return paginate(i, page, pagesize, pred);
    }
    /**
     * Paginates elements from an iterator based on a predicate.
     *
     * @param <T> The type of elements in the iterator.
     * @param iterator The iterator to paginate.
     * @param page The page number (starting from 0).
     * @param pagesize The number of elements per page.
     * @param pred The predicate to filter elements.
     * @return A list containing elements that satisfy the predicate for the specified page.
     */
    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pagesize, Predicate<T> pred) {
        List<T> pageResult = new ArrayList();
        int count = 0;
        int startindex = page * pagesize;
        int endindex = startindex + pagesize;
        while(iterator.hasNext()) {
            T obj = iterator.next();
            if (pred.predicate(obj)) {
                if (count >= startindex && count < endindex) {
                    pageResult.add(obj);
                }
                ++count;
            }
        }
        return pageResult;
    }

    public static <T> List<T> findList(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> i = iterable.iterator();
        return findList(i, pred);
    }

    public static <T> List<T> findList(Iterator<T> iterator, Predicate<T> pred) {
        List<T> result = new ArrayList<>();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (pred.predicate(current)) {
                result.add(current);
            }

        }
        return result;
    }
}
