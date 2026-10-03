package FlowerShop;



public class Accessory {

    private final String _name;
    private final double _price;

    protected Accessory(String name, double price) {
        this._name = name;
        this._price = price;
    }

    public double get_price() {
        return _price;
    }

    public String get_name() {
        return _name;
    }

    @Override
    public String toString() {
        return String.format("%s | price %.2f", _name, get_price());
    }
}

