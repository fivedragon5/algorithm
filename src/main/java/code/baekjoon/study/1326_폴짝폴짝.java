package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem1326 {

    static int[] bridge;
    static int N;

    public static void main(String args[]) throws IOException {
        /**
         N(1≤N≤10,000)

         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        bridge = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            bridge[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        int jump = 0;
        int findBridge = to;

        while (from%bridge[findBridge-1] != 0) {

            System.out.println(findBridge);

            int index = findIndex(findBridge);
            if (index == -1) {
                System.out.println(-1);
                return;
            }
            else {
                System.out.println("jump" + index);
                findBridge = index;
                jump++;
            }
        }

        System.out.println(jump+1);

    }

    static int findIndex(int number) {
        int index = -1;
        for (int i = 0; i < N; i++) {
            System.out.println(i+"?");
            if (number%bridge[i] == 0) return i+1;
        }
        return index;
    }
}

/**
 5
 1 2 2 1 2
 1 5
 */
