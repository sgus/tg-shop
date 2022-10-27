package com.example.tgbot2.handle;

import com.example.tgbot2.handle.impl.ShowDeveloper;
import com.example.tgbot2.handle.impl.StartCommandExec;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.example.tgbot2.handle.CommandName.SHOW_DEVELOPER;
import static com.example.tgbot2.handle.CommandName.START_COMMAND;

@Component
public class CommandContainer { //сами команды, которые отрабатывают при нажатии кнопки

    private final Map<String, CommandExec> commandMap;

    public CommandContainer(StartCommandExec startCommand, ShowDeveloper showDeveloper) {
        this.commandMap = new HashMap<>();

        commandMap.put(START_COMMAND.getCommandName(), startCommand);
        commandMap.put(SHOW_DEVELOPER.getCommandName(), showDeveloper);

    }

    public boolean checkCommand(String commandName) {
        return commandMap.containsKey(commandName);
    }

    public CommandExec getStatus(String commandName) {
        return commandMap.get(commandName);
    }

}