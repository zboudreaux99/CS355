package edu.odu.cs.cs355;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test input string
        String input = "abc,def,ghi\n\"j\\\"kl\",\"mn,o\"";

        // Create a CSVParser instance with the input string
        CSVParser parser = new CSVParser(new StringReader(input));

        // Call the csvFile() method
        List<List<String>> result = parser.csvFile();

        // Expected output
        List<List<String>> expected = new ArrayList<>();

        List<String> row1 = new ArrayList<>();
        row1.add("abc");
        row1.add("def");
        row1.add("ghi");
        expected.add(row1);

        List<String> row2 = new ArrayList<>();
        row2.add("j\"kl");
        row2.add("mn,o");
        expected.add(row2);

        // Print the results
        System.out.println("Actual Result: " + result);
        System.out.println("Expected Result: " + expected);

        // Verify the results
        if (result.equals(expected)) {
            System.out.println("Test passed!");
        } else {
            System.out.println("Test failed.");
        }
    }
}
