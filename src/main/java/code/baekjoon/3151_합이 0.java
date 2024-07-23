package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * https://www.acmicpc.net/problem/3151
 * 1 <= N <= 10,000
 * -10000 <= A(i) <= 10,000
 *
 * 1. A(i) + A(j) + A(k) = 0
 */
class Problem3151 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] array = new int[N];
        long answer = 0L;

        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        // 2개의 수를 고정 후 1개 찾기
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N ; j++) {
                int sum = array[i] + array[j];
                // 범위 탐색
                int lower = binarySearchLower(array, j + 1, -sum);
                int upper = binarySearchUpper(array, j + 1, -sum);
                answer += upper - lower;
            }
        }
        System.out.println(answer);
    }

    private static int binarySearchLower(int[] array, int start, int target) {
        int left = start;
        int right = array.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static int binarySearchUpper(int[] array, int start, int target) {
        int left = start;
        int right = array.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (array[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // 수를 Count하기 위해 추가
        if (left < array.length && array[left] == target) {
            left++;
        }

        return left;
    }
}
/*
10
2 -5 2 3 -4 7 -4 0 1 -6

8
-10 5 5 5 5 5 5 5

6
-8 3 3 5 5 5
 */
