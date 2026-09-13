import java.io.*;
import java.util.*;

class Result {

    public static void matrixRotation(List<List<Integer>> matrix, int r) {

        int m = matrix.size();
        int n = matrix.get(0).size();

        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {

            int top = layer;
            int bottom = m - 1 - layer;
            int left = layer;
            int right = n - 1 - layer;

            List<Integer> elements = new ArrayList<>();

            // Top row
            for (int j = left; j <= right; j++) {
                elements.add(matrix.get(top).get(j));
            }

            // Right column
            for (int i = top + 1; i <= bottom; i++) {
                elements.add(matrix.get(i).get(right));
            }

            // Bottom row
            for (int j = right - 1; j >= left; j--) {
                elements.add(matrix.get(bottom).get(j));
            }

            // Left column
            for (int i = bottom - 1; i > top; i--) {
                elements.add(matrix.get(i).get(left));
            }

            // Rotate anti-clockwise
            int rotation = r % elements.size();

            List<Integer> rotated = new ArrayList<>();

            for (int i = rotation; i < elements.size(); i++) {
                rotated.add(elements.get(i));
            }

            for (int i = 0; i < rotation; i++) {
                rotated.add(elements.get(i));
            }

            int index = 0;

            // Put values back into top row
            for (int j = left; j <= right; j++) {
                matrix.get(top).set(j, rotated.get(index++));
            }

            // Put values back into right column
            for (int i = top + 1; i <= bottom; i++) {
                matrix.get(i).set(right, rotated.get(index++));
            }

            // Put values back into bottom row
            for (int j = right - 1; j >= left; j--) {
                matrix.get(bottom).set(j, rotated.get(index++));
            }

            // Put values back into left column
            for (int i = bottom - 1; i > top; i--) {
                matrix.get(i).set(left, rotated.get(index++));
            }
        }

        // Print result
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    System.out.print(" ");
                }

                System.out.print(matrix.get(i).get(j));
            }

            System.out.println();
        }
    }
}

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

        String[] first =
            br.readLine().trim().split("\\s+");

        int m = Integer.parseInt(first[0]);
        int n = Integer.parseInt(first[1]);
        int r = Integer.parseInt(first[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        for (int i = 0; i < m; i++) {

            String[] values =
                br.readLine().trim().split("\\s+");

            List<Integer> row = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                row.add(Integer.parseInt(values[j]));
            }

            matrix.add(row);
        }

        Result.matrixRotation(matrix, r);

        br.close();
    }
}
