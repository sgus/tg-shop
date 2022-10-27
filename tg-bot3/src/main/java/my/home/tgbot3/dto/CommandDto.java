package my.home.tgbot3.dto;

import lombok.Data;
import my.home.tgbot3.config.TelegramBot;
import my.home.tgbot3.exec.CommandExec;

import java.util.Map;

@Data
public class CommandDto {
    private String commandName;
    private String commandQuery;
    private CommandExec commandExec;

    public void doAction(TelegramBot bot, Map<String, String> data) {
        commandExec.doAction(bot, data);
    }
}