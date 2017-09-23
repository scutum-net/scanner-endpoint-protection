package scutum.dummy;

import com.google.gson.Gson;
import scutum.core.contracts.IScanner;
import scutum.core.contracts.ScannedData;

public class DummyScanner implements IScanner {
    private Gson gson = new Gson();
    @Override
    public ScannedData scan() {
        DummyScannerData data = new DummyScannerData();
        data.setId(1);
        data.setData("Some data");

        String json = gson.toJson(data);

        return new ScannedData(
                1,
                1001,
                "DummyScanner",
                1000, // TODO: change to string
                1,
                json
        );
    }
}
