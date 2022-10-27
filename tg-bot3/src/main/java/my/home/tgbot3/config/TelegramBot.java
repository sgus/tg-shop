package my.home.tgbot3.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
@PropertySource("application.properties")
@ConfigurationProperties(prefix = "bot")
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    String botName;
    @Value("${bot.token}")
    String token;

//    private final MessageHandler messageHandler;



    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().isUserMessage()) {
            String message = update.getMessage().getText().trim();
            Map<String, String> data = new HashMap<>();
            data.put("chatId", update.getMessage().getChatId().toString());
            data.put("userName", update.getMessage().getFrom().getUserName());
           log.info("Received an update with text " + update.getMessage().getText() + " in a private chat from " + update.getMessage().getChatId());
//            messageHandler.handleMessages(update);
//            if (commandContainer.checkCommand(message)) {
//                String commandName = message.split(" ")[0].toLowerCase();
//                commandContainer.getStatus(commandName).doAction(this, data);
//            } else if (buttonContainer.checkButton(message)) {
//                log.info("button click");
//                CommandExec status = commandContainer.getStatus(buttonContainer.getCommandName(message));
//                status.doAction(this, data);
//            } else {
////                commandContainer.getNoCommand().doAction(this, data);
//                log.info("No command");
//            }


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


