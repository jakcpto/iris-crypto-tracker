package Crypto.operation;

import com.intersystems.enslib.pex.BusinessOperation;
import com.intersystems.jdbc.IRISObject;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

public class CryptoCsvOperation extends BusinessOperation {

    CSVWriter writer;
    String path = "/jgw/db/crypto.csv";

    @Override
    public void OnInit() throws Exception {
        if(!new File(path).exists()){
            writer = new CSVWriter(new FileWriter(path, true));
            writer.writeNext(new String[]{"date", "price", "Change_1h", "Change_24h", "Change_30d"});
        }else {
            writer = new CSVWriter(new FileWriter(path, true));
        }

        super.OnInit();
    }

    @Override
    public Object OnMessage(Object request) throws Exception {

        RequestModel requestModel = new RequestModel(request);

        writer.writeNext(new String[]{
                requestModel.getDate(),
                String.valueOf(requestModel.getPrice()),
                String.valueOf(requestModel.getPercentChange1h()),
                String.valueOf(requestModel.getPercentChange24h()),
                String.valueOf(requestModel.getPercentChange30d())
        });

        writer.flush();

        return super.OnMessage(request);
    }
}
