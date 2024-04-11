package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 풀이)
 *
 * 제한)
 *  1 ≤ N ≤ 10,000
 *  1 ≤ M ≤ 10
 */
class Problem23843 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Integer[] devices = new Integer[N];

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            devices[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(devices, (device1, device2) -> device2 - device1);

        int temp = 0;

        for (int i = 0; i < N; i++) {
            if (queue.size() < M) {
                queue.add(devices[i]);
            }
            else {
                temp = queue.poll();
                queue.add(temp + devices[i]);
            }
        }

        for (int q : queue) {
            temp = Math.max(temp, q);
        }

        System.out.println(temp);
    }
}
/*
5 2
1 4 4 8 1

9

5 1
1 1 1 1 1
 */

