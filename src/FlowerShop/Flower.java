package FlowerShop;

public class Flower {

    private final String name;
    private final double basePrice;
    private final int freshness;
    private final int stemLength;

    protected Flower(String name, double basePrice, int freshness, int stemLength) {
        this.name = name;
        this.basePrice = basePrice;
        this.freshness = freshness;
        this.stemLength = stemLength;
    }

    public double get_price() {
        return basePrice;
    }

    public String get_name() {
        return name;
    }

    protected double get_base_price() {
        return basePrice;
    }

    public int get_freshness() {
        return freshness;
    }

    public int get_stem_length() {
        return stemLength;
    }

    @Override
    public String toString() {
        return String.format("%s | price %.2f | freshness %d%% | stem %d cm",
                name, get_price(), freshness, stemLength);
    }
}
