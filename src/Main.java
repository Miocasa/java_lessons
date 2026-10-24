import ProgrammingBook.*;

import Publisher.Book;
import Publisher.Declinable;
import Publisher.LessonBook;
import Publisher.Magazine;

import java.util.*;

public class Main{
    public static void main(String[] args) {
        variant_A();
//        variant_B();
    }

    // Интерйфейс - срс
    // Строки - практика

    /** Variant A (interfaces) SRSP
     * 1. Полиграфические издания. Возможности: оформить договор; открыть\
     * редактировать\верстать издание; отправить на печать; отказаться от издания; оплатить издание;
     * возобновить\закрыть издание. Добавить
     */
    static void variant_A() {
        Scanner sc = new Scanner(System.in);

        Book book = new Book("Kotlin integrated in java");
        book.assignBookId("978-5-XX-XXXXXX-X");
        book.setCopiesCount(3000);
        book.setPrice(50000.0);

        book.acceptContract();
        book.open();
        book.edit("Chapter 1...");
        book.makeUp();
        book.print();
        book.pay(50000.0);
        book.close();
        System.out.println(book);


        Magazine magazine = new Magazine("Science Today");
        magazine.setMagazineEdition(12);
        magazine.acceptContract();
        magazine.declinePublishing();
        System.out.println(magazine);
        magazine.resumePublishing();
        System.out.println(magazine);

        // Errors
        LessonBook lb = new LessonBook("Algebra 7");
        lb.setGrade(7);
        lb.acceptContract();
        lb.open();
        try {
            lb.makeUp();
        } catch (IllegalStateException e) {
            System.out.printf("State error: %s\n", e.getMessage());
//            System.out.println(e);
        }
        lb.addExercises(120);
        lb.makeUp();
        lb.print();
        lb.setPrice(1000.0);
        try {
            lb.pay(10.0);
        } catch (IllegalArgumentException e) {
            System.out.printf("Arg error: %s\n", e.getMessage());
        }
    }

    /** Variant B (strings) practic
     * Создать программу обработки текста учебника по программированию с использованием классов: Символ, Слово, Предложение, Абзац, Лексема, Листинг,
     * Знак препинания и др. Во всех задачах с формированием текста заменять табуляции и последовательности пробелов одним пробелом.
     * Предварительно текст следует разобрать на составные части, выполнить
     * одно из перечисленных ниже заданий и вывести полученный результат.
     * 1. Найти наибольшее количество предложений текста, в которых есть одинаковые слова.
     */
    static void variant_B() {
        // source text, tabs and spaces are collapsed by the parser
        String rawText = "Variable stores a    value. A value can be changed.\n" +
                         "Loop repeats the   code.\tLoop    checks the condition.\n" +
                         "\n" +
                         "```java\n" +
                         "for (int i = 0; i < 3; i++) { System.out.println(i); }\n" +
                         "```\n" +
                         "\n" +
                         "The condition in a   loop returns a value.\n";

        Text text = TextParser.parse(rawText);
//        System.out.println(rawText);
        System.out.println(text);

        List<Sentence> sentences = text.getAllSentences();

        int max = 0;
        List<Word> best = new ArrayList<>();
        for (Sentence s : sentences) {
            for (Word w : s.getUniqueWords()) {
                int count = 0;
                for (Sentence other : sentences) {
                    if (other.getUniqueWords().contains(w)) {
                        count++;
                    }
                }
                if (count > max) {
                    max = count;
                    best.clear();
                    best.add(w);
                } else if (count == max && !best.contains(w)) {
                    best.add(w);
                }
            }
        }

        System.out.printf("Max sentences with the same word: %d\n", max);
        for (Word w : best) {
            System.out.printf("Word \"%s\" is in sentences:\n", w);
            for (Sentence s : sentences) {
                if (s.getUniqueWords().contains(w)) {
                    System.out.printf("  %s\n", s);
                }
            }
        }
    }


}

