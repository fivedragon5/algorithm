package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * TODO: 20230106 포인터를 통해 어느 시점 이상일경우 곰팡이의 수를 누적하고 중요도 부분은 탐색? 을 사용해야 할꺼 같음.
 *
 * 제한)
 * 1 <= N <= 200,000
 * 1 <= G <= 10^9
 * 0 <= K <= N
 * 1 <= Si <= 10^9
 * 1 <= Li <= 10^9
 * O = {0,1}
 */
class Problem24041 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //세균 수 : S(i) * max(1, x - L(i))
        int[][] items = new int[N][3];
//        ArrayList<int[]> items = new ArrayList<>();
        // 부패속도, 유통기한, 중요도
        int S = 0;
        int L = 0;
        int O = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            O = Integer.parseInt(st.nextToken());
            items[i][0] = S;
            items[i][1] = L;
            items[i][2] = O;
        }


        for (int[] item : items) {
            System.out.println(Arrays.toString(item));
        }

        Arrays.sort(items, Comparator.comparingInt(i -> i[1]));
        
        System.out.println("정렬");
        for (int[] item : items) {
            System.out.println(Arrays.toString(item));
        }
        // 9 -> 3
        // 10 -> 6
        // 11 -> 16
        // 12 -> 26
    }
}
// L + 36 / 2
/*
4 36 0
2 14 1
3 8 1
5 12 1
7 10 0

12

4 36 1
2 14 1
3 8 1
5 12 1
7 10 0

13

4 36 2
2 14 1
3 8 1
5 12 1
7 10 0

14
 */

