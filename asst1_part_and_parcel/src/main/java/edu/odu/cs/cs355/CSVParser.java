package edu.odu.cs.cs355;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    /**
     * Parses inputs for the following grammar:
     * 
     * <CSVFile> ::= <Line> endOfLine <CSVFile> | endOfInput
     * <Line> ::= <Field> <NonEmpty> | endOfLine
     * <NonEmpty> ::= , <Line> | [empty]
     * <Field> ::= value
     */

    private CSVScanner scanner;

    /**
     * Create a new scanner for CSV content.
     * 
     * @param rdr
     */
    public CSVParser(Reader rdr) {
        scanner = new CSVScanner(rdr);
    }

    /**
     * Attempt to parse a <Field>, returning the field value.
     * 
     * @return a field value or null if a <Field> could not be parsed.
     */
    public String field() {
        Token token = scanner.peek();

        if (token == null || token.kind.equals(Token.Kinds.Comma) || token.kind.equals(Token.Kinds.EndOfLine)
                || token.kind.equals(Token.Kinds.EndOfInput)) {
            scanner.next();
            return "";
        }

        String output = scanner.next().toString();
        String[] parts = output.split(":");
        return parts[1];
    }

    /**
     * Attempt to parse a <Line>, returning a list of value strings.
     * 
     * @return a list of value strings if successful or null if the input cannot be
     *         parsed as a <Line>
    */
    public List<String> line() {
        List<String> lineValues = new ArrayList<>();
        String firstField = field(); // Parse the first field
    
        // If the first field is empty and we're at the end of the line or input, return an empty list
        if (firstField.isEmpty()) {
            Token token = scanner.peek();
            if (token != null && (token.kind.equals(Token.Kinds.EndOfLine) || token.kind.equals(Token.Kinds.EndOfInput))) {
                return lineValues;
            }
        }
    
        // Add the first field to the list (even if it's empty)
        lineValues.add(firstField);
    
        // Parse the rest of the fields
        while (true) {
            Token token = scanner.peek();
            if (token == null || token.kind.equals(Token.Kinds.EndOfLine) || token.kind.equals(Token.Kinds.EndOfInput)) {
                break;
            }
            
            if (token.kind.equals(Token.Kinds.Comma)) {
                scanner.next(); // Consume the comma
                
                // Check for consecutive commas
                if (scanner.peek() != null && scanner.peek().kind.equals(Token.Kinds.Comma)) {
                    lineValues.add(""); // Add an empty field for consecutive commas
                } else {
                    lineValues.add(field()); // Add the next field (even if it's empty)
                }
            } else {
                break;
            }
        }
    
        // Consume the EndOfLine token if present
        if (scanner.peek() != null && scanner.peek().kind.equals(Token.Kinds.EndOfLine)) {
            scanner.next();
        }
    
        return lineValues;
    }

    /**
     * Attempt to parse a <NonEmpty>, returning a list of value strings.
     * 
     * @return a list of value strings if successful or null if the input cannot be
     *         parsed as a <NonEmpty>
     */
    public List<String> nonEmpty() {
        List<String> nonEmptyValues = new ArrayList<>();
        String currentField = "";
        boolean inField = true;

        while (inField) {
            Token token = scanner.peek();

            switch (token.kind) {
                case Comma:
                    nonEmptyValues.add(currentField);
                    currentField = "";
                    scanner.next(); // Consume the comma
                    break;
                case EndOfLine:
                    if (currentField.length() > 0 || !nonEmptyValues.isEmpty()) {
                        nonEmptyValues.add(currentField);
                    }
                    inField = false;
                    scanner.next(); // Consume the EOL
                    break;
                case EndOfInput:
                    if (currentField.length() > 0 || !nonEmptyValues.isEmpty()) {
                        nonEmptyValues.add(currentField);
                    }
                    inField = false;
                    break;
                default:
                    currentField = field();
            }
        }

        return nonEmptyValues;
    }

    /**
     * Attempt to parse a <CSVFile>, returning a list of (rows) of lists of
     * (columns) value strings.
     * 
     * @return a list of lists of value strings if successful or null if the input
     *         cannot be parsed as a <CSVFile>
     */   
    public List<List<String>> csvFile() {
        List<List<String>> csvContent = new ArrayList<>();

        while (true) {
            // Parse a line
            List<String> line = line();

            // If line is empty, the CSV file is complete
            if (line.isEmpty()) {
                break;
            }

            // Add the parsed line to the CSV content
            csvContent.add(line);

            // Check for end of input (if the next token is EndOfInput)
            Token token = scanner.peek();
            if (token != null && token.kind.equals(Token.Kinds.EndOfInput)) {
                break;
            }
        }

        return csvContent; // Return the parsed CSV content
    }
}