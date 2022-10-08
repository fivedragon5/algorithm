package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Problem3190 {

    public static void main(String args[]) throws IOException {
        /**
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());

        int[][] apples = new int[K][2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            apples[i][0] = Integer.parseInt(st.nextToken());
            apples[i][1] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());

        int[] seconds = new int[L];
        String[] directions = new String[L];


        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            seconds[i] = Integer.parseInt(st.nextToken());
            directions[i] = st.nextToken();
        }

        System.out.println(Arrays.toString(apples));
        System.out.println(Arrays.toString(seconds));
        System.out.println(Arrays.toString(directions));
    }
}

/**
 *
 ex)
 6
 3
 3 4
 2 5
 5 3
 3
 3 D
 15 L
 17 D

 */
