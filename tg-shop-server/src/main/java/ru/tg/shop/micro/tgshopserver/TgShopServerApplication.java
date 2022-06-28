package ru.tg.shop.micro.tgshopserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TgShopServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TgShopServerApplication.class, args);
    }

}
