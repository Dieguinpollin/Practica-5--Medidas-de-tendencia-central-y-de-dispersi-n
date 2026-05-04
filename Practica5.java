import java.io.File;
import java.util.*;

public class Practica5 {
    public static void main(String[] args) throws Exception {
        List<Integer> d = new ArrayList<>();
        Scanner s = new Scanner(new File("matriculas.txt"));
        
        while (s.hasNextLine()) {
            for (char c : s.nextLine().toCharArray()) {
                if (Character.isDigit(c)) d.add(c - '0');
            }
        }
        s.close();

        int n = d.size();
        Collections.sort(d);

        double sum = 0, sumSq = 0;
        for (int x : d) sum += x;
        double mean = sum / n;

        for (int x : d) sumSq += Math.pow(x - mean, 2);
        double var = sumSq / (n - 1);
        double std = Math.sqrt(var);

        int range = d.get(n - 1) - d.get(0);
        double q1 = p(d, 25), q2 = p(d, 50), q3 = p(d, 75);

        int mode = d.get(0), maxF = 0;
        int[] f = new int[10];
        for (int x : d) {
            f[x]++;
            if (f[x] > maxF) {
                maxF = f[x];
                mode = x;
            }
        }

        System.out.printf("Media: %.4f\n", mean);
        System.out.printf("Varianza: %.4f\n", var);
        System.out.printf("Desviacion estandar: %.4f\n", std);
        System.out.println("Rango: " + range);
        System.out.printf("Q1: %.2f, Q2 (Mediana): %.2f, Q3: %.2f\n", q1, q2, q3);
        System.out.printf("IQR: %.2f\n", (q3 - q1));
        System.out.println("Moda: " + mode);
    }

    static double p(List<Integer> d, double p) {
        double pos = (p / 100.0) * (d.size() - 1);
        int i = (int) pos;
        return i + 1 < d.size() ? d.get(i) + (pos - i) * (d.get(i + 1) - d.get(i)) : d.get(i);
    }
}
