package com.example.tgbot2.config;

import com.example.tgbot2.handle.ButtonContainer;
import com.example.tgbot2.handle.CommandExec;
import com.example.tgbot2.handle.CommandContainer;
import com.example.tgbot2.handle.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final MessageHandler messageHandler;
    private final CommandContainer commandContainer;
    private final ButtonContainer buttonContainer;

    @Autowired
    public TelegramBot(BotConfig botConfig, MessageHandler messageHandler, CommandContainer commandContainer, ButtonContainer buttonContainer) {
        this.botConfig = botConfig;
        this.messageHandler = messageHandler;
        this.commandContainer = commandContainer;
        this.buttonContainer = buttonContainer;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().isUserMessage()) {
            String message = update.getMessage().getText().trim();
            Map<String, String> data = new HashMap<>();
            data.put("chatId", update.getMessage().getChatId().toString());
            data.put("userName", update.getMessage().getFrom().getUserName());
            log.info("Received an update with text " + update.getMessage().getText() + " in a private chat from " + update.getMessage().getChatId());
            messageHandler.handleMessages(update);
            if (commandContainer.checkCommand(message)) {
                String commandName = message.split(" ")[0].toLowerCase();
                commandContainer.getStatus(commandName).doAction(this, data);
            } else if (buttonContainer.checkButton(message)) {
                log.info("button click");
                CommandExec status = commandContainer.getStatus(buttonContainer.getCommandName(message));
                status.doAction(this, data);
            } else {
//                commandContainer.getNoCommand().doAction(this, data);
                log.info("No command");
            }


            log.info("Finished processing an update with text from a private chat " + update.getMessage().getChatId());
            return;
        }
        log.error("onUpdateReceived couldn't find a method to handle a message " + update.getMessage().toString());
        if (update.hasMyChatMember()) {
            log.info("The bot was added to a chat. The chat is " + update.getMyChatMember().getChat().getTitle());
            //логика на добавление бота в чат
//            registrationService.registerChat(update);
//            execute(SendMessage.builder()
//                    .text(Replies.ADDED_TO_CHAT.text)
//                    .chatId(update.getMyChatMember().getChat().getId())
//                    .build());
        }
    }
}


