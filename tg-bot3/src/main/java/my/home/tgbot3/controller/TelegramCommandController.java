package my.home.tgbot3.controller;

import lombok.extern.slf4j.Slf4j;
import my.home.tgbot3.annotation.CommandMapping;
import my.home.tgbot3.annotation.TgController;

@TgController
@Slf4j
public class TelegramCommandController  implements ControllerTG    {
    @CommandMapping("/start")
    public void startCommand(){
        log.info("Hi Endpoint");
        System.out.println("Hi Endpoint");
    }
}
