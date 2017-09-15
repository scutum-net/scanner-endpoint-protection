package scutum.scanner.endpointprotection.processors;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import scutum.core.contracts.Alert;
import scutum.core.contracts.Processor;
import scutum.core.contracts.ScannedData;
import scutum.core.contracts.endpointprotection.ProcessData;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataProcessorWindows implements Processor {
    private final Gson serializer;

    public DataProcessorWindows() {
        serializer = new Gson();
    }

    @Override
    public Integer getProviderId() {
        return 1;
    }

    @Override
    public Alert[] process(ScannedData data) {
        Integer providerId = data.getProviderId();
        Integer machineId = data.getMachineId();
        Integer scanId = data.getScanId();
        String jsonInput = data.getData();

        List<ProcessData> processes = getProcesses(jsonInput);
        return getAlerts(processes);
    }

    private Alert[] getAlerts(List<ProcessData> processes) {
        Optional<ProcessData> alertedProcess = processes.stream().filter(x->x.getPath().toLowerCase().contains("notepad.exe")).findAny();
        if (alertedProcess.isPresent()) {
            ProcessData processData = alertedProcess.get();
            String dataOutput = serializer.toJson(processData);
            Alert alert = new Alert(dataOutput);
            return new Alert[]{alert};
        }
        return new Alert[0];
    }

    private List<ProcessData> getProcesses(String jsonInput) {
        Type listType = new TypeToken<ArrayList<ProcessData>>() {}.getType();
        List<ProcessData> processes = serializer.fromJson(jsonInput, listType);
        return processes;
    }
}