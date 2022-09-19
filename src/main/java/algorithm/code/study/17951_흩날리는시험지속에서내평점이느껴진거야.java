package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
  예제)
    8 2
    12 7 19 20 17 14 9 10
 */
class Problem17951 {

    public static void main(String args[]) throws IOException {
        /**
         *
         * K의 그룹으로 나눔
         * 그룹에서 맞은 문제 개수의 합중 최솟값을 시험 점수로 함
         * 
         * 현수가 이번 시험에서 받을 수 있는 최대 점수를 계산
         * 
         * 1 <= K <= N <= 10^5 | 100,000
         * 0 <= x <= 20
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int total = 0;

        int[] papers = new int[N];

        for (int i = 0; i < N; i++) {
            papers[i] = Integer.parseInt(st.nextToken());
            total += papers[i];
        }

        int left = 0;
        int right = total;

        while (left <= right) {
            int mid = (left+right)/2;
            int count = 1;
            int sum = 0;
            int min = total;

            for (int i = 0; i < N ; i++) {
                sum += papers[i];

                if (sum >= mid) {
                    count++;
                    min = Math.min(min, sum);
                    sum = 0;
                }
            }

            if (count > K) left = mid + 1;
            else right = mid - 1;

        }
        System.out.println(left - 1);
    }
}
