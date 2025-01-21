/**
 * 
 */
package edu.odu.cs.cs355;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author zeil
 *
 */
public class TestParser {

    @Test
    public void testField() {
        String inputStr = "abc d,";
        Reader rdr = new StringReader(inputStr);
        CSVParser parser = new CSVParser(rdr);

        String v = parser.field();
        System.out.println(v);
        assertThat(v, is("abc d"));
    }

    @Test
    public void testEmptyField() {
        String inputStr = ",";
        Reader rdr = new StringReader(inputStr);
        CSVParser parser = new CSVParser(rdr);

        String v = parser.field();
        assertThat(v, is(""));
    }

    @Test
    public void testNonEmpty() {
        String inputStr = "abc, def, ghi\n";
        Reader rdr = new StringReader(inputStr);
        CSVParser parser = new CSVParser(rdr);

        List<String> v = parser.nonEmpty();
        String[] expected = { "abc", "def", "ghi" };

        assertThat(v, is(Arrays.asList(expected)));
    }

    @Test
    public void testNonEmpty2() {
        String[] expected2 = { "abc", "def", "", "ghi" };
        String inputStr = "abc, def,, ghi\n";
        Reader rdr = new StringReader(inputStr);
        CSVParser parser = new CSVParser(rdr);
        List<String> v = parser.nonEmpty();
        assertThat(v, is(Arrays.asList(expected2)));
    }

    @Test
    public void testNonEmpty3() {
        String[] expected3 = { "abc", "def", "ghi", "" };
        String inputStr = "abc, def, ghi,\n";
        Reader rdr = new StringReader(inputStr);
        CSVParser parser = new CSVParser(rdr);
        List<String> v = parser.nonEmpty();
        assertThat(v, is(Arrays.asList(expected3)));
    }

    @Test
    public void testNonEmpty4() {
        String[] expected = { "abc", "def", "ghi" };
        String inputStr = "abc, \"def\", ghi";
        Reader rdr = new StringReader(inputStr);
        CSVParser parser = new CSVParser(rdr);
        List<String> v = parser.nonEmpty();
        assertThat(v, is(Arrays.asList(expected)));
    }

    @Test
    public void testLine() {
        String inputStr = "abc, def, ghi\n";
        Reader rdr = new StringReader(inputStr);
        CSVParser parser = new CSVParser(rdr);

        List<String> v = parser.line();
        String[] expected = { "abc", "def", "ghi" };

        assertThat(v, is(Arrays.asList(expected)));
    }

    @Test
    public void testLine2() {
        String inputStr = "\n";
        Reader rdr = new StringReader(inputStr);
        CSVParser parser = new CSVParser(rdr);

        List<String> v = parser.line();
        assertThat(v.size(), is(0));
    }

    @Test
    public void testLine3() {
        String[] expected2 = { "abc", "def", "", "ghi" };
        String inputStr = "abc, def,, ghi\n";
        Reader rdr = new StringReader(inputStr);
        CSVParser parser = new CSVParser(rdr);
        List<String> v = parser.line();
        assertThat(v, is(Arrays.asList(expected2)));
    }

    @Test
    public void testLine4() {
        String[] expected3 = { "abc", "def", "ghi", "" };
        String inputStr = "abc, def, ghi,\n";
        Reader rdr = new StringReader(inputStr);
        CSVParser parser = new CSVParser(rdr);
        List<String> v = parser.line();
        assertThat(v, is(Arrays.asList(expected3)));
    }

    @Test
    public void testFile() {
        String inputStr = "abc, def, ghi\n\"j\\\"kl\",\"mn,o\"";
        Reader rdr = new StringReader(inputStr);
        CSVParser parser = new CSVParser(rdr);

        List<List<String>> v = parser.csvFile();
        String[] expected1 = { "abc", "def", "ghi" };
        String[] expected2 = { "j" + '"' + "kl", "mn,o" };

        ArrayList<List<String>> expected = new ArrayList<>();
        expected.add(Arrays.asList(expected1));
        expected.add(Arrays.asList(expected2));

        assertThat(v, is(expected));
    }

    @Test
    public void testFile2() {
        String inputStr = "abc, \"def\" ghi\n";
        Reader rdr = new StringReader(inputStr);
        CSVParser parser = new CSVParser(rdr);

        List<List<String>> v = parser.csvFile();
        assertNull(v);
    }

}
