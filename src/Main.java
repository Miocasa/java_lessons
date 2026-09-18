import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.*;


public class Main{
    public static void main(String[] args) {
//        lesson4_1_med_1();
//        lesson4_1_med_2();

//        lesson4_1_high_1();
//        lesson4_1_high_2();

//        lesson4_2_med_1();
//        lesson4_2_med_2();
//        lesson4_2_high_1();
        lesson4_2_high_2();
    }

    /** 4.1 Массивы, Средний
     *  1. Ввести два целочисленных массива − по 10 элементов в каждом. Сформировать новый массив, на четных местах которого будут элементы с
     *  нечетными индексами из первого массива, а на нечетных – с четными индексами из второго.
     *
     *  Проверочные значения:
     *  Ввод:
     *      11 12 13 14 15 16 17 18 19 20
     *      21 22 23 24 25 26 27 28 29 30
     *  Вывод:
     *      12 21 14 23 16 25 18 27 20 29
     */
    static void lesson4_1_med_1() {
        Scanner sc = new Scanner(System.in);

        int size = 10;
        int[] a = new int[size];
        int[] b = new int[size];

        int[] res = new int[size];

        System.out.printf("Enter numbers of first massive (%d piece):\n", size);
        for (int i = 0; i < size; i++) {
            a[i] = sc.nextInt();
        }
        System.out.printf("Enter numbers of second massive (%d piece):\n", size);
        for (int i = 0; i < size; i++) {
            b[i] = sc.nextInt();
        }

//        1 2 3 4 5
//        12 23
        for (int i = 0; i < size; i++) {
            res[i] = (i % 2 == 0)
                    ? a[(i + 1) % size]
                    : b [i - 1 ];
        }
        System.out.print("Out massive:\n");


        for (int i : res){
            System.out.print(i + " ");
        }

//        System.out.printf("Binary in decimal: %d", res);
    }

    /** 4.1 Массивы, Средний
     *  2. Ввести массив, состоящий из 8 элементов (восемь двузначных чисел) целого типа.
     * Получить новый массив, состоящий из цифр, находящихся в младших разрядах элементов исходного массива.
     *
     *  Проверочные значения:
     *  Ввод:
     *  12 34 56 78 90 23 45 67
     *  Вывод:
     *  2 4 6 8 0 3 5 7
     */
    static void lesson4_1_med_2() {
        Scanner sc = new Scanner(System.in);

        int size = 8;
        int[] a = new int[size];

        int[] res = new int[size];

        System.out.printf("Enter numbers of first massive (%d piece):\n", size);
        for (int i = 0; i < size; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < size; i++) {
            res[i] = a[i] % 10;
        }

        System.out.print("Out massive:\n");
        for (int i : res){
            System.out.print(i + " ");
        }
    }

    /** 4.1 Массивы, Высокий
     *  1. Дано целое число в двоичной системе счисления, т.е. последовательность цифр  0 и 1.
     *  Составить программу перевода этого числа в десятичную систему счисления.
     *
     *  Проверочные значения:
     *  Ввод 1011 вывод 13
     *  Ввод 111111 вывод 63
     */
    static void lesson4_1_high_1(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number, 31bit max: ");
        String binary = sc.next();


        int res = 0;
        for (int i = 0; i < min(32, binary.length()); i++) {
            int b = (binary.charAt(i) == '1') ? 1 : 0;
            res |= (b << i);
        }

        System.out.printf("Binary in decimal: %d", res);
    }

    /** 4.1 Массивы, Высокий
     *  2. Дано целое число в двоичной системе счисления, т.е. последовательность цифр 0 и 1.
     *  Составить программу перевода этого числа в восьмеричную систему счисления.
     *
     *  Проверочные значения:
     *  Ввод 1011 вывод 15
     *  Ввод 111111 вывод 77
     *
     *  Сделал бонусом, просто поменял формат вывода с decimal на octal (printf)
     */
    static void lesson4_1_high_2(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number, 31bit max: ");
        String binary = sc.next();


        int res = 0;
        for (int i = 0; i < min(32, binary.length()); i++) {
            int b = (binary.charAt(i) == '1') ? 1 : 0;
            res |= (b << i);
        }

        System.out.printf("Binary in octal: %o", res);

    }


    /** 4.2 Двухмерные массивы, Средний
     * 1. В произвольной матрице - отсортировать по убыванию элементы последовательности, расположенные после второго отрицательного числа.
     *
     * Проверочные значения:
     * Ввод:
     * (rows cols): 3 3
     * 5 -2 8
     * 1 -4 3
     * 9 7 6
     * Вывод:
     * 5 -2 8
     * 1 -4 9
     * 7 6 3
     */
    static void lesson4_2_med_1() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Matrix size (rows cols): ");

        int w = sc.nextInt();
        int h = sc.nextInt();

