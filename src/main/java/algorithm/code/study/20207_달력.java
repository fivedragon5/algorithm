package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 *
 *
 * (1 ≤ N ≤ 1000)
 * (1 ≤ S ≤ E ≤ 365)
 */

class Problem20207 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] days = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            days[i][0] = Integer.parseInt(st.nextToken());
            days[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(days, (day1, day2) -> {
            if (day1[0] == day2[0]) {
                return day2[1] - day1[1];
            }
            return day1[0] - day2[0];
        });

        for (int[] d : days) {
            System.out.println(Arrays.toString(d));
        }
    }
}
/*
7
2 4
4 5
5 6
5 7
7 9
11 12
12 12

28

5
1 9
8 9
4 6
3 4
2 5

36
 */

