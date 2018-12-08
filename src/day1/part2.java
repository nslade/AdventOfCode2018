package day1;

import common.fileInput;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class part2 {

    public static void findDuplicateFrequency(int startFreq, List<Integer> changes) {
        int currentFreq = startFreq;
        List<Integer> previousValues = new ArrayList<>();
        boolean duplicateFound = false;
        previousValues.add(startFreq);
        while (!duplicateFound) {
            for (int i = 0; i < changes.size(); i++) {
                currentFreq += changes.get(i);
                if (!previousValues.contains(currentFreq)) {
                    previousValues.add(currentFreq);
                } else {
                    duplicateFound = true;
                    break;
                }
            }
        }
        System.out.println("First repeated frequency: " + currentFreq);
        return;
    }

    public static void calculateFinalFrequency(int startFreq, List<Integer> changes) {
        int currentFreq = startFreq;
        for (int i = 0; i < changes.size(); i++) {
            currentFreq += changes.get(i);
        }
        System.out.println("Final frequency: " + currentFreq);
        return;
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
        Path relativePath = Paths.get("inputs/day1.txt");
        Path absolutePath = relativePath.toAbsolutePath();
        List<Integer> inputValues = fileInput.readInts(absolutePath.toString());
        if (inputValues == null) {
            return;
        }

        calculateFinalFrequency(startingFrequency, inputValues);
        findDuplicateFrequency(startingFrequency, inputValues);

        return;
    }
}
