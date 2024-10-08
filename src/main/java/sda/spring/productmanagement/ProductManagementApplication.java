package sda.spring.productmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "sda.spring.productmanagement")
public class ProductManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProductManagementApplication.class, args);
    }

}
//14.09.2024 1:10:00 additional things