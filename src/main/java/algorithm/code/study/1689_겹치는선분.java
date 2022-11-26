package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1 <= N <= 1000000 선분의 갯수
 * -100,000,000 <= s < e <= 100,000,000
 * s < e
 *
 * 선분의 끝 점에서 겹치는 것은 겹치는 것으로 세지 않는다.
 */

class Problem1689 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] lines = new int[N][2];

        int answer = 1;
        int stack = 1;
        int start = 0;
        int end = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(lines, (line1, line2) -> {
            if(line1[0] == line2[0]) {
                return line1[1] - line2[1];
            }
            return line1[0] - line2[0];
        });

        for (int[] a : lines) {
            System.out.println(Arrays.toString(a));
        }

        int maxStart = lines[0][0];
        int minEnd = lines[0][1];

        for (int i = 1; i < N; i++) {
            start = lines[i][0];
            end = lines[i][1];

            if (minEnd > start) {
                stack++;
                answer = Math.max(answer, stack);
            }
            else {
                stack--;
            }
        }
    }
}
/*
11
1 2
3 6
7 8
10 11
13 16
0 5
5 6
2 5
6 10
9 14
12 15

3

1
-1 1

 */

