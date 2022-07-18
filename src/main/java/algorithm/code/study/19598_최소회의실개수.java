package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Problem19598 {

    public static void main(String args[]) throws IOException {
        /**
         3
         0 40
         15 30
         5 10
         */

        // 1 <= N <= 1000000
        // 0 <= time <= N^31-1 보다 작음

        ArrayList<int[]> list = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new int[]{start, end});
        }

        ArrayList<Integer> meetingRooms = new ArrayList<>();

        for (int[] meeting : list) {

        }

    }
}
