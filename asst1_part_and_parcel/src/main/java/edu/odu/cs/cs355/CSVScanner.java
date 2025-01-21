package edu.odu.cs.cs355;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class CSVScanner {

    private String currentLine;
    private int currentPos;
    private Token nextToken;

    private BufferedReader in;

    /**
     * Create a new scanner for CSV content.
     * 
     * @param rdr
     */
    public CSVScanner(Reader rdr) {
        in = new BufferedReader(rdr);
        currentLine = null;
        currentPos = 0;
        nextToken = null;
    }

    /**
     * Reveal the next token without advancing the input.
     * 
     * @return the next token
     */
    public Token peek() {
        if (nextToken != null)
            return nextToken;
        else if (in == null)
            nextToken = new Token(Token.Kinds.EndOfInput, "");
        else {
            if (currentLine == null) {
                try {
                    currentLine = in.readLine();
                    if (currentLine == null) {
                        in.close();
                        in = null;
                        nextToken = new Token(Token.Kinds.EndOfInput, "");
                        return nextToken;
                    }
                    currentPos = 0;
                } catch (IOException ex) {
                    nextToken = null;
                    return null;
                }
            }
            while (currentPos < currentLine.length()
                    && Character.isWhitespace(currentLine.charAt(currentPos)))
                ++currentPos;
            if (currentPos >= currentLine.length()) {
                currentLine = null;
                nextToken = new Token(Token.Kinds.EndOfLine, "");
            } else {
                char c = currentLine.charAt(currentPos);
                if (c == ',') {
                    nextToken = new Token(Token.Kinds.Comma, "");
                    ++currentPos;
                } else if (c == '"') {
                    boolean lastWasBackslash = false;
                    StringBuffer value = new StringBuffer();
                    ++currentPos;
                    while (currentPos < currentLine.length()) {
                        char c0 = currentLine.charAt(currentPos);
                        ++currentPos;
                        if (lastWasBackslash) {
                            value.append(c0);
                            lastWasBackslash = false;
                        } else if (c0 == '\\') {
                            lastWasBackslash = true;
                        } else if (c0 == '"') {
                            nextToken = new Token(Token.Kinds.Value, value.toString());
                            break;
                        } else {
                            value.append(c0);
                        }
                    }
                    if (currentPos >= currentLine.length()) {
                        nextToken = new Token(Token.Kinds.Value, value.toString());
                    }
                } else {
                    StringBuffer value = new StringBuffer();
                    while (currentPos < currentLine.length()) {
                        char c0 = currentLine.charAt(currentPos);
                        ++currentPos;
                        if (c0 == ',') {
                            nextToken = new Token(Token.Kinds.Value, value.toString());
                            --currentPos;
                            break;
                        } else {
                            value.append(c0);
                        }
                    }
                    if (currentPos >= currentLine.length()) {
                        nextToken = new Token(Token.Kinds.Value, value.toString());
                    }
                }
            }
        }
        return nextToken;
    }

    /**
     * Reveal the next token and advance the input.
     * 
     * @return the next token
     */
    public Token next() {
        Token t = peek();
        nextToken = null;
        return t;
    }

}
