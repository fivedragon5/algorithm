package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 제한)
 * N%2 == 0
 * 2 <= N <= 200,000
 * 2 <= H <= 500,000
 */
class Problem3020 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] crack = new int[H];
        int height = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            height = Integer.parseInt(st.nextToken());
            if (i % 2 == 0) {
                for (int j = 0; j < height; j++) {
                    crack[j]++;
                }
            }
            else {
                for (int j = H - 1; j >= H - height; j--) {
                    crack[j]++;
                }
            }
        }
        Arrays.sort(crack);
        int min = crack[0];
        int count = 0;
        for (int wall : crack) {
            if (min != wall) {
                break;
            }
            count++;
        }
        System.out.println(min + " " + count);
    }
}
/*
14 5
1
3
4
2
2
4
3
4
3
3
3
2
3
3

6 7
1
5
3
3
5
1

2 3

14 5
1
3
4
2
2
4
3
4
3
3
3
2
3
3

7 2
 */

