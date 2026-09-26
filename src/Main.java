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
    /**
     * Вариант А
     * Customer: id, Фамилия, Имя, Отчество, Адрес, Номер кредитной карточки,
     * Номер банковского счета.
     * Создать массив объектов. Вывести:
     * a) список покупателей в алфавитном порядке;
     * b) список покупателей, у которых номер кредитной карточки находится
     * в заданном интервале.
     */
    static void variant_A() {
        Scanner sc = new Scanner(System.in);
        Customer[] cus = new Customer[]{
                new Customer(123, "Fraud", "Lane", "Backerson",
                        "sity Astana, Turan 12 avenue", 1242343123, 312322351),
                new Customer(531, "Maksatov", "Erjan", "kuseinov",
                        "sity Astana, Abylaikhan 32 avenue", 1543348923, 682324558),
                new Customer(104, "Aliyev", "Askar", "Berikovich",
                        "city Astana, Mangilik El 18 avenue", 1823940123L, 412390812L),
                new Customer(892, "Smagulova", "Dinara", "Maratovna",
                        "city Almaty, Dostyk 45 avenue", 2394810293L, 891230491L),
                new Customer(341, "Kim", "Oleg", "Sergeyevich",
                        "city Astana, Kabanbay Batyr 11 avenue", 1192830491L, 301928401L),
                new Customer(615, "Ospanov", "Daniyar", "Aydosovich",
                        "city Shymkent, Tauke Khan 82 avenue", 4920193840L, 712093841L),
                new Customer(278, "Serikova", "Assel", "Nurlanovna",
                        "city Astana, Respublika 5 avenue", 1029384756L, 593820192L),
                new Customer(903, "Nurpeisov", "Timur", "Armanovich",
                        "city Almaty, Abay 150 avenue", 3847201938L, 902810394L),
                new Customer(452, "Akhmetova", "Madina", "Ruslanovna",
                        "city Karaganda, Bukhar Zhyrau 24 avenue", 1928374650L, 201928374L),
                new Customer(731, "Suleimenov", "Bekzat", "Yerzhanovich",
                        "city Astana, Sarayshyk 7 avenue", 5839201928L, 683920192L),
                new Customer(119, "Zhumabayeva", "Aruzhan", "Kayratovna",
                        "city Almaty, Seyfullin 510 avenue", 2938471029L, 102938471L),
                new Customer(844, "Bolatov", "Sanzhar", "Dauletovich",
                        "city Astana, Uly Dala 38 avenue", 4019283746L, 829103948L)
        };

        print_brief();
        while (true) {
            System.out.print("\ncustomers@jre $ ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")){
                System.out.println("Exiting.");
                return;
            }

            String[] args = input.split("\\s+");

            if(args.length > 0 && (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?"))) {
                print_brief();
            }
            else if(args.length > 0 && args[0].equalsIgnoreCase("sort"))
            {
                if(args.length >= 2 && (args[1].equalsIgnoreCase("--alphabet") || args[1].equalsIgnoreCase("-a"))) {
                    int name_part = 0;
                    if (args.length >= 3){
                        if (args[2].equalsIgnoreCase("first"))
                            name_part = 0;
                        else if (args[2].equalsIgnoreCase("middle"))
                            name_part = 1;
                        else if (args[2].equalsIgnoreCase("last"))
                            name_part = 2;
                    }
                    print_alphabet_sorted(cus, name_part);
                }
                else if(args.length >= 4 && (args[1].equals("--credit-range") || args[1].equals("-c"))) {
                    try {
                        long start = Long.parseLong(args[2]);
                        long end = Long.parseLong(args[3]);
                        print_card_range(cus, start, end);
                    } catch (NumberFormatException e){
                        System.out.println("Error: start and end must be number");
                    }
                }
                else {
                    System.out.println("Unknown sort type");
                }
            }


        }
    }
    public static void print_brief(){
        System.out.println(
                "Usage: command [option]\n" +
                "Commands:\n" +
                "\tsort [-a | --alphabet <first, middle, last>, default: first]\n" +
                "\t\t [-c | --credit-range <start end>]\n" +
                "\t\t # sort Customers\n" +
                "\thelp | ? # print this\n" +
                "\texit # end program"
        );
    }
    public static void print_alphabet_sorted(Customer[] cus, int name_part){
//        Customer[] sorted = cus;
        Customer[] sorted = cus.clone();

        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = i + 1; j < sorted.length; j++) {

                if (name_part == 0 && sorted[i].getFirstName().compareToIgnoreCase(sorted[j].getFirstName()) > 0) {
                    Customer temp = sorted[i];
                    sorted[i] = sorted[j];
                    sorted[j] = temp;
                } else if (name_part == 1 && sorted[i].getMiddleName().compareToIgnoreCase(sorted[j].getMiddleName()) > 0) {
                    Customer temp = sorted[i];
                    sorted[i] = sorted[j];
                    sorted[j] = temp;
                } else if (name_part == 2 && sorted[i].getLastName().compareToIgnoreCase(sorted[j].getLastName()) > 0) {
                    Customer temp = sorted[i];
                    sorted[i] = sorted[j];
                    sorted[j] = temp;
                }
            }
        }

        System.out.println("Sorted by alphabet:\n");
        for (Customer c : sorted) {
            System.out.println(c);
        }
    }
    public static void print_card_range(Customer[] cus, long start, long end){
        System.out.println("Customers in range:\n");
        for (Customer c : cus){
            if (c.getCardNumber() >= start && c.getCardNumber() <= end)
                System.out.println(c);
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

