package com.emilia;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.platform.commons.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class GoodForBob {
    @SneakyThrows
    public static void main(String[] args) {
        String port = "8080,1099";
        Arrays.stream(port.split(","))
                        .forEach(GoodForBob::killProcessWithPort);
        System.out.println("ok");
    }

    private static void killProcessWithPort(String port){
        String command = "powershell.exe netstat -ano -p tcp";
        List<String> lines = executeCommand(command);

        lines.stream()
                .filter(StringUtils::isNotBlank)
                .skip(2)
                .map(String::trim)
                .map(x->x.split("\\s+"))
                .filter(x-> x[1].matches(".*:"+port))
                .filter(x->x[3].equalsIgnoreCase("LISTENING"))
                .map(x->x[4])
                .forEach(x->executeCommand("powershell.exe kill "+x));
    }
    @SneakyThrows
    public static List<String> executeCommand(String command){
        Process powerShellProcess = Runtime.getRuntime().exec(command);
        powerShellProcess.getOutputStream().close();
        BufferedReader stdout = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
        return IOUtils.readLines(stdout);
    }
}
