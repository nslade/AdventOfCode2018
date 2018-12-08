package day2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import common.fileInput;

enum State {
    SAME, DIFFERENCE, FAIL, FOUND, NOMATCHES
}

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

    public static void findBoxes(List<String> ids) {
        State state = State.SAME;
        int idIndex = 0;
        String currentId = ids.get(idIndex);
        int compareIdIndex = idIndex + 1;
        String compareId = ids.get(compareIdIndex);
        int charIndex = 0;
        int differenceIndex = 0;
        while (state != State.FOUND && state != State.NOMATCHES) {
            switch (state) {
                case SAME:
                    if (charIndex == compareId.length() - 1) {
                        if (currentId.charAt(charIndex) == compareId.charAt(charIndex)) {
                            state = State.FAIL;
                        } else {
                            differenceIndex = charIndex;
                            state = State.FOUND;
                        }
                    } else if (currentId.charAt(charIndex) != compareId.charAt(charIndex)) {
                        differenceIndex = charIndex;
                        state = State.DIFFERENCE;
                    }
                    charIndex++;
                    break;
                case DIFFERENCE:
                    if (charIndex == compareId.length() - 1) {
                        if (currentId.charAt(charIndex) == compareId.charAt(charIndex)) {
                            state = State.FOUND;
                        } else {
                            state = State.FAIL;
                        }
                    } else if (currentId.charAt(charIndex) != compareId.charAt(charIndex)) {
                        state = State.FAIL;
                    }
                    charIndex++;
                    break;
                case FAIL:
                    if (compareIdIndex < ids.size() - 1) {
                        compareIdIndex++;
                        compareId = ids.get(compareIdIndex);
                        charIndex = 0;
                        state = State.SAME;
                    } else if (idIndex < ids.size() - 2) {
                        idIndex++;
                        currentId = ids.get(idIndex);
                        compareIdIndex = idIndex + 1;
                        compareId = ids.get(compareIdIndex);
                        charIndex = 0;
                        state = State.SAME;
                    } else {
                        state = State.NOMATCHES;
                    }
                    break;
                default:
                    break;
            }
        }
        if (state == State.FOUND) {
            String boxId = currentId.substring(0, differenceIndex) + currentId.substring(differenceIndex + 1, currentId.length());
            System.out.println("Box id: " + boxId);
        } else if (state == State.NOMATCHES) {
            System.out.println("No matches");
        }
    }

    public static void main(String []args) {

        Path relativePath = Paths.get("inputs/day2.txt");
        Path absolutePath = relativePath.toAbsolutePath();
        List<String> inputValues = fileInput.readStrings(absolutePath.toString());
        if (inputValues == null) {
            System.out.println("Error reading input");
            return;
        }
        //calculateChecksum(inputValues);
        findBoxes(inputValues);
        return;
    }
}
