package ProgrammingBook;

import java.util.ArrayList;
import java.util.List;

public class Paragraph {
    private List<Sentence> sentences = new ArrayList<>();

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Sentence s : sentences) {
            result.append(s);
            result.append(' ');
        }
        return result.toString().trim();
    }
}