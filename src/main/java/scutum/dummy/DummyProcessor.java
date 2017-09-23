package scutum.dummy;

import scutum.core.contracts.Alert;
import scutum.core.contracts.IProcessor;
import scutum.core.contracts.ScannedData;

public class DummyProcessor implements IProcessor {
    @Override
    public Integer getProviderId() {
        return 1001;
    }

    @Override
    public Alert[] process(ScannedData data) {
        if (data.getData().isEmpty()) {
            Alert alert = new Alert(
                    "Empty string is sent");
            return new Alert[]{alert};
        } else {
            return new Alert[]{};
        }
    }
}
