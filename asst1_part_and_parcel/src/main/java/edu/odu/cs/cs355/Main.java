package edu.odu.cs.cs355;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // String inputStr = "abc, \"def\" ghi\n";
        // Reader rdr = new StringReader(inputStr);
        // CSVParser parser = new CSVParser(rdr);

        // System.out.println(parser.next());
        // System.out.println(parser.next());
        // System.out.println(parser.next());
        // System.out.println(parser.next());
        // System.out.println(parser.next());
        // System.out.println(parser.line());
        // System.out.println(parser.line().isEmpty());

        String inputStr = "abc, \"def\" ghi\n";
        Reader rdr = new StringReader(inputStr);
        CSVParser parser = new CSVParser(rdr);

        List<List<String>> v = parser.csvFile();
        System.out.println(v);
        System.out.println(parser.line());
    }
}
