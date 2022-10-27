package my.home.tgbot.service;

import my.home.tgbot.service.enums.Appendix;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public interface DynamicButtonsService {

    ReplyKeyboardMarkup createMarkup(long chatID, Appendix appendix);
    ReplyKeyboardMarkup createMarkup(long chatID, String appendix);

}
