package com.bintangSiahaanJBusAF.controller;

/**
 * The {@code BaseResponse} class represents a generic response structure used in the application.
 * It includes information about the success status, a descriptive message, and an optional payload.
 *
 * @param <T> the type of payload data
 * @author Bintang Siahaan
 */
public class BaseResponse<T> {
    public boolean success;
    public String message;
    public T payload;

    /**
     * Constructs a new {@code BaseResponse} with the specified success status, message, and payload.
     *
     * @param success the success status of the operation
     * @param message a descriptive message about the operation
     * @param payload the payload containing additional data
     */
    public BaseResponse(boolean success, String message, T payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }
}
