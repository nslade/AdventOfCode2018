package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class part1 {

    public static int calculateFrequency(int startFreq, List<Integer> changes) {
        int currentFreq = startFreq;
        for (int i = 0; i < changes.size(); i++) {
            currentFreq += changes.get(i);
        }
        return currentFreq;
    }

    public static List<Integer> readInput(String filepath) {

        List<Integer> inputValues = new ArrayList<>();

        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    inputValues.add(scanner.nextInt());
                } else {
                    scanner.next();
                }
            }
            return inputValues;
        } catch(FileNotFoundException fnfe) {
            System.out.println("File not found");
        }
        return null;
    }

    public static void main(String []args)
    {
        int startingFrequency = 0;
        Path relativePath = Paths.get("inputs/day1_test4.txt");
        Path absolutePath = relativePath.toAbsolutePath();
        List<Integer> inputValues = readInput(absolutePath.toString());
        if (inputValues == null) {
            return;
        }

        int finalFrequency = calculateFrequency(startingFrequency, inputValues);
        System.out.println(finalFrequency);

        return;
    }
}
