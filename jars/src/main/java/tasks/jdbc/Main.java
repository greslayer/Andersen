package tasks.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
@ComponentScan(basePackages = "tasks.config")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
