package scutum.scanner.endpointprotection.providers;

import com.google.gson.Gson;
import scutum.core.contracts.IScanner;
import scutum.core.contracts.endpointprotection.ProcessData;
import scutum.core.contracts.endpointprotection.MachineData;
import scutum.core.contracts.ScannedData;
import scutum.scanner.endpointprotection.utils.ProcessHelper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


/**
 * Java class that monitors processes and their parents
 */
//todo: add hash
//todo: add file size
//todo: try to add dependencies
//todo: make it more efficient
public class DataScannerLinux implements IScanner {

    private final Integer scanType;
    private final String hostName;
    private final String customerName;
    private final Integer version;

    public DataScannerLinux(String hostName, String customerName, Integer  version, Integer scanType ) {
        this.hostName = hostName;
        this.customerName = customerName;
        this.version = version;
        this.scanType = scanType;
    }

    @Override
    public ScannedData scan() {
        MachineData machineData = new MachineData(hostName, customerName, version, scanType, LocalDateTime.now(), new ArrayList<>());
        try {
            // add processes from task manager
            Collection<ProcessData> processes = getPlainProcesses();

            // add all to the final model
            machineData.getProcesses().addAll(processes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ScannedData(Integer.valueOf(customerName),9,"9",3,9, new Gson().toJson(machineData));
    }

    private Collection<ProcessData> getPlainProcesses() throws IOException {
        Collection<ProcessData> processes = new ArrayList<>();

        Collection<String> lines = ProcessHelper.runCommand("ps -ef");
        lines.forEach(line -> {

            StringTokenizer stringTokenizer = new StringTokenizer(line);
            String[] words = new String[stringTokenizer.countTokens()];
            int index = 0;
            while (stringTokenizer.hasMoreTokens()) {
                words[index++] = stringTokenizer.nextToken();
            }
            ProcessData processData = new ProcessData(Integer.valueOf(words[1]), Integer.valueOf(words[2]), words[0], words[7], "123456l", 34234);
            processes.add(processData);
        });
        return processes;
    }

}
