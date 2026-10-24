package Publisher;

import Publisher.BookFeatures;
import Publisher.PublisherBase;

public class Book extends PublisherBase implements BookFeatures {
    private String bookId = null;
    private long copies = 0;

    public Book(String title) {
        super(title);
    }

    @Override
    public void assignBookId(String id) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("Book id can't be blank");
        bookId = id;
    }

    @Override
    public void setCopiesCount(long n) {
        if (n <= 0)
            throw new IllegalArgumentException("Copies count must be positive: " + n);
        copies = n;
    }

    @Override
    public void print() {
        if (bookId == null)
            throw new IllegalStateException("Assign book id before printing");
        if (copies <= 0)
            throw new IllegalStateException("Set copies count before printing");
        super.print();
    }
}