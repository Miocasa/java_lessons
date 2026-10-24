package Publisher;

public class Magazine extends PublisherBase implements JournalFeatures {
    private long edition = 0;

    public Magazine(String title) {
        super(title);
    }

    @Override
    public void setMagazineEdition(long number) {
        if (number <= 0)
            throw new IllegalArgumentException("Edition number must be positive: " + number);
        edition = number;
    }

    @Override
    public void print() {
        if (edition <= 0)
            throw new IllegalStateException("Set magazine edition before printing");
        super.print();
    }
}