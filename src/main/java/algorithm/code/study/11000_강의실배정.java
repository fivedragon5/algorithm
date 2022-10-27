package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Problem11000 {

    /**
     * 1 <= N <= 2,000,000
     * 0 <= S < T <= 1,000,000,000
     */

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] list = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list[i][0] = Integer.parseInt(st.nextToken());
            list[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list, (m1, m2) -> {
            if (m1[0] == m2[0]) {
                return m1[1] - m2[1];
            }
            return m1[0] - m2[0];
        });

        solve2(list);


//        for(int[] a : list) {
//            System.out.println(Arrays.toString(a));
//        }

    }

    public static void solve2(int[][] list) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int room = 0;

        queue.add(list[0][1]);

        for (int i = 1; i < list.length; i++) {
            while (!queue.isEmpty() && queue.peek() <= list[i][0]) {
                queue.poll();
            }
            queue.add(list[i][1]);
            room = queue.size() > room ? queue.size() : room;
        }

        System.out.println(room);
    }
}

/**
 3
 3 5
 1 3
 2 4
 */
