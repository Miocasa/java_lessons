package FlowerShop;

public class Rose extends Flower{

    public Rose(String color, double basePrice, int freshness, int stemLength) {
        super(color + " rose", basePrice, freshness, stemLength);
    }

    @Override
    public double get_price() {
        return get_base_price() * (1 + get_stem_length() / 100.0);
    }

}
