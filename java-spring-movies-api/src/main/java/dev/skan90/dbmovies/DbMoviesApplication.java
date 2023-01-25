package dev.skan90.dbmovies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DbMoviesApplication {

    public static void main(String[] args) {

        SpringApplication.run(DbMoviesApplication.class, args);
    }
}