        int[][] mass = new int[w][h];

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                mass[i][j] = sc.nextInt();
            }
        }

        int neg = 0;
        int index = -1;

        for (int i = 0; i < w * h; i++) {
            int r = i / h;
            int c = i % h;

            if (mass[r][c] < 0) {
                neg++;
                if (neg == 2) {
                    index = i;
                    break;
                }
            }
        }

        if (index == -1 || index == w * h - 1) {
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    System.out.print(mass[i][j] + " ");
                }
                System.out.println();
            }
            return;
        }

        int len = (w * h) - (index + 1);
        int[] tail = new int[len];

        int k = 0;
        for (int i = index + 1; i < w * h; i++) {
            tail[k++] = mass[i / h][i % h];
        }

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (tail[i] < tail[j]) {
                    int temp = tail[i];
                    tail[i] = tail[j];
                    tail[j] = temp;
                }
            }
        }

        k = 0;
        for (int i = index + 1; i < w * h; i++) {
            mass[i / h][i % h] = tail[k++];
        }

        System.out.print("Out massive:\n");
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                System.out.print(mass[i][j] + " ");
            }
            System.out.println();
        }
    }


    /** 4.2 Двухмерные массивы, Средний
     * 2. Необходимо заполнить двухмерный массив из 0 и 1. А после его вывода -
     * массив должен иметь следующий вид:
     * 0 1 0 1
     * 1 0 1 0
     * 0 1 0 1
     * 1 0 1 0
     *
     * Проверочные значения:
     * Ввод: None
     * Вывод:
     * 0 1 0 1
     * 1 0 1 0
     * 0 1 0 1
     * 1 0 1 0
     */
    static void lesson4_2_med_2() {
        int rows = 4;
        int cols = 4;
        int[][] matrix = new int[rows][cols];

        for (int k = 0; k < rows * cols; k++) {
            int i = k / cols;
            int j = k % cols;

            matrix[i][j] = (i + j) % 2;
        }

        for (int k = 0; k < rows * cols; k++) {
            int i = k / cols;
            int j = k % cols;

            System.out.print(matrix[i][j] + " ");

            if (j == cols - 1) {
                System.out.println();
            }
        }
    }



    /** 4.2 Двухмерные массивы, Высокий
     * 1. Дан массив фамилий студентов 1-го курса и массив их результатов в беге на 100 метров.
     * Составьте команду из четырех лучших бегунов для участия в эстафете.
     *
     * Проверочные значения:
     * Ввод:
     * 5
     * abc 12.32
     * ads 16.4
     * fdg 2.3
     * goj 7.5
     * win 1.1
     * Вывод:
     * 0. win - 1.10
     * 1. fdg - 2.30
     * 2. goj - 7.50
     * 3. abc - 12.32
     */
    static void lesson4_2_high_1(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter runners count: ");
        int n = sc.nextInt();

        String[] name = new String[n];
        double[] time = new double[n];

        System.out.print("Enter runners (name time):\n");

        for (int i = 0; i < n; i++) {
            name[i] = sc.next();
            time[i] = sc.nextDouble();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (time[i] > time[j]){
                    double t = time[j];
                    time[j] = time[i];
                    time[i] = t;
                    String nm = name[j];
                    name[j] = name[i];
                    name[i] = nm;
                }
            }
        }
        System.out.println("Best four runners:");
        for (int i = 0; i < 4; i++) {
            System.out.printf("%d. %s - %2.2f\n", i, name[i], time[i]);
        }
    }

    /** 4.2 Двухмерные массивы, Высокий
     * 2.Сформируйте массив L(I,J) с помощью датчика случайных чисел.
     * Увеличить каждый элемент массива в 3 раза и поменяйте знак на противоположный.
     * Массив выведите на экран в виде таблицы
     *
     * Проверочные значения:
     * Ввод:
     *
     * Вывод:
     */
    static void lesson4_2_high_2(){
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter rows and cols count (row col): ");
        int r = sc.nextInt();
        int c = sc.nextInt();

        final int MAX_VALUE = 1024;

        int[][] mass = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mass[i][j] = random.nextInt(MAX_VALUE) - (MAX_VALUE / 2);
            }
//            mass[i][0] = (int)(MAX_VALUE * random()) - (MAX_VALUE / 2);
//            mass[i][1] = (int)(MAX_VALUE * random()) - (MAX_VALUE / 2);
        }

        int horLineLen = c * 8 + 1;

        System.out.println("Original massive:");
        System.out.println("_".repeat(horLineLen));

        for (int i = 0; i < r; i++) {

            for (int j = 0; j < c; j++) {
                System.out.printf("| %5d ", mass[i][j]);
            }
            System.out.println('|');
        }
        System.out.println("-".repeat(horLineLen));


        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mass[i][j] = mass[i][j] * -3;
            }

        }

        System.out.println("Out massive:");
        System.out.println("_".repeat(horLineLen));

        for (int i = 0; i < r; i++) {

            for (int j = 0; j < c; j++) {
                System.out.printf("| %5d ", mass[i][j]);
            }
            System.out.println('|');
        }
        System.out.println("-".repeat(horLineLen));

//        System.out.println("Out massive:");
//        System.out.println("_______________");
//        for (int i = 0; i < r; i++) {
//
//            System.out.printf("| %4d | %4d |\n", mass[i][0], mass[i][1]);
//        }
//        System.out.println("---------------");

    }
}