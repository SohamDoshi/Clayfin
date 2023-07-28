package com.clayfin.services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvFileSplitter {

    public String splitter() {
        String largeCsvFilePath = "C:\\Users\\Soham\\Desktop\\Clyfin\\employees.csv"; 
        int numberOfFiles = 10;

        try {
            List<String> lines = readLargeCsvFile(largeCsvFilePath);
            List<List<String>> splitData = splitDataIntoEqualParts(lines, numberOfFiles);

            for (int i = 0; i < splitData.size(); i++) {
                writeCsvFile(splitData.get(i), "C:\\Users\\Soham\\Desktop\\Clyfin\\output\\Employee_" + (i + 1) + ".csv");
            }

            return "CSV file split completed successfully.";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private static List<String> readLargeCsvFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static List<List<String>> splitDataIntoEqualParts(List<String> data, int numberOfParts) {
        int dataSize = data.size();
        int partSize = dataSize / numberOfParts;

        List<List<String>> splitData = new ArrayList<>();

        for (int i = 0; i < numberOfParts; i++) {
            int fromIndex = i * partSize;
            int toIndex = (i == numberOfParts - 1) ? dataSize : fromIndex + partSize;
            List<String> part = data.subList(fromIndex, toIndex);
            splitData.add(part);
        }

        return splitData;
    }

    private static void writeCsvFile(List<String> lines, String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }
}

