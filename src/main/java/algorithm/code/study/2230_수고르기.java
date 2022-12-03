package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  투포인터로 알고리즘 사용
 *  1.주어진 번호를 오름차순으로 정렬
 *  2.right:1, left:0 부터 시작하여 numbers[right] - numbers[left]로 차를 구함 
 *  3.두수의 차와 주어진 M을 비교하여 M보다 클경우 left증가, M보다 작을경우 right증가하여 최소값을 갱신해줌
 *
 * 제한)
 *     1 ≤ N ≤ 100,000
 *     0 ≤ M ≤ 2,000,000,000
 *     0 ≤ |A[i]| ≤ 1,000,000,000
 */

class Problem2230 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        if (M == 0) {
            System.out.println(0);
            return;
        }

        long[] numbers = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numbers[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(numbers);

        long min = 2000000000;
        long calc = 0;
        int left = 0;
        int right = 1;

        while (right < N) {
            calc = numbers[right] - numbers[left];
            if (calc > M) {
                left++;
                min = Math.min(min, calc);
            }
            else if (calc < M || left == right) {
                right++;
            }
            else if (calc == M) {
                min = calc;
                break;
            }
        }
        System.out.println(min);
    }
}
/*
3 3
1
5
3

4
 */

