package day3;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Claim {
    private int id;
    private int leftEdge;
    private int topEdge;
    private int width;
    private int height;
    private boolean isOverlapped;

    public Claim(String claimDetails) {
        // claim details: #id @ leftEdge, topEdge: widthxheight
        Pattern ptn = Pattern.compile("(\\#)([0-9]*)(\\ @ )([0-9]*)(\\,)([0-9]*)(\\: )([0-9]*)([a-z])([0-9]*)");
        Matcher matcher = ptn.matcher(claimDetails);
        while(matcher.find())
        {
            this.id = Integer.parseInt(matcher.group(2));
            this.leftEdge = Integer.parseInt(matcher.group(4));
            this.topEdge = Integer.parseInt(matcher.group(6));
            this.width = Integer.parseInt(matcher.group(8));
            this.height = Integer.parseInt(matcher.group(10));
        }
        this.isOverlapped = false;
    }

    public int getId() {
        return this.id;
    }

    public int getLeftEdge() {
        return this.leftEdge;
    }

    public int getTopEdge() {
        return this.topEdge;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setOverlapped() {
        this.isOverlapped = true;
    }

    public boolean isOverlapped() {
        return this.isOverlapped;
    }
}
