package Crypto.operation;

import com.intersystems.enslib.pex.*;
import com.intersystems.gateway.GatewayException;
import com.intersystems.jdbc.IRISObject;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CryptoMailOperation extends BusinessOperation {

    private final static String username = "v.berkutov@teccod.ru";
    private final static String password = "xfmpiwohnmokwetw";

    private static Session session;

    @Override
    public void OnInit() throws Exception, GatewayException {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host", "smtp.yandex.ru");
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.ssl.enable", "true");

        session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        super.OnInit();
    }

    @Override
    public Object OnMessage(Object request) throws Exception {
        
        RequestModel requestModel = new RequestModel(request);

        String message = requestModel.getDate() + "\n\n" +
                "Price : " + String.format("%.2f", requestModel.getPrice()) + " $\n" +
                "Change for 1 hour : " + requestModel.getPercentChange1h() + " %\n" +
                "Change for 24 hours : " + requestModel.getPercentChange24h() + " %\n" +
                "Change for 30 days : " + requestModel.getPercentChange30d() + " %\n";

        sendMail("Cryptocoin info", message, requestModel.getSendTo());

        return super.OnMessage(request);
    }

    void sendMail(String title, String textMessage, String sendTo) throws Exception {

        javax.mail.Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username, "Iris-crypto-tracker"));
        message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(sendTo));
        message.setSubject(title);
        message.setText(textMessage);

        Transport.send(message);

    }
}
