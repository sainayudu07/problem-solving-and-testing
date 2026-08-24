import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        // Number of lines
        int n = Integer.parseInt(br.readLine().trim());

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        // Read the n lines
        for (int i = 0; i < n; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());

            ArrayList<Integer> row = new ArrayList<>();

            for (int j = 0; j < d; j++) {
                row.add(Integer.parseInt(st.nextToken()));
            }

            list.add(row);
        }

        // Number of queries
        int q = Integer.parseInt(br.readLine().trim());

        StringBuilder output = new StringBuilder();

        // Process queries
        for (int i = 0; i < q; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // x and y are 1-based
            if (x >= 1 && x <= list.size()
                    && y >= 1 && y <= list.get(x - 1).size()) {

                output.append(list.get(x - 1).get(y - 1));
            } else {
                output.append("ERROR!");
            }

            output.append('\n');
        }

        System.out.print(output);
    }
}