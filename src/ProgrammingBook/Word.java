package ProgrammingBook;

import java.util.ArrayList;
import java.util.List;

public class Word extends Token {

    public Word(String value) {
        super(value.toLowerCase());
    }

    public List<Symbol> getSymbols() {
        List<Symbol> symbols = new ArrayList<>();
        for (char c : value.toCharArray()) {
            symbols.add(new Symbol(c));
        }
        return symbols;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Word) {
            return value.equals(((Word) o).value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}