package com.bintangSiahaanJBusAF;

import com.bintangSiahaanJBusAF.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
/**
 * The main class for the JBus application.
 * This class serves as the entry point for running the JBus application using Spring Boot.
 * It initializes the JsonDBEngine, starts the Spring application, and adds a shutdown hook for joining the JsonDBEngine.
 * @author Bintang Siahaan
 */
@SpringBootApplication
public class JBus {
    /**
     * The main method that is called when the JBus application is executed.
     *
     * @param args The command line arguments passed to the application.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the application is interrupted while waiting for the JsonDBEngine to finish.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));

    }
}