package day5;

import common.fileInput;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class part1 {

    public static List<Character> removeDuplicates(List<Character> polymer) {
        int index = 0;
        while (index < polymer.size() - 1) {
            if (polymer.get(index).toString().equalsIgnoreCase(polymer.get(index + 1).toString())) {
                if (!polymer.get(index).toString().equals(polymer.get(index + 1).toString())) {
                    polymer.remove(index);
                    polymer.remove(index);
                    if (index > 0) {
                        index--;
                    }
                }
                else {
                    index++;
                }
            } else {
                index++;
            }
        }
        return polymer;
    }

    public static void main(String []args)
    {
        Path relativePath = Paths.get("inputs/day5.txt");
        Path absolutePath = relativePath.toAbsolutePath();
        List<Character> inputValues = fileInput.readChars(absolutePath.toString());
        if (inputValues == null) {
            return;
        }
        List<Character> polymer = removeDuplicates(inputValues);

        System.out.println(polymer);
        System.out.println(polymer.size());

        return;
    }
}
