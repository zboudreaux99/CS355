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

        if (token == null) {
            return "";
        } else if (token.kind.equals(Token.Kinds.Comma)) {
            return "";
        } else if (token.kind.equals(Token.Kinds.EndOfLine)) {
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
        List<String> values = new ArrayList<>();

        String fieldValue = field();
        if (fieldValue == null && !"\n".equals(scanner.peek().toString())) {
            return null;
        }

        if (fieldValue != null) {
            values.add(fieldValue);
        }

        List<String> nonEmptyValues = nonEmpty();
        if (nonEmptyValues != null) {
            values.addAll(nonEmptyValues);
        }

        return values;
    }

    /**
     * Attempt to parse a <NonEmpty>, returning a list of value strings.
     * 
     * @return a list of value strings if successful or null if the input cannot be
     *         parsed as a <NonEmpty>
     */
    public List<String> nonEmpty() {
        if (scanner.peek() == null || !scanner.peek().toString().equals(",")) {
            return null;
        }

        scanner.next();

        List<String> values = line();
        if (values == null) {
            return null;
        }

        return values;
    }

    /**
     * Attempt to parse a <CSVFile>, returning a list of (rows) of lists of
     * (columns) value strings.
     * 
     * @return a list of lists of value strings if successful or null if the input
     *         cannot be parsed as a <CSVFile>
     */
    public List<List<String>> csvFile() {
        List<List<String>> rows = new ArrayList<>();
        while (scanner.peek() != null) {
            if ("\n".equals(scanner.peek().toString())) {
                scanner.next(); // Consume end of line
                continue;
            }

            List<String> row = line();
            if (row == null) {
                return null;
            }

            rows.add(row);

            if ("\n".equals(scanner.peek().toString())) {
                scanner.next();
            }
        }

        return rows;
    }

}
