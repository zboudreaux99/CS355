package edu.odu.cs.cs355;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // String inputStr = "abc, def, ghi\n";
        String inputStr = "abc, def,, ghi\n";
        Reader rdr = new StringReader(inputStr);
        CSVParser parser = new CSVParser(rdr);

        System.out.println(parser.field());
        System.out.println(parser.field());
        System.out.println(parser.field());
        System.out.println(parser.field());
        System.out.println(parser.field());
        System.out.println(parser.field());
        System.out.println(parser.field());
        System.out.println(parser.field());
        System.out.println(parser.field());
        System.out.println(parser.field());
        // List<String> v = parser.nonEmpty();
        // System.out.println(v);
    }
}
