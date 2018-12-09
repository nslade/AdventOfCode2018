package day4;

import common.fileInput;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class part2 {

    public static void findLongestSleep(List<GuardSleepPattern> guardPatterns) {
        int timesAsleep = 0;
        int guardId = 0;
        int minuteAsleep = 0;
        GuardSleepPattern currentPattern;
        for (int i = 0; i < guardPatterns.size(); i++) {
            currentPattern = guardPatterns.get(i);
            if (currentPattern.timesAsleep > timesAsleep) {
                timesAsleep = currentPattern.timesAsleep;
                guardId = currentPattern.guardId;
                minuteAsleep = currentPattern.mostAsleep;
            }
        }
        System.out.println("Guard id: " + guardId + " minute: " + minuteAsleep);
        System.out.println("Answer: " + guardId * minuteAsleep);
    }

    public static List<GuardSleepPattern> generateSleepPattern(Record[] records) {
        List<Integer> guardIds = new ArrayList<>();
        List<GuardSleepPattern> guardPatterns = new ArrayList<>();
        int startMinute = 0;
        int endMinute;
        int currentId = 0;
        for (int i = 0; i < records.length; i++) {
            Record currentRecord = records[i];
            if (!guardIds.contains(currentRecord.guardId)) {
                guardIds.add(currentRecord.guardId);
                guardPatterns.add(new GuardSleepPattern(currentRecord.guardId));
            }
        }

        for (int i = 0; i < records.length; i++) {
            Record currentRecord = records[i];
            if (currentRecord.type == RecordType.BEGINSHIFT) {
                currentId = currentRecord.guardId;
            } else if (currentRecord.type == RecordType.FALLASLEEP) {
                startMinute = currentRecord.date.getMinute();
            } else if (currentRecord.type == RecordType.WAKEUP) {
                endMinute = currentRecord.date.getMinute();
                guardPatterns.get(guardIds.indexOf(currentId)).setSleep(startMinute, endMinute);
            }
        }

        for (int i = 0; i < guardPatterns.size(); i++) {
            guardPatterns.get(i).calculateSleepTime();
        }
        return guardPatterns;
    }

    public static Record[] setIds(Record[] records) {
        int recordIndex = 0;
        int currentId = -1;
        Record currentRecord;
        while (recordIndex < records.length) {
            currentRecord = records[recordIndex];
            if (currentRecord.type == RecordType.BEGINSHIFT) {
                currentId = currentRecord.guardId;
            } else {
                records[recordIndex].guardId = currentId;
            }
            recordIndex++;
        }
        return records;
    }

    public static Record[] sortRecords(Record[] records) {
        Record temp;
        for (int i = 1; i < records.length; i++) {
            for (int j = i; j > 0; j--) {
                if(records[j].date.isBefore(records[j-1].date)) {
                    temp = records[j];
                    records[j] = records[j - 1];
                    records[j - 1] = temp;
                }
            }
        }
        return records;
    }

    public static Record[] getRecords(List<String> input) {
        Record[] records = new Record[input.size()];
        for (int i = 0; i < input.size(); i++) {
            records[i] = new Record(input.get(i));
        }
        return records;
    }

    public static void main(String []args)
    {
        Path relativePath = Paths.get("inputs/day4.txt");
        Path absolutePath = relativePath.toAbsolutePath();
        List<String> inputValues = fileInput.readLines(absolutePath.toString());
        if (inputValues == null) {
            return;
        }
        Record[] records = getRecords(inputValues);
        records = sortRecords(records);

        records = setIds(records);

        List<GuardSleepPattern> guardPatterns = generateSleepPattern(records);

        findLongestSleep(guardPatterns);

        return;
    }
}
