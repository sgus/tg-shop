package my.home.tgbot.handler.impl;

import com.vdurmont.emoji.EmojiManager;
import lombok.extern.slf4j.Slf4j;
import my.home.tgbot.handler.MessageHandler;
import my.home.tgbot.service.SendMessageService;
import my.home.tgbot.utils.TelegramUtils;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Controller
public class MessageHandlerImpl implements MessageHandler {
    private final SendMessageService senderService;

    public MessageHandlerImpl(SendMessageService senderService) {
        this.senderService = senderService;
    }


    @Override
    public void handleMessages(Update update) {
        String message = update.getMessage().getText();
        long userID = update.getMessage().getChatId();
        log.info("Successfully received the message to the Message handler: " + message);
        if (update.getMessage().isCommand()) log.info("Attempted to use a command!");
        else if (EmojiManager.containsEmoji(message.substring(0, 3))
                || TelegramUtils.checkForUnsupportedEmoji(message)) {//if the message is a button
            buttonsHandler(userID, message);
        } else if (message.startsWith("@"))
//            senderService.sendMessages(userID,//if the message is a username
//                    logicMessagesHandler.handleUsernames(userID, message));
            senderService.sendMessages(userID,"Воспользуйтесь клавиатурой");
        else if (message.length() >= 40)
            senderService.sendMessages(userID,"Воспользуйтесь клавиатурой");
//            senderService.sendMessages(userID, logicMessagesHandler.setMessage(userID, message));
    }

    private void buttonsHandler(long chatID, String message) {
//        if (!userService.existsByTelegramId(chatID)) {
//            senderService.sendMessages(chatID, Buttons.USER_NOT_FOUND);
//            return;
//        }
//        Optional<Buttons> logicButtons = logicMessagesHandler.handleStaticMessages(chatID, message);
//        if (logicButtons.isPresent()) senderService.sendMessages(chatID, logicButtons.get());
//        else if (message.startsWith("️⃣", 1) || message.startsWith(ButtonsMappingUtils.PREVIOUS_PAGE) || message.startsWith(ButtonsMappingUtils.NEXT_PAGE))
//            senderService.sendMessages(numpadHandler.handleMessages(chatID, message));
//        else senderService.sendMessages(chatID, receivedMessagesContainer.getByText(message));
    }
}
