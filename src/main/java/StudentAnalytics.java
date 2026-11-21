import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import smile.clustering.KMeans;
import smile.clustering.PartitionClustering;
import smile.data.DataFrame;
import smile.io.Read;
import smile.math.MathEx;
import smile.plot.swing.Canvas;
import smile.plot.swing.Heatmap;
import smile.plot.swing.ScatterPlot;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Университет студенттерінің үлгерімін талдау жүйесі.
 * Commons Math — статистика, Weka — болжау моделі, Smile — сегменттеу және графиктер.
 */
public class StudentAnalytics {
    record NewStudent(String name, double hours, double attendance, double gpa) { }
    static final String[] FEATURES = {"hours", "attendance", "gpa"};
    static final List<String> report = new ArrayList<>();
    static void log(String format, Object... args) {
        String line = String.format(format, args);
        System.out.println(line);
        report.add(line);
    }
    public static void main(String[] args) throws Exception {
        MathEx.setSeed(19650218);
        new File("out").mkdirs();
        new File("model").mkdirs();
// ===== 1. Деректі жүктеу (Smile) =====
        DataFrame df = Read.csv("data/students.csv", "header=true");
        double[][] x = df.select(FEATURES).toArray();                 // n x 3
        double[] exam = df.column("exam").toDoubleArray();
        String[] result = df.column("result").toStringArray();
        log("1) Деректер: %d студент, %d белгі", df.nrow(), FEATURES.length);
// ===== 2. Статистикалық талдау (Commons Math) =====
        log("%n2) Сипаттамалық статистика:");
        String[] cols = {"hours", "attendance", "gpa", "exam"};
        double[][] all = df.select(cols).toArray();
        for (int j = 0; j < cols.length; j++) {
            DescriptiveStatistics s = new DescriptiveStatistics();
            for (double[] row : all) s.addValue(row[j]);
            log("   %-10s орташа=%6.2f  std=%5.2f  медиана=%6.2f  [%5.1f; %5.1f]",
                    cols[j], s.getMean(), s.getStandardDeviation(), s.getPercentile(50), s.getMin(),
                    s.getMax());
        }
        double[][] corr = new PearsonsCorrelation(all).getCorrelationMatrix().getData();
        log("   exam-мен корреляция: hours=%.2f  attendance=%.2f  gpa=%.2f", corr[0][3], corr[1][3], corr[2][3]);
        OLSMultipleLinearRegression ols = new OLSMultipleLinearRegression();
        ols.newSampleData(exam, x);
        double[] beta = ols.estimateRegressionParameters();
        log("   Балл моделі: exam = %.2f + %.2f*hours + %.2f*attendance + %.2f*gpa  (R^2=%.3f)",
                beta[0], beta[1], beta[2], beta[3], ols.calculateRSquared());
// ===== 3. Болжау моделі (Weka) =====
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File("data/students.csv"));
        Instances raw = loader.getDataSet();
        Remove remove = new Remove();
        remove.setAttributeIndices("1,5");                             // group, exam
        remove.setInputFormat(raw);
        Instances data = Filter.useFilter(raw, remove);
        data.setClassIndex(data.numAttributes() - 1);
        int passIdx = data.classAttribute().indexOfValue("pass");
        log("%n3) Weka модельдері (10-fold CV):");
        for (var model : new weka.classifiers.Classifier[]{new J48(), new Logistic()}) {
            Evaluation e = new Evaluation(data);
            e.crossValidateModel(model, data, 10, new Random(1));
            log("   %-9s дәлдік=%.1f%%  AUC=%.3f", model.getClass().getSimpleName(), e.pctCorrect(),
                    e.areaUnderROC(passIdx));
        }
        Logistic best = new Logistic();
        best.buildClassifier(data);
        SerializationHelper.write("model/pass-logistic.model", best);
        log("   Logistic моделі model/pass-logistic.model файлына сақталды");
// ===== 4. Сегменттеу (Smile k-means) =====
        double[] mu = MathEx.colMeans(x), sd = MathEx.colSds(x);
        double[][] z = new double[x.length][];
        for (int i = 0; i < x.length; i++) z[i] = standardize(x[i], mu, sd);
        KMeans km = PartitionClustering.run(20, () -> KMeans.fit(z, 3));
        log("%n4) Студенттер сегменттері (k-means, k=3):");
        for (int c = 0; c < km.k; c++) {
            int n = 0, pass = 0;
            double[] sum = new double[3];
            double examSum = 0;
            for (int i = 0; i < x.length; i++) {
                if (km.y[i] != c) continue;
                n++;
                examSum += exam[i];
                if (result[i].equals("pass")) pass++;
                for (int j = 0; j < 3; j++) sum[j] += x[i][j];
            }
            log("   сегмент %d: %2d студент | сағат=%4.1f қатысу=%3.0f%% GPA=%.2f | орташа балл=%.1f | өткендер=%3.0f%%",
                    c, n, sum[0] / n, sum[1] / n, sum[2] / n, examSum / n, 100.0 * pass / n);
        }

// ===== 5. Жаңа семестр: тәуекел тобын анықтау (үш кітапхана бірге) =====
        List<NewStudent> incoming = List.of(
                new NewStudent("Айгерім", 15.0, 92, 3.6),
                new NewStudent("Нұрлан", 4.5, 70, 2.8),
                new NewStudent("Дана", 9.5, 80, 3.0),
                new NewStudent("Ерлан", 11.0, 60, 2.5),
                new NewStudent("Әлия", 7.0, 95, 3.9),
                new NewStudent("Тимур", 3.0, 55, 2.2));
        log("%n5) Жаңа студенттер бойынша болжам:");
        log("   %-8s %6s %8s %5s | %10s | %7s | %7s | %s", "аты", "сағат", "қатысу", "GPA", "болж. балл", "P(pass)", "сегмент", "шешім");
        double[][] newPoints = new double[incoming.size()][];
        for (int k = 0; k < incoming.size(); k++) {
            NewStudent s = incoming.get(k);
            double[] f = {s.hours(), s.attendance(), s.gpa()};
            double score = beta[0] + beta[1] * f[0] + beta[2] * f[1] + beta[3] * f[2];   // Commons Math
            Instance inst = new DenseInstance(data.numAttributes());                       // Weka
            inst.setDataset(data);
            for (int j = 0; j < 3; j++) inst.setValue(j, f[j]);
            double p = best.distributionForInstance(inst)[passIdx];
            int seg = km.predict(standardize(f, mu, sd));                                  // Smile
            String decision = p < 0.3 ? "ТӘУЕКЕЛ: кураторға хабарлау" : p < 0.7 ? "бақылауда ұстау" :
                    "қалыпты";
            log("   %-8s %6.1f %7.0f%% %5.2f | %10.1f | %7.3f | %7d | %s",
                    s.name(), s.hours(), s.attendance(), s.gpa(), score, p, seg, decision);
            newPoints[k] = new double[]{s.hours(), s.attendance()};
        }
// ===== 6. Визуализация (Smile) =====
        String[] labels = {"hours", "attendance", "gpa", "exam"};
        Color[] blues = new Color[16];                                 // ақтан (0) көкке (1) дейін
        for (int i = 0; i < blues.length; i++) {
            int c = 255 - i * 13;
            blues[i] = new Color(c, c + (255 - c) / 3, 255);
        }
        Canvas heat = new Heatmap(labels, labels, corr, blues).canvas();
        heat.setTitle("Корреляция матрицасы (Пирсон)");
        heat.setAxisLabels("", "");
        ImageIO.write(heat.toBufferedImage(800, 650), "png", new File("out/correlation.png"));
        String[] segLabels = new String[x.length];
        for (int i = 0; i < x.length; i++) segLabels[i] = "сегмент " + km.y[i];
        double[][] hoursAtt = new double[x.length][];
        for (int i = 0; i < x.length; i++) hoursAtt[i] = new double[]{x[i][0], x[i][1]};
        Canvas seg = ScatterPlot.of(hoursAtt, segLabels, 'o').canvas();
        seg.add(ScatterPlot.of(newPoints, '*', Color.BLACK));
        seg.setTitle("Студенттер сегменттері (* — жаңа студенттер)");
        seg.setAxisLabels("Апталық оқу сағаты", "Қатысу, %");
        ImageIO.write(seg.toBufferedImage(900, 600), "png", new File("out/segments.png"));
        log("%n6) Графиктер: out/correlation.png, out/segments.png");
        Files.write(Path.of("out/report.txt"), report);
        System.out.println("Есеп out/report.txt файлына жазылды");
    }

    static double[] standardize(double[] v, double[] mu, double[] sd) {
        double[] r = new double[v.length];
        for (int j = 0; j < v.length; j++) r[j] = (v[j] - mu[j]) / sd[j];
        return r;
    }
}