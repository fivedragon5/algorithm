package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
  예제)
     3
     10
     20
     40

    3
    30
    70
    20

    1
    10
 */
class Problem1715 {

    public static void main(String args[]) throws IOException {
        /**
         *
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            queue.add(Integer.parseInt(st.nextToken()));
        }

        int sum = 0;

        while (queue.size() > 1) {
            int min_1 = queue.poll();
            int min_2 = queue.poll();

            int plusMin = min_1 + min_2;

            sum += plusMin;

            queue.add(plusMin);
        }

        System.out.println(sum);
    }
}
