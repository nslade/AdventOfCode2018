package day4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


enum RecordType {
    BEGINSHIFT, FALLASLEEP, WAKEUP
}

public class Record {
    LocalDateTime date;
    RecordType type;
    int guardId;

    public Record(String recordDetails) {
        Pattern ptn = Pattern.compile("(\\[)([0-9]*-[0-9]*-[0-9]* [0-9]*:[0-9]*)(\\] )([a-zA-Z0-9# ]*)");
        Matcher matcher = ptn.matcher(recordDetails);
        String action = "";
        while (matcher.find()) {
            action = matcher.group(4);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.date = LocalDateTime.parse(matcher.group(2), formatter);
        }
        String[] parts = action.split(" ");
        if (parts[0].equals("Guard")) {
            type = RecordType.BEGINSHIFT;
            guardId = Integer.parseInt(parts[1].substring(1));
        } else if (parts[0].equals("falls")) {
            type = RecordType.FALLASLEEP;
        } else if (parts[0].equals("wakes")) {
            type = RecordType.WAKEUP;
        }

    }
}
