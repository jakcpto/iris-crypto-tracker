package Crypto.operation;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.intersystems.enslib.pex.*;
import com.intersystems.jdbc.IRISObject;
import com.intersystems.jdbc.IRIS;
import com.intersystems.gateway.GatewayContext;

import java.io.*;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TelegramBotOperation extends BusinessOperation {

    private static CryptoTelegramBot bot;

    @Override
    public void OnInit() throws Exception{

        try {
            bot = new CryptoTelegramBot();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object OnMessage(Object request) throws Exception {
        
        RequestModel requestModel = new RequestModel(request);

        String message = requestModel.getDate() + "\n\n" +
                "Price : " + String.format("%.2f", requestModel.getPrice()) + " $\n" +
                "Change for 1 hour : " + requestModel.getPercentChange1h() + " %\n" +
                "Change for 24 hours : " + requestModel.getPercentChange24h() + " %\n" +
                "Change for 30 days : " + requestModel.getPercentChange30d() + " %\n";
        
        bot.sendMessageToSubscribers(message);

        return super.OnMessage(request);
    }
    
}
