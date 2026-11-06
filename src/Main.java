import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Main{
    public static void main(String[] args) {
        variant_C();
//        variant_C_without_buffer();
    }

    /** Variant C (files) SRSP
     *  Прочитать текст Java-программы и записать в
     *  другой файл в обратном порядке символы каждой строки.
     */
    static void variant_C() {
        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = new BufferedReader( new FileReader("/home/miocasa/IdeaProjects/java_lessons/src/Main.java"))){
            String line = "";

            while ((line = reader.readLine()) != null){
                result.append(flipLine(line));
                result.append('\n');
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Inverted lines file:\n%s", result.toString());
        try (FileWriter writer = new FileWriter("/home/miocasa/IdeaProjects/java_lessons/src/main_inverted.java")) {
            writer.write(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
/*
    static void variant_C_without_buffer() {

        try (BufferedReader reader = new BufferedReader( new FileReader("/home/miocasa/IdeaProjects/java_lessons/src/Main.java"))){
            FileWriter writer = new FileWriter("/home/miocasa/IdeaProjects/java_lessons/src/main_inverted.java");

            String line = "";

            while ((line = reader.readLine()) != null){
                writer.write(flipLine(line));
                writer.write('\n');
            }

            // check file
            BufferedReader result_reader = new BufferedReader( new FileReader("/home/miocasa/IdeaProjects/java_lessons/src/main_inverted.java"));
            System.out.println("Inverted lines file:");

            while ((line = result_reader.readLine()) != null) {
                System.out.printf("%s\n", line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
    public static String flipLine(String line) {
        String result = "";

        for (int i = line.length() - 1; i >= 0 ; i--) {
            result += (char)line.getBytes()[i];
        }
        return result;
    }


}

