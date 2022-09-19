package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 예제
 3 2
 1 2 1 2 1 2
 답 2

 3 6
 10 10 10 10 10 10
 답 31

 4 5
 10 1 10 6 3 4 8 2
 답 24

 5 8
 100 99 60 80 30 20 10 89 99 100
 답 472
 */
class Problem2055 {

    public static void main(String args[]) throws IOException {
        /**
         *
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[2*N];



        for (int i = 0; i < N * 2; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(Arrays.toString(arr));
    }
}
