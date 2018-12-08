package day1;

import common.fileInput;

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

    public static void main(String []args)
    {
        int startingFrequency = 0;
        Path relativePath = Paths.get("inputs/day1.txt");
        Path absolutePath = relativePath.toAbsolutePath();
        List<Integer> inputValues = fileInput.readInts(absolutePath.toString());
        if (inputValues == null) {
            return;
        }

        int finalFrequency = calculateFrequency(startingFrequency, inputValues);
        System.out.println(finalFrequency);

        return;
    }
}
