import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Notepad {
    private final List<Page> _pages = new ArrayList<>();

    Notepad(){
    }

    public void add_note(LocalDate date, String text) {
        Page page = find_page(date);
        if (page == null) {
            page = new Page(date);
            _pages.add(page);
        }
        page.add_note(text);
    }

    private Page find_page(LocalDate date) {
        for (Page page : _pages) {
            if (page.get_date().equals(date)) {
                return page;
            }
        }
        return null;
    }

    public void print_all() {
        if (_pages.isEmpty()) {
            System.out.println("Notebook empty");
            return;
        }
        _pages.sort(Comparator.comparing(Page::get_date));
        for (Page page : _pages) {
            System.out.println(page);
        }
    }

    public void print_by_date(LocalDate date) {
        Page page = find_page(date);
        if (page == null) {
            System.out.println("This date no notes");
        } else {
            System.out.println(page);
        }
    }

    public List<Page> get_pages() {
        return Collections.unmodifiableList(_pages);
    }

    static class Page {
        private final List<Note> _notes = new ArrayList<>();
        private final LocalDate _date;
        Page(LocalDate date) {
            _date = date;
        }

        void add_note(Note note){
            _notes.add(note);
        }
        void add_note(String note){
            _notes.add(new Note(note));
        }
        LocalDate get_date() {
            return _date;
        }

        public List<Note> get_notes() {
            return _notes;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%04d.%02d.%02d\n", _date.getYear(), _date.getMonth().getValue(), _date.getDayOfMonth()));

            for (int i = 0; i < _notes.size(); i++) {
                sb.append(String.format("  %d. %s\n", i + 1, _notes.get(i)));
            }
            return sb.toString();
        }
    }
    static class Note {
        private final String str;
        private final LocalDate created = LocalDate.now();

        public Note(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }
}
