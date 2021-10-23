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
        IRISObject req = (IRISObject)request;
        IRISObject requestMessage = (IRISObject) req.get("Request");

        double price = requestMessage.getDouble("Price");
        double percentChange1h = requestMessage.getDouble("PercentChange1h");
        double percentChange24h = requestMessage.getDouble("PercentChange24h");
        double percentChange30d = requestMessage.getDouble("PercentChange30d");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.ENGLISH);
        String date = output.format(sdf.parse(requestMessage.getString("LastUpdate")));

        String message = date + "\n\n" +
                "Price : " + String.format("%.2f", price) + " $\n" +
                "Change for 1 hour : " + percentChange1h + " %\n" +
                "Change for 24 hours : " + percentChange24h + " %\n" +
                "Change for 30 days : " + percentChange30d + " %\n";
        
        bot.sendMessageToSubscribers(message);

        return super.OnMessage(request);
    }
    
}
