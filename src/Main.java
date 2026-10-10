import java.time.LocalDate;
import java.util.Scanner;


public class Main{
    public static void main(String[] args) {
        variant_A();
    }

    /** Variant A
     * Создать класс Notepad с внутренним классом или классами, с помощью
     * объектов которого могут храниться несколько записей на одну дату.
     */
    static void variant_A() {
        Scanner sc = new Scanner(System.in);
//        Notepad.Page page = new Notepad.Page(LocalDate.of(2022, 9, 23));
//        System.out.printf("%s\n", page);

        Notepad notepad = new Notepad();
        notepad.add_note(LocalDate.of(2025, 3, 15), "Buy milk");
        notepad.add_note(LocalDate.of(2025, 3, 15), "Call +7 (7xx) xxx xx xx");
        notepad.add_note(LocalDate.of(2025, 3, 15), "Pass IElTS exam");
        notepad.add_note(LocalDate.of(2025, 3, 16), "Online meet with KAne");
        notepad.add_note(LocalDate.of(2025, 3, 16), "Take a post");

        System.out.println("All notes");
        notepad.print_all();

        System.out.println("\nNotes on 15.03.2025");
        notepad.print_by_date(LocalDate.of(2025, 3, 15));
    }


}