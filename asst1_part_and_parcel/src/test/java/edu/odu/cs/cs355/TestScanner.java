/**
 * 
 */
package edu.odu.cs.cs355;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

import java.io.Reader;
import java.io.StringReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author zeil
 *
 */
public class TestScanner {

    /* Uncomment if you would like to run the Scanner tests 
    @Test
    public void testBasicScan() {
        String inputStr = "abc, 123, def";
        Reader rdr = new StringReader(inputStr);
        CSVScanner scanner = new CSVScanner(rdr);
        Token t = scanner.next();
        assertThat(t, is(new Token(Token.Kinds.Value, "abc")));

        t = scanner.peek();
        assertThat(t.kind, is(Token.Kinds.Comma));
        t = scanner.peek();
        assertThat(t.kind, is(Token.Kinds.Comma));
        t = scanner.next();
        assertThat(t.kind, is(Token.Kinds.Comma));
        
        t = scanner.next();
        assertThat(t, is(new Token(Token.Kinds.Value, "123")));
    }

    @Test
    public void testEndings() {
        String inputStr = "abc, 123\n def\n";
        Reader rdr = new StringReader(inputStr);
        CSVScanner scanner = new CSVScanner(rdr);
        Token t = scanner.next();
        assertThat(t, is(new Token(Token.Kinds.Value, "abc")));
        t = scanner.next();
        assertThat(t.kind, is(Token.Kinds.Comma));
        t = scanner.next();
        assertThat(t, is(new Token(Token.Kinds.Value, "123")));
        t = scanner.next();
        assertThat(t.kind, is(Token.Kinds.EndOfLine));
        t = scanner.next();
        assertThat(t, is(new Token(Token.Kinds.Value, "def")));
        t = scanner.next();
        assertThat(t.kind, is(Token.Kinds.EndOfLine));
        t = scanner.next();
        assertThat(t.kind, is(Token.Kinds.EndOfInput));
        t = scanner.next();
        assertThat(t.kind, is(Token.Kinds.EndOfInput));
        t = scanner.next();
        assertThat(t.kind, is(Token.Kinds.EndOfInput));
    }


    @Test
    public void testQuoting() {
        String inputStr = "abc, \"def\", \"gh,i\", \"j\\\"kl\"";
        Reader rdr = new StringReader(inputStr);
        CSVScanner scanner = new CSVScanner(rdr);
        Token t = scanner.next();
        assertThat(t, is(new Token(Token.Kinds.Value, "abc")));


        t = scanner.next();
        assertThat(t.kind, is(Token.Kinds.Comma));

        // Values can be quoted, but the quotes are not part of the actual value.
        t = scanner.next();
        assertThat(t, is(new Token(Token.Kinds.Value, "def")));

        t = scanner.next();
        assertThat(t.kind, is(Token.Kinds.Comma));

        // Quoted strings can contain commas
        t = scanner.next();
        assertThat(t, is(new Token(Token.Kinds.Value, "gh,i")));

        t = scanner.next();
        assertThat(t.kind, is(Token.Kinds.Comma));

        // Quoted strings can contain an internal " character if preceded by a backslash.
        t = scanner.next();
        assertThat(t, is(new Token(Token.Kinds.Value, "j" + '"' + "kl")));

        t = scanner.next();
        assertThat(t.kind, is(Token.Kinds.EndOfLine));
    }

*/

}
