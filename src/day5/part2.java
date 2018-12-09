package day5;

import common.fileInput;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class part2 {

    public static List<Character> reactPolymer(List<Character> polymer) {
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

    public static List<Character> findUnits(String fullPolymer) {
        List<Character> units = new ArrayList<>();
        for (int i = 0; i < fullPolymer.length(); i++) {
            char currentChar = Character.toLowerCase(fullPolymer.charAt(i));
            if (!units.contains(currentChar)) {
                units.add(currentChar);
            }
        }
        return units;
    }

    public static void main(String []args)
    {
        Path relativePath = Paths.get("inputs/day5.txt");
        Path absolutePath = relativePath.toAbsolutePath();
        List<String> inputValues = fileInput.readLines(absolutePath.toString());
        if (inputValues == null) {
            return;
        }
        String fullPolymerString = inputValues.get(0);

        List<Character> fullPolymer = new ArrayList<>();
        for (int j = 0; j < fullPolymerString.length(); j++) {
            fullPolymer.add(fullPolymerString.charAt(j));
        }
        fullPolymer = reactPolymer(fullPolymer);

        int smallestPolymer = fullPolymer.size();

        List<Character> units = findUnits(fullPolymerString);

        for (int i = 0; i < units.size(); i++) {
            String newPolymerString = fullPolymerString;
            newPolymerString = newPolymerString.replace(units.get(i).toString(), "");
            newPolymerString = newPolymerString.replace(units.get(i).toString().toUpperCase(), "");
            List<Character> polymer = new ArrayList<>();
            for (int j = 0; j < newPolymerString.length(); j++) {
                polymer.add(newPolymerString.charAt(j));
            }
            polymer = reactPolymer(polymer);
            if (polymer.size() < smallestPolymer) {
                smallestPolymer = polymer.size();
            }
        }

        System.out.println(smallestPolymer);

        return;
    }
}
