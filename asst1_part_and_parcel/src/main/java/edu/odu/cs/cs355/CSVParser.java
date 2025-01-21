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
        if (token == null || token.equals(Token.Kinds.Comma) || token.equals(Token.Kinds.EndOfLine)) {
            return null;
        }

        return scanner.next().toString();
    }

    /**
     * Attempt to parse a <Line>, returning a list of value strings.
     * 
     * @return a list of value strings if successful or null if the input cannot be
     *         parsed as a <Line>
     */
    public List<String> line() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'line'");

        // NOT COMPLETE
        List<String> values = new ArrayList<>();

        String fieldValue = field();
        if (fieldValue == null && !"\n".equals(scanner.peek().toString())) {
            return null; // Invalid line
        }

        if (fieldValue != null) {
            values.add(fieldValue);
        }

        List<String> nonEmptyValues = nonEmpty();
        if (nonEmptyValues != null) {
            values.addAll(nonEmptyValues);
        }

        return values;

        // List<String> values = new ArrayList<>();
        // Token token = scanner.peek();
        
        // // Handle empty line case
        // if (token != null && token.equals(Token.Kinds.EndOfLine)) {
        //     return values;
        // }

        // // Parse first field
        // String fieldValue = field();
        // if (fieldValue == null) {
        //     return null;
        // }
        // values.add(fieldValue);

        // // Parse remaining fields
        // List<String> remaining = nonEmpty();
        // if (remaining != null) {
        //     values.addAll(remaining);
        // }

        // return values;
    }

    /**
     * Attempt to parse a <NonEmpty>, returning a list of value strings.
     * 
     * @return a list of value strings if successful or null if the input cannot be
     *         parsed as a <NonEmpty>
     */
    public List<String> nonEmpty() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'nonEmpty'");

        // NOT COMPLETE
        if (scanner.peek() == null || !scanner.peek().toString().equals(",")) {
            return null; // Empty <NonEmpty>
        }

        // Consume the comma
        scanner.next();

        List<String> values = line();
        if (values == null) {
            return null; // Invalid line after comma
        }

        return values;

        // Token token = scanner.peek();
        
        // // Empty case
        // if (token == null || !token.equals(Token.Kinds.Comma)) {
        //     return new ArrayList<>();
        // }

        // // Consume comma
        // scanner.next();

        // // Parse the rest of the line
        // List<String> lineValues = line();
        // if (lineValues == null) {
        //     return null;
        // }

        // return lineValues;
    }

    /**
     * Attempt to parse a <CSVFile>, returning a list of (rows) of lists of
     * (columns) value strings.
     * 
     * @return a list of lists of value strings if successful or null if the input
     *         cannot be parsed as a <CSVFile>
     */
    public List<List<String>> csvFile() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'csvFile'");

        // NOT COMPLETE
        List<List<String>> rows = new ArrayList<>();
        while (scanner.peek() != null) {
            if ("\n".equals(scanner.peek().toString())) {
                scanner.next(); // Consume end of line
                continue;
            }

            List<String> row = line();
            if (row == null) {
                return null; // Invalid CSV format
            }

            rows.add(row);

            if ("\n".equals(scanner.peek().toString())) {
                scanner.next(); // Consume end of line
            }
        }

        return rows;

        // List<List<String>> rows = new ArrayList<>();
        // Token token;

        // while ((token = scanner.peek()) != null) {
        //     if (token.equals(Token.Kinds.EndOfInput)) {
        //         break;
        //     }

        //     // Parse line
        //     List<String> row = line();
        //     if (row == null) {
        //         return null;
        //     }
        //     rows.add(row);

        //     // Expect end of line or end of input
        //     token = scanner.peek();
        //     if (token == null || 
        //         (!token.equals(Token.Kinds.EndOfLine) && 
        //          !token.equals(Token.Kinds.EndOfInput))) {
        //         return null;
        //     }

        //     // Consume end of line if present
        //     if (token.equals(Token.Kinds.EndOfLine)) {
        //         scanner.next();
        //     }
        // }

        // return rows;
    }

}
