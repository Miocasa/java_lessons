import java.util.Scanner;


public class Main{
    public static void main(String[] args) {
        variant_A();
//        variant_B();
    }
    /**
     * Car: id, Марка, Модель, Год выпуска, Цвет, Цена, Регистрационный номер.
     * Создать массив объектов. Вывести:
     * a) список автомобилей заданной марки;
     * b) список автомобилей заданной модели, которые эксплуатируются больше n лет;
     * c) список автомобилей заданного года выпуска, цена которых больше указанной.
     */
    static void variant_A() {
        Scanner sc = new Scanner(System.in);
        Car[] cars = new Car[] {
                new Car("Toyota", "Camry", 2020, "Black", 2500000L, "A123BC77"),
                new Car("BMW", "X5", 2021, "Blue", 6800000L, "B456EH777"),
                new Car("Audi", "A6", 2019, "White", 3200000L, "C789OP199"),
                new Car("Mercedes-Benz", "E-Class", 2022, "Black", 5500000L, "E012KX77"),
                new Car("Volkswagen", "Golf", 2018, "Red", 1400000L, "H345TH177"),
                new Car("Hyundai", "Solaris", 2021, "Gray", 1200000L, "K678MK799"),
                new Car("Kia", "Rio", 2020, "White", 1100000L, "M901AB777"),
                new Car("Ford", "Focus", 2017, "Blue", 950000L, "O234BO197"),
                new Car("Mazda", "CX-5", 2021, "Red", 2800000L, "P567EE77"),
                new Car("Nissan", "Qashqai", 2019, "Silver", 1750000L, "T890KK199"),
                new Car("Skoda", "Octavia", 2020, "Gray", 1850000L, "Y123MM77"),
                new Car("Volvo", "XC90", 2022, "White", 7200000L, "A456PP777"),
                new Car("Porsche", "Cayenne", 2021, "Black", 9500000L, "B789CC199"),
                new Car("Lexus", "RX", 2020, "Pearl", 4300000L, "E012TO77"),
                new Car("Honda", "Civic", 2018, "Blue", 1300000L, "H345TT177"),
                new Car("Renault", "Duster", 2019, "Brown", 1050000L, "K678YY799"),
                new Car("Subaru", "Forester", 2020, "Green", 2600000L, "M901XX777"),
                new Car("Kia", "Sportage", 2022, "Black", 2900000L, "O234EE197"),
                new Car("Toyota", "RAV4", 2021, "Silver", 3100000L, "P567OO77"),
                new Car("Lada", "Vesta", 2022, "White", 900000L, "T890AM199")
        };
        print_brief();
        while (true) {
            System.out.print("\ncars@jre $ ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting.");
                return;
            }
            String[] args = input.split("\\s+");
            if (args.length > 0 && (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?"))) {
                print_brief();
            } else if (args.length >= 2 && args[0].equalsIgnoreCase("brand")) {
                print_by_brand(cars, args[1]);
            } else if (args.length >= 3 && args[0].equalsIgnoreCase("model")) {
                try {
                    int n = Integer.parseInt(args[2]);
                    print_by_model_years(cars, args[1], n);
                } catch (NumberFormatException e) {
                    System.out.println("Error: n must be number");
                }
            } else if (args.length >= 3 && args[0].equalsIgnoreCase("year")) {
                try {
                    int year = Integer.parseInt(args[1]);
                    long price = Long.parseLong(args[2]);
                    print_by_year_price(cars, year, price);
                } catch (NumberFormatException e) {
                    System.out.println("Error: year and price must be numbers");
                }
            } else {
                System.out.println("Unknown command");
            }
        }
    }

    public static void print_brief() {
        System.out.println(
                "Usage: command [option]\n" +
                        "Commands:\n" +
                        "\tbrand <mark>\n" +
                        "\tmodel <model> <n>\n" +
                        "\tyear <year> <price>\n" +
                        "\thelp | ?\n" +
                        "\texit"
        );
    }

    public static void print_by_brand(Car[] cars, String brand) {
        System.out.println("Cars of brand " + brand + ":");
        for (Car c : cars) {
            if (c.get_mark().equalsIgnoreCase(brand)) {
                System.out.println(c);
            }
        }
    }

    public static void print_by_model_years(Car[] cars, String model, int n) {
        int currentYear = 2026;
        System.out.println("Cars of model " + model + " older than " + n + " years:\n");
        for (Car c : cars) {
            if (c.get_model().equalsIgnoreCase(model) && (currentYear - c.get_release_year()) > n) {
                System.out.println(c);
            }
        }
    }

    public static void print_by_year_price(Car[] cars, int year, long price) {
        System.out.println("Cars of year " + year + " with price > " + price + ":\n");
        for (Car c : cars) {
            if (c.get_release_year() == year && c.get_price() > price) {
                System.out.println(c);
            }
        }
    }
    /**
     * Вариант В
     * Определить класс Комплекс. Создать массив/список/множество размерности n из комплексных координат.
     * Передать его в метод, который выполнит сложение/умножение его элементов.
     */
    static void variant_B() {
        Scanner sc = new Scanner(System.in);
        Complex[] numbers = new Complex[] {
                new Complex(1, 2),
                new Complex(3, -4),
                new Complex(2, 5),
                new Complex(-1, 1)
        };
        System.out.println("Start massive");
        for (int i = 0; i < numbers.length; i++) {
            System.out.printf("z%d = %s\n", i + 1, numbers[i]);
        }
        System.out.println();

        Complex sum = new Complex(0,0);
        Complex prd = new Complex(1,0);

        for (Complex c : numbers){
            sum = sum.plus(c);
            prd = sum.times(c);
        }

        System.out.printf("Summary: %s\n", sum);
        System.out.printf("Product: %s\n", prd);

    }


}

