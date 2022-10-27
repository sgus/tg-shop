package com.example.tgbot2.handle.impl;

import com.example.tgbot2.config.TelegramBot;
import com.example.tgbot2.handle.ButtonName;
import com.example.tgbot2.handle.CommandExec;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ShowDeveloper implements CommandExec {

    private final String MESSAGE = "Ооо дааа\n" +
            "сюда нужно кнопки с паганацией заебошить.";


    @Override
    public void doAction(TelegramBot bot, Map<String, String> data) {
        String chatId = data.get("chatId");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(MESSAGE+ "\n" +
                "Жопа ты " + data.get("userName"));
        sendMessage.setReplyMarkup(getKeyboard2());

        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private ReplyKeyboardMarkup getKeyboard2() {

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ButtonName.RD_BUTTON.getButtonName()));
        row1.add(new KeyboardButton(ButtonName.HYDRANT_MAP_BUTTON.getButtonName()));
        row1.add(new KeyboardButton(ButtonName.SCHEDULE_BUTTON.getButtonName()));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(ButtonName.KNOWLEDGE_BASE.getButtonName()));
        row2.add(new KeyboardButton(ButtonName.DEVELOPER_BUTTON.getButtonName()));
        row2.add(new KeyboardButton(ButtonName.CHARACTERISTICS1.getButtonName()));
        row2.add(new KeyboardButton(ButtonName.CHARACTERISTICS2.getButtonName()));
        KeyboardRow row3 = new KeyboardRow();
        row2.add(new KeyboardButton(ButtonName.KNOWLEDGE_BASE.getButtonName()));
        row2.add(new KeyboardButton(ButtonName.DEVELOPER_BUTTON.getButtonName()));
        row2.add(new KeyboardButton(ButtonName.CHARACTERISTICS1.getButtonName()));
        row2.add(new KeyboardButton(ButtonName.CHARACTERISTICS2.getButtonName()));
        row2.add(new KeyboardButton(ButtonName.CHARACTERISTICS3.getButtonName()));
        row2.add(new KeyboardButton(ButtonName.CHARACTERISTICS4.getButtonName()));

        List<KeyboardRow> keyboardList = new ArrayList<>();
        keyboardList.add(row1);
        keyboardList.add(row2);
        keyboardList.add(row3);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardList);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }
}