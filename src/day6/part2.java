package day6;

import common.fileInput;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class part2 {

    private static int xMax;
    private static int yMax;
    private static int xLength;
    private static int yLength;
    private static List<List<Character>> grid;
    private static char maxLabel;

    public static void fillGrid(Coord[] coords) {
        Coord currentCoord;
        for (int i = 0; i < coords.length; i++) {
            currentCoord = coords[i];
            if (currentCoord.getX() > xMax || currentCoord.getY() > yMax) {
                growGrid(currentCoord.getX(), currentCoord.getY());
            }
            grid.get(currentCoord.getY()).set(currentCoord.getX(), currentCoord.getLabel());
        }
    }

    public static void printGrid() {
        for (int i = 0; i < grid.size(); i++) {
            System.out.println(grid.get(i));
        }
    }

    public static void initialiseGrid() {
        yMax = 0;
        xMax = 0;
        xLength = 1;
        yLength = 1;
        grid = new ArrayList<>(yLength);
        for ( int i = 0; i < yLength; ++i )
        {
            grid.add( i, new ArrayList<>(Collections.nCopies(xLength, '.')) );
        }
    }

    private static void growGrid(int xPos, int yPos) {
        int rows = yPos + 1;
        int cols = xPos + 1;
        if (yLength < rows) {
            for (int i = yLength; i < rows; i++) {
                grid.add(i, new ArrayList<>(Collections.nCopies(xLength, '.')));
            }
            yMax = yPos;
            yLength = rows;
        }
        if (xLength < cols) {
            int colsToAdd = cols - xLength;
            for (int i = 0; i < yLength; i++) {
                for (int j = 0; j < colsToAdd; j++) {
                    grid.get(i).add('.');
                }
            }
            xMax = xPos;
            xLength = cols;
        }
    }

    public static Coord[] getCoords(List<String> input) {
        Coord[] coords = new Coord[input.size()];
        char label = 'A';
        for (int i = 0; i < input.size(); i++) {
            coords[i] = new Coord(input.get(i), label);
            maxLabel = label;
            label++;
        }
        return coords;
    }

    public static void calculateClosest(Coord[] coords) {
        List<Character> currentRow;
        int shortestDistance;
        char closestPoint = ' ';
        int currentDistance;
        boolean multiplePoints;
        int maxDistance = yLength + xLength;
        Coord currentCoord;
        for (int y = 0; y < grid.size(); y++) {
            currentRow = grid.get(y);
            for (int x = 0; x < currentRow.size(); x++) {
                if (currentRow.get(x) == '.') {
                    shortestDistance = maxDistance;
                    multiplePoints = false;
                    for (int p = 0; p < coords.length; p++) {
                        currentCoord = coords[p];
                        currentDistance = abs(currentCoord.getX() - x) + abs(currentCoord.getY() - y);
                        if (currentDistance < shortestDistance) {
                            shortestDistance = currentDistance;
                            closestPoint = currentCoord.getLabel();
                            multiplePoints = false;
                        } else if (currentDistance == shortestDistance) {
                            multiplePoints = true;
                        }
                    }
                    if (multiplePoints) {
                        grid.get(y).set(x, '.');
                    } else {
                        grid.get(y).set(x, closestPoint);
                    }
                }
            }
        }
        printGrid();
    }

    public static int calculateRegion(Coord[] coords) {
        List<Character> currentRow;
        int totalDistance;
        int currentDistance;
        int maxDistance = 10000;
        int regionSize = 0;
        Coord currentCoord;
        for (int y = 0; y < grid.size(); y++) {
            currentRow = grid.get(y);
            for (int x = 0; x < currentRow.size(); x++) {
                totalDistance = 0;
                for (int p = 0; p < coords.length; p++) {
                    currentCoord = coords[p];
                    currentDistance = abs(currentCoord.getX() - x) + abs(currentCoord.getY() - y);
                    totalDistance += currentDistance;
                }
                if (totalDistance < maxDistance) {
                    grid.get(y).set(x, '#');
                    regionSize++;
                }
            }
        }
        return regionSize;
    }

    public static List<Character> removeInfinitePoints() {
        List<Character> points = new ArrayList<>();
        for (char label = 'A'; label <= maxLabel; label++) {
            points.add(label);
        }
        for (int x = 0; x < xLength; x++) {
            if (points.contains(grid.get(0).get(x))) {
                points.remove(grid.get(0).get(x));
            }
            if (points.contains(grid.get(yMax).get(x))) {
                points.remove(grid.get(yMax).get(x));
            }
        }
        for (int y = 0; y < yLength; y++) {
            if (points.contains(grid.get(y).get(0))) {
                points.remove(grid.get(y).get(0));
            }
            if (points.contains(grid.get(y).get(xMax))) {
                points.remove(grid.get(y).get(xMax));
            }
        }
        return points;
    }

    public static int findMaxArea(List<Character> nonInfinitePoints) {
        char currentChar;
        int maxArea = 0;
        int[] pointCount = new int[nonInfinitePoints.size()];
        for (int y = 0; y < yLength; y++) {
            for (int x = 0; x < xLength; x++) {
                currentChar = grid.get(y).get(x);
                if (currentChar != '.' && nonInfinitePoints.contains(currentChar)) {
                    pointCount[nonInfinitePoints.indexOf(currentChar)] += 1;
                }
            }
        }
        for (int i = 0; i < nonInfinitePoints.size(); i++) {
            if (pointCount[i] > maxArea) {
                maxArea = pointCount[i];
            }
        }
        return maxArea;
    }

    public static void main(String []args) {
        Path relativePath = Paths.get("inputs/day6.txt");
        Path absolutePath = relativePath.toAbsolutePath();
        List<String> inputValues = fileInput.readLines(absolutePath.toString());
        if (inputValues == null) {
            return;
        }
        Coord[] coords = getCoords(inputValues);
        initialiseGrid();
        fillGrid(coords);
        int regionSize = calculateRegion(coords);
        System.out.println(regionSize);
        return;
    }
}

