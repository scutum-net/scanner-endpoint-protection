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
import java.util.stream.Collectors;


//import com.sun.jna.Native;
//import com.sun.jna.platform.win32.*;
//import com.sun.jna.win32.W32APIOptions;


/**
 * Java class that monitors processes and their parents
 */
//todo: add hash
//todo: add file size
//todo: try to add dependencies
//todo: make it more efficient
public class DataScannerWindows implements IScanner {

    private final Integer scanType;
    private final String hostName;
    private final String customerName;
    private final Integer version;

    public DataScannerWindows(String hostName, String customerName, Integer  version, Integer scanType ) {
        this.hostName = hostName;
        this.customerName = customerName;
        this.version = version;
        this.scanType = scanType;
    }

    @Override
    public ScannedData scan() {
        // MachineData machineData = new MachineData(hostName, customerName, version, scanType, LocalDateTime.now(), new ArrayList<>());
        Collection<ProcessData> processes = new ArrayList<>();
        // String processData ;
        try {
            // add processes from task manager
            processes = getPlainProcesses();

            // add details - parent process id and full path on disk
            fillParentProcess(processes);

            // add all to the final model
            //processes.addAll(processes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ScannedData(Integer.valueOf(customerName),1,"1",2,1, new Gson().toJson(processes));
    }

    private Collection<ProcessData> getPlainProcesses() throws IOException {
        Collection<ProcessData> processes = new ArrayList<>();
        Collection<String> lines = ProcessHelper.runCommand("tasklist /v /fi \"PID gt 1000\" /fo csv");
        lines.forEach(line -> {
            List<String> vals = Arrays.stream(line.split(",")).map(x -> x.replace("\"", "")).collect(Collectors.toList());
            ProcessData process = new ProcessData(Integer.valueOf(vals.get(1)), -1, vals.get(6), vals.get(0), "123456l", 34234);
            processes.add(process);
        });
        return processes;
    }

    private void fillParentProcess(Collection<ProcessData> processes) throws IOException {

        Set<String> lines = new HashSet<>();
        try {
            char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            for (char c : alphabet) {
                String cmd = "cmd /c wmic process get processid,parentprocessid,executablepath|find \""+c+"\"";
                Collection<String> currentLines = ProcessHelper.runCommand(cmd);
                currentLines.stream().filter(line->!line.isEmpty()).forEach(lines::add);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        lines.forEach(line -> {
            String[] vals = line.trim().replaceAll(" +", ",").split(",");
            Optional<ProcessData> poptional = processes.stream().filter(process -> process.getId() == Integer.valueOf(vals[vals.length-1])).findFirst();
            if (poptional.isPresent()) {
                poptional.get().setParentId(Integer.valueOf(vals[vals.length-2]));

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < vals.length-2; i++) {
                    sb.append(vals[i]);
                    if (i < vals.length-3) {
                        sb.append(" ");
                    }
                }
                poptional.get().setPath(sb.toString());
            }
        });
    }




    //String name = ManagementFactory.getRuntimeMXBean().getName();
    //System.out.println(name);
    //String pid = name.split("@")[0];
    //System.out.println("Pid is:" + pid);

//    private void processNative() {
//        WinNT winNT = (WinNT) Native.loadLibrary(WinNT.class, W32APIOptions.UNICODE_OPTIONS);
//
//        WinNT.HANDLE snapshot = winNT.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0));
//
//        Tlhelp32.PROCESSENTRY32.ByReference processEntry = new Tlhelp32.PROCESSENTRY32.ByReference();
//
//        while (winNT.Process32Next(snapshot, processEntry)) {
//            System.out.println(processEntry.th32ProcessID + "\t" + Native.toString(processEntry.szExeFile));
//        }
//
//        winNT.CloseHandle(snapshot);
//    }
}
