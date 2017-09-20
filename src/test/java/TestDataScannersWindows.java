/**
 import com.google.gson.Gson;
 import org.junit.jupiter.api.Test;
 import scutum.core.contracts.Alert;
 import scutum.core.contracts.ScannedData;
 import scutum.core.contracts.endpointprotection.MachineData;
 import scutum.core.contracts.endpointprotection.ProcessData;
 import scutum.scanner.endpointprotection.processors.DataProcessorWindows;
 import scutum.scanner.endpointprotection.providers.DataScannerWindows;

 import java.util.ArrayList;
 import java.util.List;

 import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.junit.jupiter.api.Assertions.assertNotNull;
 import static org.junit.jupiter.api.Assertions.assertTrue;

 **/



/**
class TestDataScannerWindows {
    @Test
    void shouldScan() {
        DataScannerWindows dataScannerWindows = new DataScannerWindows("", "", 1, 1);
        MachineData machineData = dataScannerWindows.scan();

        assertNotNull(machineData);
        assertTrue(!machineData.getProcesses().isEmpty());
    }

    @Test
    void shouldAlert() {
        Gson serializer = new Gson();

        DataProcessorWindows dataProcessorWindows = new DataProcessorWindows();
        List<ProcessData> processDataAlert = new ArrayList<>();
        processDataAlert.add(new ProcessData(1, 2, "", "notepad.exe", "", 2));

        List<ProcessData> processDataNoAlert = new ArrayList<>();
        processDataNoAlert.add(new ProcessData(1, 2, "", "mspaint.exe", "", 2));


        ScannedData scannedData = new ScannedData(1, 1, 1, serializer.toJson(processDataAlert));
        Alert[] alerts = dataProcessorWindows.process(scannedData);
        assertEquals(1, alerts.length);

        scannedData = new ScannedData(1, 1, 1, serializer.toJson(processDataNoAlert));
        alerts = dataProcessorWindows.process(scannedData);
        assertEquals(0, alerts.length);
    }
}

 **/