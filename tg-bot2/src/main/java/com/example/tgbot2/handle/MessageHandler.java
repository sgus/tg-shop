package com.example.tgbot2.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class MessageHandler {

    private final Map<String, String> stringMap;

    public MessageHandler() {
        stringMap = new HashMap<>();

        stringMap.put(ButtonName.RD_BUTTON.getButtonName(), CommandName.SHOW_LIST_RD_COMMAND.getCommandName());
        stringMap.put(ButtonName.HYDRANT_MAP_BUTTON.getButtonName(), CommandName.SHOW_LIST_MAP_HYDRANT_COMMAND.getCommandName());
        stringMap.put(ButtonName.SCHEDULE_BUTTON.getButtonName(), CommandName.SHOW_LIST_SCHEDULES_COMMAND.getCommandName());
        stringMap.put(ButtonName.KNOWLEDGE_BASE.getButtonName(), CommandName.SHOW_KNOWLEDGE_BASE_COMMAND.getCommandName());
        stringMap.put(ButtonName.DEVELOPER_BUTTON.getButtonName(), CommandName.SHOW_DEVELOPER.getCommandName());

        stringMap.put(ButtonName.CHARACTERISTICS1.getButtonName(), CommandName.SHOW_CHARACTERISTICS1_COMMAND.getCommandName());
        stringMap.put(ButtonName.CHARACTERISTICS2.getButtonName(), CommandName.SHOW_CHARACTERISTICS2_COMMAND.getCommandName());
        stringMap.put(ButtonName.CHARACTERISTICS3.getButtonName(), CommandName.SHOW_CHARACTERISTICS3_COMMAND.getCommandName());
        stringMap.put(ButtonName.CHARACTERISTICS4.getButtonName(), CommandName.SHOW_CHARACTERISTICS4_COMMAND.getCommandName());

    }


    public void handleMessages(Update update) {
        String message = update.getMessage().getText();
        long userID = update.getMessage().getChatId();
        log.info("Successfully received the message to the Message handler: " + message);

    }



    public ReplyKeyboard inlineOne(String text, String numberText,
                                   String text2, String numberText2) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton.setText(text);
        inlineKeyboardButton.setCallbackData(numberText);
        inlineKeyboardButton1.setText(text2);
        inlineKeyboardButton1.setCallbackData(numberText2);
        buttons1.add(inlineKeyboardButton);
        buttons1.add(inlineKeyboardButton1);
        buttons.add(buttons1);
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        return markupKeyboard;
    }
}
