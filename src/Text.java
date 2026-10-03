import java.util.*;

class Text {
    private String title;
    private List<Sentence> sentences = new ArrayList<>();

    public Text(String title) {
        this.title = title;
    }

    public void add_sentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public void print_text() {
        System.out.println(title);
        for (Sentence sentence : sentences) {
            System.out.println(sentence);
        }
    }

    public void print_title() {
        System.out.println("Header: " + title);
    }
}