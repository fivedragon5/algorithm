package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * https://www.acmicpc.net/problem/18114
 * 1 <= N <= 5,000
 * 1 <= C <= 10^8
 * 1 <= w <= 10^8
 *
 * 1. 최대 3개의 물건을 고를 수 있음
 * 2. 최대 3개의 물건을 골라서 무게가 C가 되는 조합여부
 */
class Problem18114 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] items = new int[N];

        for (int i = 0; i < N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(items);

        // 물건이 1개일 경우, 물건에 같은 무게가 있는지 확인한다.
        if (binarySearch(items, 0, C)) {
            System.out.println(1);
            return;
        }

        // 물건이 2개 or 3개 일 경우
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int weightSum = items[i] + items[j];
                if (weightSum == C) {
                    System.out.println(1);
                    return;
                } else if (binarySearch(items, j + 1, C - weightSum)) {
                    System.out.println(1);
                    return;
                }
            }
        }

        System.out.println(0);
    }

    private static boolean binarySearch(int[] array, int start, int target) {
        int left = start;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) {
                return true;
            } else if(array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
/*
5 5
1 2 2 2 2

5 10
1 2 3 4 5
 */
