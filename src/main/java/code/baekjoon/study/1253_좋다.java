package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1253
 * 1 <= N <= 2,000
 * |A(i)| <= 1,000,000,000
 *
 * 1. A(i) + A(j) + A(k) = 0
 */
class Problem1253 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        boolean[] checked = new boolean[N];

        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        int answer = 0;

        for (int i = 0; i < N - 1 ; i++) {
            for (int j = i + 1 ; j < N; j++) {
                int target = list.get(i) + list.get(j);
                int index = findTargetIndex(list, target);
                while (index < list.size()) {
                    if (index == i || index == j) {
                        index++;
                    } else if (list.get(index) == target && !checked[index]) {
                        checked[index] = true;
                        index++;
                        answer++;
                    } else {
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static int findTargetIndex(List<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
/*
10
10 2 3 4 5 6 7 8 9 1

3
0 1 1

5
-1 0 1 2 3
 */
