package com.clayfin.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.github.javafaker.Faker;

public class EmployeeCsvGenerator {

    public String csvCreator() {
        String csvFilePath = "C:\\Users\\Soham\\Desktop\\Clyfin\\employees.csv"; // Output CSV file path
        int numberOfRecords = 100_000;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {

            Faker faker = new Faker();

            for (int i = 0; i < numberOfRecords; i++) {
                String name = faker.name().fullName();
                String email = faker.internet().emailAddress();
                String mobileNumber = faker.phoneNumber().cellPhone();
                String panNumber = generateRandomPanNumber();

                String line = String.format("%s,%s,%s,%s", name, email, mobileNumber, panNumber);
                bw.write(line);
                bw.newLine();
            }

            return "CSV file with random employee data generated successfully.";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private static String generateRandomPanNumber() {
        // Generate a random PAN number with the pattern "AAAAA1111A"
        String panNumber = "";
        String alphaCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numericCharacters = "0123456789";
        for (int i = 0; i < 5; i++) {
            panNumber += alphaCharacters.charAt((int) (Math.random() * alphaCharacters.length()));
        }
        for (int i = 0; i < 4; i++) {
            panNumber += numericCharacters.charAt((int) (Math.random() * numericCharacters.length()));
        }
        panNumber += alphaCharacters.charAt((int) (Math.random() * alphaCharacters.length()));
        return panNumber;
    }
}

