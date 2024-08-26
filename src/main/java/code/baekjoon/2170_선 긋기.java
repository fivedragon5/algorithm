package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2170
 * 1 <= N <= 1,000,000
 * -1,000,000,000 <= x <= y <= 1,000,000,000
 */
class Problem2170 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new int[]{x, y});
        }

        Collections.sort(list, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        int start = list.get(0)[0];
        int end = list.get(0)[1];
        int totalLineLength = 0;
        for (int[] line : list) {
            int currentX = line[0];
            int currentY = line[1];
            if (currentX <= end) {
                end = Math.max(end, currentY);
            } else {
                totalLineLength += end - start;
                start = currentX;
                end = currentY;
            }
        }
        totalLineLength += end - start;
        System.out.println(totalLineLength);
    }
}
/*
4
1 3
2 5
3 5
6 7

5
 */
