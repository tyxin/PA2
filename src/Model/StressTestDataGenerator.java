package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class StressTestDataGenerator {
    public static void main(String[] args) {
        String[] filenames = {"healthcare","mrt","schools"};

        for (int j = 0;j< filenames.length;j++){
            Random random = new Random(j);
            try {
                File file = new File("src/resources/stressTest/"+filenames[j]+"_data.csv");

                FileWriter fileWriter = new FileWriter(file);
                for (int i = 0; i < 1000000; i++) {
                    fileWriter.append("data" + String.valueOf(i) + "," + (double) (random.nextInt(10000000) + 1) / 1000000 +
                            "," + (double) (random.nextInt(10000000) + 1) / 1000000 + ","+(random.nextInt(100)+1) +"\n");
                }

                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}