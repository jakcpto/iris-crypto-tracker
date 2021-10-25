package Crypto.operation;

import com.intersystems.jdbc.IRISObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class RequestModel {

    private String sendTo;
    private String date;
    private double price;
    private double percentChange1h;
    private double percentChange24h;
    private double percentChange30d;
    private final IRISObject request;
    private final IRISObject requestMessage;

    public RequestModel(Object request) {
        this.request = (IRISObject) request;
        this.requestMessage = (IRISObject) this.request.get("Request");
    }

    public String getSendTo() {
        return request.getString("ToEmailAddress");
    }

    public String getDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.ENGLISH);
            return output.format(sdf.parse(requestMessage.getString("LastUpdate")));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double getPrice() {
        return requestMessage.getDouble("Price");
    }

    public double getPercentChange1h() {
        return requestMessage.getDouble("PercentChange1h");
    }

    public double getPercentChange24h() {
        return requestMessage.getDouble("PercentChange24h");
    }

    public double getPercentChange30d() {
        return requestMessage.getDouble("PercentChange30d");
    }
}
