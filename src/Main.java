import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Main{
    public static void main(String[] args) {
//        variant_A();
//        variant_B();
        srsp();
    }

    // Srsp Ex 6 from (13 Зертханлық жұмыс. Файлдар)
    public static void srsp() {
        Map<Double, Integer> map = new TreeMap<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("number1.txt"), StandardCharsets.UTF_8);

            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty()) continue;
                for (String token : line.split("\\s+")) {
                    double value = Double.parseDouble(token.replace(',', '.'));
                    map.merge(value, 1, Integer::sum);
                }
            }

            StringBuilder result = new StringBuilder();
            for (Map.Entry<Double, Integer> e : map.entrySet()) {
                result.append(fmt(e.getKey())).append(" - ")
                        .append(e.getValue()).append(System.lineSeparator());
            }
            System.out.print(result);
            Files.writeString(Paths.get("number3.txt"), result.toString());


            Path path2 = Paths.get("number2.txt");
            Files.writeString(path2,
                    "Result = " + map.size() + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            System.out.println("number2.txt:");
            for (String s : Files.readAllLines(path2, StandardCharsets.UTF_8)) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String fmt(double d) {
        return d == Math.rint(d) ? String.valueOf((long) d) : String.valueOf(d);
    }

    /** Variant A (КОЛЛЕКЦИИ И STREAM API)
     * Ввести число, занести его цифры в стек. Вывести число, у которого цифры
     * идут в обратном порядке.
     */
    static void variant_A() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number: ");
        long num = sc.nextLong();

        boolean neg = num < 0;
        if (neg) {
            num = -num;
        }

        Stack<Integer> stack = new Stack<>();
        while (num > 0) {
            stack.push((int)(num % 10));
            num /= 10;
        }
//        System.out.println(stack);

        long result = 0;
        while (!stack.isEmpty()) {
            result = result * 10 + stack.remove(0); // stack.pop();
        }
        if (neg) {
            result = -result;
        }

        System.out.printf("Number with reversed digits: %d\n", result);
    }

    /** Variant B (КОЛЛЕКЦИИ И STREAM API)
     *  Задан список целых чисел и некоторое число X. Не используя вспомогательных объектов и методов сортировки
     *  и не изменяя размера списка, переставить элементы списка так, чтобы сначала шли числа, не превосходящие X, а затем числа, больше X.
     *
     *  input:
     *      Enter threshold: 4
     *  output:
     *      Before sort: [1, -20, 30, 9, -4, 5, 789, 0, -44, 556]
     *      After sort: [0, -20, -44, 0, -4, 5, 789, 9, 30, 556]
     */
    static void variant_B() {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1,-20,30,9,-4,5,789,0,-44,556);

        System.out.print("Enter threshold: ");
        int x = sc.nextInt();

        System.out.printf("Before sort: %s\n", list);
        sort(list, x);
        System.out.printf("After sort: %s\n", list);

    }
    static void sort(List<Integer> list, int x) {
        int more = 0;
        int less = list.size() - 1;

        while (more < less) {
//            for (; more < less && list.get(more) <= x; more++) {}
//            for (; more < less && list.get(less) > x; less--) {}
            while (more < less && list.get(more) <= x) {
                more++;
            }
            while (more < less && list.get(less) > x) {
                less--;
            }

            if (more < less) {
                int tmp = list.get(more);
                list.set(more, list.get(less));
                list.set(less, tmp);
                more++;
                less--;
            }
        }

        list.set(0, 0);
    }
}

