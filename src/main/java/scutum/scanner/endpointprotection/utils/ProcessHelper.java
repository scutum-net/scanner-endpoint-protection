package scutum.scanner.endpointprotection.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class ProcessHelper {

    public static Collection<String> runCommand(String runCommand) throws IOException {
        Collection<String> lines = new ArrayList<>();
        java.lang.Process process = Runtime.getRuntime().exec(runCommand);
        Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
        boolean isNoFirstLine = false;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            System.out.println(line);

            if (isNoFirstLine) {
                lines.add(line);
            }
            isNoFirstLine = true;
        }
        scanner.close();
        return lines;
    }
}
