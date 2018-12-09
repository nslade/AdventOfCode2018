package day4;

import java.util.List;

public class GuardSleepPattern {
    int guardId;
    int[] sleepPattern;
    int totalSleep;
    int mostAsleep;
    int timesAsleep;

    public GuardSleepPattern(int id){
        this.guardId = id;
        sleepPattern = new int[60];
        totalSleep = 0;
        mostAsleep = 0;
        timesAsleep = 0;
    }

    public void setSleep(int startMinute, int endMinute) {
        for (int m = startMinute; m < endMinute; m++) {
            sleepPattern[m] += 1;
        }
    }

    public void calculateSleepTime() {
        for (int i = 0; i < sleepPattern.length; i++) {
            if (sleepPattern[i] > timesAsleep) {
                timesAsleep = sleepPattern[i];
                mostAsleep = i;
            }
            totalSleep += sleepPattern[i];
        }
    }
}
