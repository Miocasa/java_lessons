package ProgrammingBook;

public class Token {
    protected String value;

    public Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}