package ru.tg.shop.micro.tgshopbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TgShopBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TgShopBotApplication.class, args);
    }

}
