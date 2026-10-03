import java.util.*;

class Sentence {
    private List<Word> words = new ArrayList<>();

    public Sentence(String text) {
        String[] parts = text.split("\\s+");

        for (String part : parts) {
            words.add(new Word(part));
        }
    }

    public void add_word(Word word) {
        words.add(word);
    }

    @Override
    public String toString() {
        return String.join(" ", words.stream()
                .map(Word::toString)
                .toList());
    }
}
