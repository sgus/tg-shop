package my.home.tgbot.config;

import lombok.extern.slf4j.Slf4j;
import my.home.tgbot.handler.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotConfig botConfig;
    private final MessageHandler messageHandler;


    @Autowired
    public TelegramBot(TelegramBotConfig botConfig, MessageHandler messageHandler) {
        this.botConfig = botConfig;
        this.messageHandler = messageHandler;
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
            log.info("Received an update with text " + update.getMessage().getText() + " in a private chat from " + update.getMessage().getChatId());
            messageHandler.handleMessages(update);
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


