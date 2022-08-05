package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Problem2470 {

    public static void main(String args[]) throws IOException {
        /** 10억 이기때문에 int 사용
         5
         -2 4 -99 -1 98

         2
         100000000 99999999
         */
        // -99 -2 -1 4 98
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] temp = new int[N];

        int start = 0;
        int end = N-1;

        int[] array = new int[3];
        array[2] = 2000000001;

        for (int i = 0; i < N; i++) {
            temp[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(temp);

        while (start < end) {

            if (start >= end) break;

            int top = temp[end];
            int bottom = temp[start];
            int sum = top + bottom;


            if (sum == 0) {
                array[0] = bottom;
                array[1] = top;
                break;
            }

            if (array[2] > Math.abs(sum)) {
                array[0] = bottom;
                array[1] = top;
                array[2] = Math.abs(sum);
            }

            if (sum > 0) end--;
            else start++;
        }

        System.out.println(array[0] + " " + array[1]);
    }
}
