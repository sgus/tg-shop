package my.home.tgbot3.exec;

import my.home.tgbot3.config.TelegramBot;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface CommandExec {
    void doAction(TelegramBot bot, Map<String, String> data);
}