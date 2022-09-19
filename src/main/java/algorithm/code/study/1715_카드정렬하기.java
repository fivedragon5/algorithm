package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
  예제)
     3
     10
     20
     40
 */
class Problem1715 {

    public static void main(String args[]) throws IOException {
        /**
         *
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] cards = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        int sum = cards[0];

        for (int i = 1; i < cards.length; i++) {
            sum += cards[i];
        }
    }
}
