package my.home.tgbot.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import my.home.tgbot.button.model.Buttons;
import my.home.tgbot.config.TelegramBotConfig;
import my.home.tgbot.service.FormatService;
import my.home.tgbot.service.SendMessageService;
import my.home.tgbot.utils.ButtonsMappingUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@Slf4j
public class SendMessageServiceImpl extends DefaultAbsSender implements SendMessageService {

    private final FormatService formatService;
    private final TelegramBotConfig botConfig;
//    private final DynamicButtonsService dynamicButtonsService;

    public SendMessageServiceImpl(FormatService formatService, TelegramBotConfig botConfig ) {
        super(new DefaultBotOptions());
        this.formatService = formatService;
        this.botConfig = botConfig;
//        this.dynamicButtonsService = dynamicButtonsService;
    }

    @SneakyThrows
    @Override
    public void sendMessages(long userID, String message) {
        message = formatService.format(userID, message);
        log.info("SenderService attempted to send a message: " + message);
        execute(new SendMessage(Long.toString(userID), message));
    }

    @SneakyThrows
    @Override
    public void sendMessages(long userID, Buttons buttons) {
        String message = formatService.format(userID, buttons.getMessage());
        log.info("SenderService attempted to send a message with buttons: " + buttons.getMessage());

        execute(SendMessage.builder()
                .text(message)
                .chatId(userID)
                .replyMarkup(
                        ButtonsMappingUtils.createStaticMarkup(buttons.getKeyboard()))
                .parseMode("HTML")
                .build()
        );
    }

    @SneakyThrows
    @Override
    public void sendMessages(SendMessage sendMessage) {
        log.info("SenderService attempted to send a SendMessage: " + sendMessage.toString());
        sendMessage.setText(formatService.format(Long.parseLong(sendMessage.getChatId()), sendMessage.getText()));
        sendMessage.setParseMode("HTML");
        execute(sendMessage);
    }

    @SneakyThrows
    @Override
    public void replyToMessage(Message msgToReply, String message) {
        log.info("SenderService attempted to reply to a message: \"" + msgToReply.getText() + "\" with text: " + message);
        message = formatService.format(msgToReply.getFrom().getId(), message);
        execute(SendMessage.builder()
                .replyToMessageId(msgToReply.getMessageId())
                .text(message)
                .chatId(msgToReply.getChatId())
                .parseMode("HTML")
                .build());
    }
//
//    private final TelegramBot telegramBot;
//
//    @Autowired
//    public BotMessageSendServiceImpl(TelegramBot telegramBot) {
//        this.telegramBot = telegramBot;
//    }
//
//    @Override
//    public void sendMessage(String chatId, String message) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(message);
//
//        try {
//            telegramBot.execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}
