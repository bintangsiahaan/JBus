package com.bintangSiahaanJBusAF;
/**
 * The Predicate interface represents a functional interface with a single method for evaluating a predicate.
 * This interface is designed to provide a mechanism for defining custom predicate functions.
 *
 * @param <T> The type of the input to the predicate.
 * @author Bintang Siahaan
 */
public interface Predicate<T> {
    /**
     * Evaluates this predicate on the given argument.
     *
     * @param pred The input argument.
     * @return True if the input argument matches the predicate condition; false otherwise.
     */
    public boolean predicate(T pred);
}
