package ProgrammingBook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sentence {
    private List<Token> tokens = new ArrayList<>();

    public void addToken(Token lexeme) {
        tokens.add(lexeme);
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public Set<Word> getUniqueWords() {
        Set<Word> words = new HashSet<>();
        for (Token l : tokens) {
            if (l instanceof Word) {
                words.add((Word) l);
            }
        }
        return words;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Token t : tokens) {
            if (t instanceof Word && !result.isEmpty()) result.append(' ');
            result.append(t);
        }
        if (result.isEmpty()) return result.toString();
        return String.format("%c%s", Character.toUpperCase(result.charAt(0)), result.substring(1));
    }
}