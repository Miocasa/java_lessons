import Publisher.Book;
import Publisher.Declinable;
import Publisher.LessonBook;
import Publisher.Magazine;

import java.time.LocalDate;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        variant_A();
    }


    /** Variant A
     * 1. Полиграфические издания. Возможности: оформить договор; открыть\
     * редактировать\верстать издание; отправить на печать; отказаться от издания; оплатить издание;
     * возобновить\закрыть издание. Добавить
     */
    static void variant_A() {
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

        // Polymorph
        Magazine magazine = new Magazine("Science Today");
        magazine.setMagazineEdition(12);
        magazine.acceptContract();
        Declinable d = magazine;
        d.declinePublishing();
        System.out.println(magazine);
        d.resumePublishing();
        System.out.println(magazine);

        // Errors
        LessonBook lb = new LessonBook("Algebra 7");
        lb.setGrade(7);
        lb.acceptContract();
        lb.open();
        try {
            lb.makeUp();
        } catch (IllegalStateException e) {
            System.out.println("State error: " + e.getMessage());
//            System.out.println(e);
        }
        lb.addExercises(120);
        lb.makeUp();
        lb.print();
        lb.setPrice(1000.0);
        try {
            lb.pay(10.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Arg error: " + e.getMessage());
        }
    }
}

