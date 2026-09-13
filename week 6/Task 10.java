import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'circularPalindromes' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING s as parameter.
     */

    public static List<Integer> circularPalindromes(String s) {
    int n = s.length();
    String t = s + s;
    int m = t.length();

    // Manacher for odd-length palindromes
    int[] odd = new int[m];
    int l = 0, r = -1;

    for (int i = 0; i < m; i++) {
        int k = (i > r) ? 1 : Math.min(odd[l + r - i], r - i + 1);

        while (i - k >= 0 && i + k < m &&
               t.charAt(i - k) == t.charAt(i + k)) {
            k++;
        }

        odd[i] = k - 1;

        if (i + odd[i] > r) {
            l = i - odd[i];
            r = i + odd[i];
        }
    }

    // Manacher for even-length palindromes
    int[] even = new int[m];
    l = 0;
    r = -1;

    for (int i = 0; i < m; i++) {
        int k = (i > r) ? 0 : Math.min(even[l + r - i + 1], r - i + 1);

        while (i - k - 1 >= 0 && i + k < m &&
               t.charAt(i - k - 1) == t.charAt(i + k)) {
            k++;
        }

        even[i] = k;

        if (i + even[i] - 1 > r) {
            l = i - even[i];
            r = i + even[i] - 1;
        }
    }

    // Sparse tables for range maximum queries
    int[][] oddTable = buildSparseTable(odd);
    int[][] evenTable = buildSparseTable(even);

    List<Integer> result = new ArrayList<>();

    for (int start = 0; start < n; start++) {
        int end = start + n - 1;

        int bestOdd = getBestOdd(start, end, odd, oddTable);
        int bestEven = getBestEven(start, end, even, evenTable);

        result.add(Math.max(bestOdd, bestEven));
    }

    return result;
}

private static int getBestOdd(int start, int end,
                              int[] odd, int[][] table) {

    int mid = (start + end) / 2;
    int best = 1;

    // Centers on the left side
    int maxX = mid - start;

    if (maxX >= 0) {
        int low = 0, high = maxX;

        while (low <= high) {
            int x = (low + high) / 2;

            int left = start + x;
            int maxRadius = rangeMax(table, left, mid);

            if (maxRadius >= x) {
                best = Math.max(best, x);
                low = x + 1;
            } else {
                high = x - 1;
            }
        }
    }

    // Centers on the right side
    int rightStart = mid + 1;
    int maxRightX = end - rightStart;

    if (maxRightX >= 0) {
        int low = 0, high = maxRightX;

        while (low <= high) {
            int x = (low + high) / 2;

            int right = end - x;
            int maxRadius = rangeMax(table, rightStart, right);

            if (maxRadius >= x) {
                best = Math.max(best, x);
                low = x + 1;
            } else {
                high = x - 1;
            }
        }
    }

    return 2 * best + 1;
}

private static int getBestEven(int start, int end,
                               int[] even, int[][] table) {

    int mid = (start + end + 1) / 2;
    int best = 0;

    // Centers on the left side
    int maxX = mid - start;

    if (maxX >= 0) {
        int low = 0, high = maxX;

        while (low <= high) {
            int x = (low + high) / 2;

            int left = start + x;
            int maxRadius = rangeMax(table, left, mid);

            if (maxRadius >= x) {
                best = Math.max(best, x);
                low = x + 1;
            } else {
                high = x - 1;
            }
        }
    }

    // Centers on the right side
    int rightStart = mid + 1;
    int maxRightX = end + 1 - rightStart;

    if (maxRightX >= 0) {
        int low = 0, high = maxRightX;

        while (low <= high) {
            int x = (low + high) / 2;

            int right = end + 1 - x;
            int maxRadius = rangeMax(table, rightStart, right);

            if (maxRadius >= x) {
                best = Math.max(best, x);
                low = x + 1;
            } else {
                high = x - 1;
            }
        }
    }

    return 2 * best;
}

private static int[][] buildSparseTable(int[] arr) {
    int n = arr.length;
    int log = 1;

    while ((1 << log) <= n) {
        log++;
    }

    int[][] table = new int[log][n];

    for (int i = 0; i < n; i++) {
        table[0][i] = arr[i];
    }

    for (int j = 1; j < log; j++) {
        int len = 1 << j;
        int half = len >> 1;

        for (int i = 0; i + len <= n; i++) {
            table[j][i] =
                Math.max(table[j - 1][i],
                         table[j - 1][i + half]);
        }
    }

    return table;
}

private static int rangeMax(int[][] table, int left, int right) {
    if (left > right) {
        return 0;
    }

    int length = right - left + 1;
    int log = 31 - Integer.numberOfLeadingZeros(length);

    return Math.max(
        table[log][left],
        table[log][right - (1 << log) + 1]
    );
}

    }



public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        List<Integer> result = Result.circularPalindromes(s);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
