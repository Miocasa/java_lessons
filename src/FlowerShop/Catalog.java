package FlowerShop;

import FlowerShop.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Catalog {

    private final List<Flower> flowers = new ArrayList<>();
    private final List<Accessory> accessories = new ArrayList<>();

    public Catalog() {
        flowers.add(new Rose("Red", 3.00, 95, 60));
        flowers.add(new Rose("White", 2.80, 80, 50));
        flowers.add(new Tulip("Yellow", 1.50, 90, 35));
        flowers.add(new Tulip("Pink", 1.60, 70, 40));
        flowers.add(new Peony("Ultra Violet", 0.80, 85, 25));

        accessories.add(new Accessory("Ribbon", 0.50));
        accessories.add(new Accessory("Wrapping paper", 1.20));
        accessories.add(new Accessory("Basket", 4.00));
    }

    public List<Flower> get_flowers() {
        return flowers;
    }

    public List<Accessory> get_accessories() {
        return accessories;
    }
}