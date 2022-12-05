package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이)
 * 일정을 길이:365 배열로 나열한뒤 일정이 있는 부분을 +1 입력
 * 365배열을 순회하면서 0을 만났을경우 연속된 일정이 끝난것으로 판별한뒤 넓이 계산
 *
 * 제한)
 * (1 ≤ N ≤ 1000)
 * (1 ≤ S ≤ E ≤ 365)
 */

class Problem20207 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] calendar = new int[366];
        int start = 0;
        int end = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            for (int j = start-1; j <= end-1; j++) {
                calendar[j] = calendar[j] + 1;
            }
        }

        int width = 0;
        int max = 0;
        int number = 0;
        start = 0;

        for (int i = 0; i < 366; i++) {
            number = calendar[i];
            if (max > 0 && number == 0) {
                width += (i - start) * max;
                max = 0;
                start = 0;
            }
            else if (number > 0) {
                if (max == 0) start = i;
                max = Math.max(max, number);
            }
        }
        System.out.println(width);
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

1
1 365
 */

