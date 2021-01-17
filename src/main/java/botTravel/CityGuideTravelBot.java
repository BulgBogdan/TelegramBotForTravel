package botTravel;

import botTravel.entity.City;
import botTravel.service.CityService;
import botTravel.service.Impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@PropertySource("classpath:telegram.properties")
public class CityGuideTravelBot extends TelegramLongPollingBot {

    @Autowired
    private CityService cityService = new CityServiceImpl();

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        //get and create message
        Message inMessage = update.getMessage();
        SendMessage outMessage = new SendMessage();

        String textMessage = inMessage.getText();
        long chatId = inMessage.getChatId();

        try {

            if (update.hasMessage() && update.getMessage().hasText()) {

                City city = cityService.getByName(textMessage);

                String listCity = "";
                for (City cityFromBase : cityService.getAll()) {
                    listCity = listCity + cityFromBase.getCityName() + "\n";
                }

                //chat where the incoming message
                outMessage.setChatId(chatId);

                if (textMessage.equals("/start")) {
                    outMessage.setText("Здравствуй Дорогой друг! " +
                            "Я туристический Бот, могу тебе рассказать про города, которые я знаю: " +
                            "\n" + listCity);
                } else if (textMessage.equalsIgnoreCase("bye")
                        || textMessage.equalsIgnoreCase("пока")) {
                    outMessage.setText("До встречи, добрый человек. Надеюсь, я тебе помог!");
                } else if (city != null) {
                    outMessage.setText(city.getCityInfo());
                } else {
                    outMessage.setText("К сожалению, я не знаю этого слова.");
                }

                execute(outMessage);

                //other than text message
            } else {
                outMessage.setChatId(chatId);
                outMessage.setText("Только города(");
                execute(outMessage);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
