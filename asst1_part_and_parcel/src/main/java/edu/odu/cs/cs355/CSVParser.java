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
    private static final String COMMA = ",";
    private static final String NEWLINE = "\n";

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

        if (token == null || token.kind.equals(Token.Kinds.Comma) || token.kind.equals(Token.Kinds.EndOfLine)) {
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
        Token peek = scanner.peek();
        
        if (peek == null || isEndOfLine(peek)) {
            return values;
        }

        String fieldValue = field();
        values.add(fieldValue);

        List<String> remaining = nonEmpty();
        if (remaining != null) {
            values.addAll(remaining);
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
        Token peek = scanner.peek();
        if (peek == null || !COMMA.equals(peek.toString())) {
            return null;
        }

        scanner.next(); // Consume comma
        return line();
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
        Token peek;

        while ((peek = scanner.peek()) != null) {
            if (isEndOfLine(peek)) {
                scanner.next(); // Consume newline
                continue;
            }

            List<String> row = line();
            if (!row.isEmpty()) {
                rows.add(row);
            }

            consumeNewlineIfPresent();
        }

        return rows;
    }

    private boolean isEndOfLine(Token token) {
        return NEWLINE.equals(token.toString());
    }

    private void consumeNewlineIfPresent() {
        Token peek = scanner.peek();
        if (peek != null && isEndOfLine(peek)) {
            scanner.next();
        }
    }
}
