package edu.odu.cs.cs355;

/**
 * @author zeil
 *
 */
public class Token {

    public enum Kinds {Value, Comma, EndOfLine, EndOfInput, Unknown}

    public Kinds kind;
    public String value;

    public Token (Kinds theKind, String theValue) {
        kind = theKind;
        value = theValue;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Token) {
            Token t = (Token)obj;
            return t.kind.equals(kind) && t.value.equals(value);
        } else
            return false;
    }

    public String toString() {
        return "" + kind + ":" + value;
    }
}
