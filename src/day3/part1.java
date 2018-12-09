package day3;

import common.fileInput;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class part1 {
    private static int fabricWidth = 1000;
    private static int fabricHeight = 1000;

    public static int findOverlaps(List<List<Integer>> fabric) {
        int totalOverlaps = 0;
        for (int i = 0; i < fabricHeight; i++) {
            for (int j = 0; j < fabricWidth; j++) {
                if (fabric.get(i).get(j) > 1) {
                    totalOverlaps++;
                }
            }
        }
        return totalOverlaps;
    }

    public static List<List<Integer>> generateFabric(List<Claim> claims) {
        List<List<Integer>> fabric = initialiseFabric();
        for (int i = 0; i < claims.size(); i++) {
            Claim currentClaim = claims.get(i);
            int endWidth = currentClaim.getLeftEdge() + currentClaim.getWidth();
            int endHeight = currentClaim.getTopEdge() + currentClaim.getHeight();
            if (endWidth > fabricWidth || endHeight > fabricHeight) {
                fabric = growFabric(fabric, endHeight, endWidth);
            }
            for (int x = currentClaim.getLeftEdge(); x < endWidth; x++) {
                for (int y = currentClaim.getTopEdge(); y < endHeight; y++) {
                    fabric.get(x).set(y, fabric.get(x).get(y) + 1);
                }
            }
        }
        return fabric;
    }

    public static List<List<Integer>> growFabric(List<List<Integer>> fabric, int rows, int cols) {
        if (fabricHeight < rows) {
            for (int i = fabricHeight; i < rows; i++) {
                fabric.add(i, new ArrayList<>(Collections.nCopies(fabricWidth, 0)));
            }
            fabricHeight = rows;
        }
        if (fabricWidth < cols) {
            int colsToAdd = cols - fabricWidth;
            for (int i = 0; i < fabricHeight; i++) {
                for (int j = 0; j < colsToAdd; j++) {
                    fabric.get(i).add(0);
                }
            }
            fabricWidth = cols;
        }
        return fabric;
    }

    public static List<List<Integer>> initialiseFabric() {
        List<List<Integer>> rows = new ArrayList<List<Integer>>(fabricHeight);
        for ( int i = 0; i < fabricHeight; ++i )
        {
            rows.add( i, new ArrayList<>(Collections.nCopies(fabricWidth, 0)) );
        }
        return rows;

    }

    public static List<Claim> generateClaims(List<String> input) {
        List<Claim> claims = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            Claim currentClaim = new Claim(input.get(i));
            claims.add(currentClaim);
        }
        return claims;
    }

    public static void main(String []args)
    {
        Path relativePath = Paths.get("inputs/day3.txt");
        Path absolutePath = relativePath.toAbsolutePath();
        List<String> inputValues = fileInput.readLines(absolutePath.toString());
        if (inputValues == null) {
            return;
        }
        List<Claim> claims = generateClaims(inputValues);

        List<List<Integer>> fabric = generateFabric(claims);
        int getOverlaps = findOverlaps(fabric);

        System.out.println(getOverlaps);

        return;
    }
}
