package day2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import common.fileInput;

public class part1 {

    public static void calculateChecksum(List<String> ids) {
        PatternMatches totalMatchCount = new PatternMatches();
        for (int i = 0; i < ids.size(); i++) {
            LetterCount currentCount = new LetterCount();
            String currentId = ids.get(i);
            for (int j = 0; j < currentId.length(); j++) {
                currentCount.countLetter(currentId.charAt(j));

            }
            currentCount.findPatterns();
            if (currentCount.twoFound) {
                totalMatchCount.incrementTwo();
            }
            if (currentCount.threeFound) {
                totalMatchCount.incrementThree();
            }
        }
        System.out.println("Total match count: two: " + totalMatchCount.getTwo() + " three: " + totalMatchCount.getThree());
        System.out.println("Checksum is " + (totalMatchCount.getTwo() * totalMatchCount.getThree()));
        return;
    }

    public static void main(String []args) {

        Path relativePath = Paths.get("inputs/day2.txt");
        Path absolutePath = relativePath.toAbsolutePath();
        List<String> inputValues = fileInput.readLines(absolutePath.toString());
        if (inputValues == null) {
            System.out.println("Error reading input");
            return;
        }
        calculateChecksum(inputValues);
        return;
    }
}
