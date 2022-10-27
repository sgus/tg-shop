package my.home.tgbot.service;

import my.home.tgbot.button.model.Buttons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface SendMessageService {
   void sendMessages(long userID, String message);

   void sendMessages(long userID, Buttons buttons);

   void sendMessages(SendMessage sendMessage);

   void replyToMessage(Message msgToReply, String message);
}
