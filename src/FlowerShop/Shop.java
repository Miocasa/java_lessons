package FlowerShop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Shop {
    private final List<Flower> flowers = new ArrayList<>();
    private final List<Accessory> accessories = new ArrayList<>();

    public void add_flower(Flower flower) {
        flowers.add(flower);
    }
    public void add_accessory(Accessory accessory) {
            accessories.add(accessory);
    }
    public double get_cost() {
        double cost = 0;
        for (Flower flower : flowers) {
            cost += flower.get_price();
        }
        for (Accessory accessory : accessories) {
            cost += accessory.get_price();
        }
        return cost;
    }

    public void sort_by_freshness() {
        flowers.sort(Comparator.comparingInt(Flower::get_freshness).reversed());
    }

    public List<Flower> find_by_stem_length(int min, int max) {
        return flowers.stream()
                .filter(flower -> flower.get_stem_length() >= min
                        && flower.get_stem_length() <= max)
                .collect(Collectors.toList());
    }

    public List<Flower> get_flowers() {
        return flowers;
    }

    public List<Accessory> get_accessory() {
        return accessories;
    }

    public boolean is_empty() {
        return flowers.isEmpty() && accessories.isEmpty();
    }
}
