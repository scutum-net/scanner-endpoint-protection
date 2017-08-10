import org.junit.jupiter.api.Test;
import scutum.scanner.endpointprotection.contracts.MachineData;
import scutum.scanner.endpointprotection.providers.DataScannerWindows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestDataScannerWindows {
    @Test
    void shouldScan(){
        DataScannerWindows dataScannerWindows = new DataScannerWindows();
        MachineData machineData = dataScannerWindows.scan();

        assertNotNull(machineData);
        assertTrue(!machineData.getProcesses().isEmpty());
    }
}
