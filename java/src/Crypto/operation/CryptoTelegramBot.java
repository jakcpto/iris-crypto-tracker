package Crypto.operation;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.sql.*;

public class CryptoTelegramBot extends TelegramLongPollingBot {

    private final Statement statement;

    public CryptoTelegramBot() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:/jgw/db/Users.s3db");
        statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists 'SubscribedUsers' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'chat_id' INT, UNIQUE ('chat_id'))");
    }

    @Override
    public String getBotUsername() {
        return "iris_crypto_tracker_bot";
    }

    @Override
    public String getBotToken() {
        return "2080795819:AAEj_TRoI7KPu6RvKZDoIlB4g_sCWIRczok";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){

            switch (update.getMessage().getText()){
                case ("/start"):
                    subscribeUser(update.getMessage().getChatId());
                    break;

                case ("/stop"):
                    unsubscribeUser(update.getMessage().getChatId());
                    break;

                case ("/info"):
                    sendMessageInfo(update.getMessage().getChatId());
                    break;
            }
        }
    }

    private void subscribeUser(Long idChat) {
        try {
            statement.execute("INSERT OR IGNORE INTO 'SubscribedUsers' ('chat_id') VALUES ("+idChat+")");
            sendMessage(idChat, "Subscribe successful!");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private void unsubscribeUser(Long idChat){
        try {
            statement.execute("DELETE FROM 'SubscribedUsers' WHERE chat_id = "+idChat+"");
            sendMessage(idChat, "You unsubscribed!");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void sendMessageToSubscribers(String text) throws Exception{
        ResultSet resultSet = statement.executeQuery("SELECT * FROM 'SubscribedUsers'");
        SendMessage sendMessage = new SendMessage();

        while(resultSet.next()){
            sendMessage.setChatId(String.valueOf(resultSet.getInt("chat_id")));
            sendMessage.setText(text);
            execute(sendMessage);
        }
    }

    private void sendMessageInfo(Long idChat){
        String stringBuilder = "Hello it is bot created for Interoperability contest\n\n" +
                "Command:\n" +
                "/info - Show info about bot and available commands\n" +
                "/start - Subscribe sending\n" +
                "/stop - Unsubscribe sending\n\n" +
                "Bot sending actual info cryptocoin BTC - bitcoin";

        sendMessage(idChat, stringBuilder);
    }

    private void sendMessage(Long idChat, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(idChat.toString());

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
