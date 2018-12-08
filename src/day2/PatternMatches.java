package day2;

public class PatternMatches {
    public int two;
    public int three;

    public PatternMatches() {
        two = 0;
        three = 0;
    }

    public int getTwo() {
        return this.two;
    }

    public int getThree() {
        return this.three;
    }

    public void incrementTwo() {
        this.two++;
    }

    public void incrementThree() {
        this.three++;
    }

    public void incrementTwo(int count) {
        this.two += count;
    }

    public void incrementThree(int count) {
        this.three += count;
    }

}