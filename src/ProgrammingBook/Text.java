package ProgrammingBook;

import java.util.ArrayList;
import java.util.List;

public class Text {
    //    private final List<Object> elements = new ArrayList<>();
    private List<Paragraph> paragraphs = new ArrayList<>();
    private List<CodeListing> listings = new ArrayList<>();

    public void addParagraph(Paragraph p) {
        paragraphs.add(p);
    }

    public void addListing(CodeListing l) {
        listings.add(l);
    }

    public List<Sentence> getAllSentences() {
        List<Sentence> all = new ArrayList<>();
        for (Paragraph p : paragraphs) {
            all.addAll(p.getSentences());
        }
        return all;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Paragraph p : paragraphs) {
            result.append(p);
            result.append("\n\n");
        }
        for (CodeListing l : listings) {
            result.append(l);
            result.append("\n\n");
        }
        return result.toString();
    }
}