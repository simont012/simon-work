// Impressive Java Program
// Author: Your Name
// Description: This program demonstrates file handling, data processing, and simple GUI in Java.

import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;

public class ImpressiveJavaProgram {

    public static void main(String[] args) {
        String fileName = "sample_data.txt";

        // Step 1: Create a sample data file
        if (!Files.exists(Paths.get(fileName))) {
            createSampleData(fileName);
            System.out.println("Sample data file '" + fileName + "' created.");
        }

        // Step 2: Read and process the data
        Map<String, Double> categories = readAndProcessData(fileName);
        System.out.println("Processed Data:");
        categories.forEach((category, total) -> {
            System.out.println("  " + category + ": $" + String.format("%.2f", total));
        });

        // Step 3: Display data in a GUI
        displayData(categories);
    }

    // Function to create a sample data file
    private static void createSampleData(String fileName) {
        List<String> data = Arrays.asList(
            "Date,Category,Amount",
            "2024-01-01,Food,45.5",
            "2024-01-02,Transport,15.0",
            "2024-01-03,Entertainment,60.0",
            "2024-01-04,Food,30.0",
            "2024-01-05,Transport,20.0"
        );

        try {
            Files.write(Paths.get(fileName), data);
        } catch (IOException e) {
            System.err.println("Error writing sample data: " + e.getMessage());
        }
    }

    // Function to read and process the data
    private static Map<String, Double> readAndProcessData(String fileName) {
        Map<String, Double> categories = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String category = parts[1];
                double amount = Double.parseDouble(parts[2]);
                categories.put(category, categories.getOrDefault(category, 0.0) + amount);
            }
        } catch (IOException e) {
            System.err.println("Error reading data: " + e.getMessage());
        }

        return categories;
    }

    // Function to display data in a GUI
    private static void displayData(Map<String, Double> categories) {
        StringBuilder displayText = new StringBuilder("Expenditure Breakdown:\n\n");
        categories.forEach((category, total) -> {
            displayText.append(category).append(": $").append(String.format("%.2f", total)).append("\n");
        });

        JOptionPane.showMessageDialog(null, displayText.toString(), "Expenditure Breakdown", JOptionPane.INFORMATION_MESSAGE);
    }
}
