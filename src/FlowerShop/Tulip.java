package FlowerShop;

public class Tulip  extends Flower{
    public Tulip(String color, double basePrice, int freshness, int stemLength) {
        super(color + " tulip", basePrice, freshness, stemLength);
    }

    @Override
    public double get_price() {
        return get_base_price() * get_freshness() / 100.0;
    }
}
