package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Problem2473 {

    public static void main(String args[]) throws IOException {
        /** 10억 int / N : 3 ~ 5000
         5
         -2 6 -97 -6 98

         7
         -2 -3 -24 -6 98 100 61

         3
         1000000000 99999999 99999998
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] temp = new int[N];
        int[] array = new int[3];
        Long min = 30000000000L;

        for (int i = 0; i < N; i++) {
            temp[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(temp);

        for (int i = 0; i < N-2 ; i++) {

            int start = i+1;
            int end = N-1;
            long fix = temp[i];

            // 1 2 3 4 5
            // 1 고정

            while (start < end) {
                //계산할때 long타입 주의
                // int + int + long
                long top = temp[end];
                long bottom = temp[start];
                long sum = fix + bottom + top; 

                if (sum == 0) {
                    array[0] = temp[i];
                    array[1] = temp[start];
                    array[2] = temp[end];
                    break;
                }

                if (min > Math.abs(sum)) {
                    array[0] = temp[i];
                    array[1] = temp[start];
                    array[2] = temp[end];
                    min = Math.abs(sum);
                }

                if (sum > 0) end--;
                else start++;
            }
        }

        System.out.print(array[0] + " " + array[1] + " " + array[2]);
    }
}
