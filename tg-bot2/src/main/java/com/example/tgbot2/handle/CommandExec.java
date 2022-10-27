package com.example.tgbot2.handle;

import com.example.tgbot2.config.TelegramBot;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface CommandExec {
    void doAction(TelegramBot bot, Map<String, String> data);
}