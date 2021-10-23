package Crypto.service;

import com.intersystems.enslib.pex.*;
import com.intersystems.jdbc.IRISObject;
import com.intersystems.jdbc.IRIS;
import com.intersystems.gateway.GatewayContext;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CryptoService extends BusinessService{

    private String URL = "https://api.coinpaprika.com/v1/tickers/btc-bitcoin";
    private IRIS iris;

    @Override
    public void OnInit() throws Exception, ClassNotFoundException {
        iris = GatewayContext.getIRIS();
    }

    @Override
    public Object OnProcessInput(Object messageInput) throws Exception {

        JSONObject jsonObject = readJsonFromUrl(URL);
        JSONObject params = jsonObject.getJSONObject("quotes").getJSONObject("USD");

        IRISObject requestData = (IRISObject)(iris.classMethodObject("Crypto.Request","%New"));

        requestData.set("OriginalJSON", jsonObject.toString());
        requestData.set("PercentChange30m", params.get("percent_change_30m"));
        requestData.set("PercentChange1h", params.get("percent_change_1h"));
        requestData.set("PercentChange24h", params.get("percent_change_24h"));
        requestData.set("PercentChange30d", params.get("percent_change_30d"));
        requestData.set("Price", params.get("price"));
        requestData.set("LastUpdate", jsonObject.get("last_updated").toString());

        IRISObject requestMessage = (IRISObject)(iris.classMethodObject("Crypto.RequestMessage","%New"));
        requestMessage.set("Request", requestData);

        SendRequestAsync("RequestRoutingRule", requestMessage);
        return super.OnProcessInput(messageInput);
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }
}
