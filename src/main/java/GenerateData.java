import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.random.RandomGenerator;

public class GenerateData {

    public static void main(String[] args) throws Exception {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 80;
        long seed = args.length > 1 ? Long.parseLong(args[1]) : 42L;

        RandomDataGenerator rnd = new RandomDataGenerator(new MersenneTwister(seed));
//        Random rnd = new Random();
        List<String> lines = new ArrayList<>();
        lines.add("group,hours,attendance,gpa,exam,result");

        for (int i = 0; i < n; i++) {
            char group = rnd.nextInt(0, 1) == 0 ? 'A' : 'B';
//            double hours = round(rnd.nextDouble(2.0, 20.0), 1);
            double hours = round(rnd.nextUniform(2.0, 20.0), 1);
            int attendance = rnd.nextInt(50, 100);
            double gpa = round(in_range(rnd.nextGaussian(3.0, 0.5), 2.0, 4.0), 2);

            double noise = rnd.nextGaussian(0.0, 6.0);
            double score = -13 + 2.0 * hours + 0.4 * attendance + 6.5 * gpa + noise;
            int exam = (int) Math.round(in_range(score, 0, 100));

            String result = exam >= 60 ? "pass" : "fail";

            lines.add(String.format(Locale.ROOT, "%c,%.1f,%d,%.2f,%d,%s",
                    group, hours, attendance, gpa, exam, result));
        }

        Files.createDirectories(Path.of("data"));
        Files.write(Path.of("data/students.csv"), lines);
        System.out.printf("Created %d lines: data/students.csv", n);
    }

    static double round(double v, int digits) {
        double p = Math.pow(10, digits);
        return Math.round(v * p) / p;
    }

    static double in_range(double v, double lo, double hi) {
        return Math.max(lo, Math.min(hi, v));
    }
}