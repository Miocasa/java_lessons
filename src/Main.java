import FlowerShop.Accessory;
import FlowerShop.Catalog;
import FlowerShop.Flower;
import FlowerShop.Shop;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.*;


public class Main{
    public static void main(String[] args) {
//        variant_A();
        variant_B();
    }
    /*
     * Создать приложение, удовлетворяющее требованиям, приведенным в задании. Наследование применять только в тех заданиях, в которых это логически
     * обосновано. Аргументировать принадлежность классу каждого создаваемого
     * метода и корректно переопределить для каждого класса методы equals(),
     * hashCode(), toString().
     */
    /**
     * Вариант А
     * 1. Создать объект класса Текст, используя классы Предложение, Слово.
     * Методы: дополнить текст, вывести на консоль текст, заголовок текста
     */
    static void variant_A() {
        Text t = new Text("Hello world !");

        t.add_sentence(new Sentence("First sentence."));
        t.add_sentence(new Sentence("Second sentence."));
        t.add_sentence(new Sentence("Third sentence."));

        t.print_title();
        t.print_text();
    }

    /*
    Создать консольное приложение, удовлетворяющее следующим требованиям:
     * • Использовать возможности ООП: классы, наследование, полиморфизм, инкапсуляция.
     * • Каждый класс должен иметь отражающее смысл название и информативный состав.
     * • Наследование должно применяться только тогда, когда это имеет смысл.
     * • При кодировании должны быть использованы соглашения об оформлении
     * кода java code convention.
     * • Классы должны быть грамотно разложены по пакетам.
     * • Консольное меню должно быть минимальным.
     * • Для хранения параметров инициализации можно использовать файлы.
     */
    /**
     * Вариант В
     * 1. Цветочница. Определить иерархию цветов. Создать несколько объектов цветов. Собрать букет (используя аксессуары)
     * с определением его стоимости. Провести сортировку цветов в букете на основе уровня свежести. Найти
     * цветок в букете, соответствующий заданному диапазону длин стеблей.
     */
    static void variant_B() {
        Scanner sc = new Scanner(System.in);
        Catalog catalog = new Catalog();
        Shop shop = new Shop();

        print_flower_brief();
        while (true) {
            System.out.print("\nflowers@jre $ ");
            if (!sc.hasNextLine()) {
                return;
            }
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting.");
                return;
            }

            String[] args = input.split("\\s+");

            if (args[0].equalsIgnoreCase("help") || args[0].equals("?")) {
                print_flower_brief();
            } else if (args[0].equalsIgnoreCase("catalog")) {
                print_catalog(catalog, args);
            } else if (args[0].equalsIgnoreCase("add")) {
                add_to_shop(catalog, shop, args);
            } else if (args[0].equalsIgnoreCase("show")) {
                print_shop(shop);
            } else if (args[0].equalsIgnoreCase("sort")) {
                sort_shop(shop, args);
            } else if (args[0].equalsIgnoreCase("find")) {
                find_by_stem(shop, args);
            } else if (!args[0].isEmpty()) {
                System.out.println("Unknown command");
            }
        }
    }
    public static void print_flower_brief() {
        System.out.println(
                "Usage: command [option]\n" +
                        "Commands:\n" +
                        "\tcatalog [-f | --flowers] [-a | --accessories]\n" +
                        "\t\t # print catalog, default: flowers and accessories\n" +
                        "\tadd <-f | --flower | -a | --accessory> <number>\n" +
                        "\t\t # add item from catalog to shop\n" +
                        "\tshow # print shop and its cost\n" +
                        "\tsort [-f | --freshness]\n" +
                        "\t\t # sort flowers in shop, freshest first\n" +
                        "\tfind <-s | --stem> <min max>\n" +
                        "\t\t # find flowers by stem length, cm\n" +
                        "\thelp | ? # print this\n" +
                        "\texit # end program"
        );
    }
    public static void print_catalog(Catalog catalog, String[] args) {
        String option = args.length >= 2 ? args[1] : "";
        boolean show_flowers = option.isEmpty() || option.equals("-f") || option.equals("--flowers");
        boolean show_accessories = option.isEmpty() || option.equals("-a") || option.equals("--accessories");

        if (!show_flowers && !show_accessories) {
            System.out.println("Unknown catalog option");
            return;
        }
        if (show_flowers) {
            System.out.println("Flowers:");
            List<Flower> flowers = catalog.get_flowers();
            for (int i = 0; i < flowers.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + flowers.get(i));
            }
        }
        if (show_accessories) {
            System.out.println("Accessories:");
            List<Accessory> accessories = catalog.get_accessories();
            for (int i = 0; i < accessories.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + accessories.get(i));
            }
        }
    }
    public static void add_to_shop(Catalog catalog, Shop shop, String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: add <-f | --flower | -a | --accessory> <number>");
            return;
        }
        int number;
        try {
            number = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Error: number must be integer");
            return;
        }

        if (args[1].equals("-f") || args[1].equals("--flower")) {
            List<Flower> flowers = catalog.get_flowers();
            if (number < 1 || number > flowers.size()) {
                System.out.println("No such flower");
                return;
            }
            shop.add_flower(flowers.get(number - 1));
            System.out.println("Added: " + flowers.get(number - 1));
        } else if (args[1].equals("-a") || args[1].equals("--accessory")) {
            List<Accessory> accessories = catalog.get_accessories();
            if (number < 1 || number > accessories.size()) {
                System.out.println("No such accessory");
                return;
            }
            shop.add_accessory(accessories.get(number - 1));
            System.out.println("Added: " + accessories.get(number - 1));
        } else {
            System.out.println("Unknown add option");
        }
    }
    public static void print_shop(Shop shop) {
        if (shop.is_empty()) {
            System.out.println("Shop is empty");
            return;
        }
        System.out.println("Flowers:");
        for (Flower flower : shop.get_flowers()) {
            System.out.println("  " + flower);
        }
        System.out.println("Accessories:");
        for (Accessory accessory : shop.get_accessory()) {
            System.out.println("  " + accessory);
        }
        System.out.printf("Total cost: %.2f%n", shop.get_cost());
    }
    public static void sort_shop(Shop shop, String[] args) {
        if (args.length >= 2 && !(args[1].equals("-f") || args[1].equals("--freshness"))) {
            System.out.println("Unknown sort type");
            return;
        }
        shop.sort_by_freshness();
        System.out.println("Shop sorted by freshness");
    }
    public static void find_by_stem(Shop shop, String[] args) {
        if (args.length < 4 || !(args[1].equals("-s") || args[1].equals("--stem"))) {
            System.out.println("Usage: find <-s | --stem> <min max>");
            return;
        }
        try {
            int min = Integer.parseInt(args[2]);
            int max = Integer.parseInt(args[3]);
            List<Flower> found = shop.find_by_stem_length(min, max);
            if (found.isEmpty()) {
                System.out.println("No flowers found");
                return;
            }
            System.out.println("Flowers with stem " + min + "-" + max + " cm:");
            for (Flower flower : found) {
                System.out.println("  " + flower);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: min and max must be number");
        }
    }


}

