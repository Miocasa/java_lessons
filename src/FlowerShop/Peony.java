package FlowerShop;

public class Peony extends Flower{
    public Peony(String color, double basePrice, int freshness, int stemLength) {
        super(color + " peony", basePrice, freshness, stemLength);
    }

    @Override
    public double get_price() {
        return get_base_price() * get_freshness() / 100.0;
    }
}
