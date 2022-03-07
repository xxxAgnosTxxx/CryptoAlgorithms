package george;

import java.io.IOException;


public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileInitializer fileInitializer = FileInitializer.getFileInitializer();
        byte[] bytefile = fileInitializer.getSource();

        TimeCryptor e1 = new TimeCryptor(Algorithms.DES, Regimes.ECB);
        TimeCryptor e2 = new TimeCryptor(Algorithms.DES, Regimes.CBC);
        TimeCryptor e3 = new TimeCryptor(Algorithms.RC2, Regimes.ECB);
        TimeCryptor e4 = new TimeCryptor(Algorithms.RC2, Regimes.CBC);
        TimeCryptor e5 = new TimeCryptor(Algorithms.Rijndael, Regimes.ECB);
        TimeCryptor e6 = new TimeCryptor(Algorithms.Rijndael, Regimes.CBC);

        long test = e1.getTimeAlghoritm(bytefile);
        System.out.println(test + " msec.");
        System.out.println(e2.getTimeAlghoritm(bytefile) + test + " msec.");

        test += e3.getTimeAlghoritm(bytefile);
        System.out.println( test + " msec.");
        System.out.println(e4.getTimeAlghoritm(bytefile) + test + " msec.");

        test += e5.getTimeAlghoritm(bytefile);
        System.out.println(test  + " msec.");
        System.out.println(e6.getTimeAlghoritm(bytefile) + test  + " msec.");

    }
}
