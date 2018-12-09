package common;

import java.io.*;
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

    public static List<String> readLines(String filepath) {

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

    public static List<Character> readChars(String filepath) {

        List<Character> inputValues = new ArrayList<>();

        try {
            InputStream in = new FileInputStream(filepath);
            Reader r = new InputStreamReader(in, "US-ASCII");
            int curChar;
            while ((curChar = r.read()) != -1) {
                inputValues.add((char) curChar);
            }
            return inputValues;
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        } catch (UnsupportedEncodingException usee) {
            System.out.println("Unsupported encoding");
        } catch (IOException ioe) {
            System.out.println("IO excpetion");
        }
        return null;
    }
}
