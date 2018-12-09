package day6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coord {
    private int x;
    private int y;
    private char label;

    public Coord(int x, int y, char label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }

    public Coord(String strCoord, char label) {
        Pattern ptn = Pattern.compile("([0-9]*)(\\,)(\\ )([0-9]*)");
        Matcher matcher = ptn.matcher(strCoord);
        while (matcher.find()) {
            this.x = Integer.parseInt(matcher.group(1));
            this.y = Integer.parseInt(matcher.group(4));
        }
        this.label = label;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public char getLabel() {
        return this.label;
    }
}
