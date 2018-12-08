package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class fileInput {

    public static List<Integer> readInts(String filepath) {

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

    public static List<String> readStrings(String filepath) {

        List<String> inputValues = new ArrayList<>();

        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                if (scanner.hasNextLine()) {
                    inputValues.add(scanner.nextLine());
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

}
