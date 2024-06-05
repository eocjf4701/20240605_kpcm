package kp.cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class KpcmApplication {
    public static void main(String[] args) {
        System.out.println("===========================KOMSCO Pay System Start============================");
        SpringApplication.run(KpcmApplication.class, args);
    }
}