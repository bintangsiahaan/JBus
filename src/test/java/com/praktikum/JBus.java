package com.bintangSiahaanJBusAF;

import com.bintangSiahaanJBusAF.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class JBus {
    public static void main(String[] args) throws IOException, InterruptedException {
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
    }
}