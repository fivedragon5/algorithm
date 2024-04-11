package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 3 10
 2 5
 4 10
 1 3

 4

 1 1
 5 10


 */
class Problem14575 {

    public static void main(String args[]) throws IOException {
        /**
          L R
         모든 사람 i가 Li이상 Ri이하의 술을 받으면서,
         모든 사람이 받은 술의 총합이 정확히 T가 되고,
         어떤 사람도 S를 초과하는 술은 받지 않게 할 수 있는,

        조건)
         1 ≤ N ≤ 1000, 1 ≤ T ≤ 10^9  1,000,000,000
         1 ≤ Li ≤ Ri ≤ 10^6
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int sum_max = 0;
        int sum_min = 0;
        int alcohol_min = 0;
        int alcohol_max = 0;

        int[][] people = new int[N][2];

        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());
            people[i][0] = Integer.parseInt(st.nextToken());
            people[i][1] = Integer.parseInt(st.nextToken());
            sum_min += people[i][0];
            sum_max += people[i][1];
            if (alcohol_min < people[i][0]) alcohol_min = people[i][0];
            if (alcohol_max < people[i][1]) alcohol_max = people[i][1];
        }

        if (sum_max < T || sum_min > T) {
            System.out.println(-1);
            return;
        }

        int middle = 0;

        while(alcohol_min <= alcohol_max) {

            middle = (alcohol_min+alcohol_max)/2;
            int alcohol_total = T;
            int more = 0;

            for (int p = 0; p < N; p++) {
                alcohol_total -= people[p][0];
                more += Math.min(middle - people[p][0], people[p][1]-people[p][0]);
            }

            if (more >= alcohol_total) {
                alcohol_max = middle - 1;
            }
            else {
                alcohol_min = middle + 1;
            }


        }

        System.out.println(middle);
    }
}
