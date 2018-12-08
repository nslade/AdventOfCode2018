package day2;

import java.util.ArrayList;
import java.util.List;


public class LetterCount {
    public List<Character> letters;
    public List<Integer> count;
    public boolean twoFound;
    public boolean threeFound;

    public LetterCount() {
        letters = new ArrayList<>();
        count = new ArrayList<>();
        twoFound = false;
        threeFound = false;
    }

    public void countLetter(char curLetter) {
        if (letters.contains(curLetter)) {
            increaseCount(letters.indexOf(curLetter));
        } else {
            letters.add(curLetter);
            count.add(1);
        }
    }

    public void increaseCount(int pos) {
        count.set(pos, count.get(pos) + 1);
    }

    public void findPatterns() {

        int i = 0;
        while (i < count.size() && (!twoFound || !threeFound)) {
            int curCount = count.get(i);
            if (curCount == 2 && !twoFound) {
                twoFound = true;
            } else if (curCount == 3 && !threeFound) {
                threeFound = true;
            }
            i++;
        }
    }
}
