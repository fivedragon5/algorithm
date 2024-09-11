package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/16498
 * 1 <= A, B, C <= 1,000
 * |A(i)| <= 100,000,000
 */
class Problem16498 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int aCount = Integer.parseInt(st.nextToken());
        int bCount = Integer.parseInt(st.nextToken());
        int cCount = Integer.parseInt(st.nextToken());

        List<Integer> playerACards = new ArrayList<>();
        List<Integer> playerBCards = new ArrayList<>();
        List<Integer> playerCCards = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < aCount; i++) {
            playerACards.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bCount; i++) {
            playerBCards.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cCount; i++) {
            playerCCards.add(Integer.parseInt(st.nextToken()));
        }

        // 정렬
        Collections.sort(playerBCards);
        Collections.sort(playerCCards);

        int result = Integer.MAX_VALUE;

        for (int cardA : playerACards) {
            if (result == 0) break;

            int cardB = findNearest(cardA, playerBCards);
            int cardC = findNearest(cardB, playerCCards);
            int alternativeC = findNearest(cardA, playerCCards);

            // A B C1
            // A B C2
            result = Math.min(result, calculateDifference(cardA, cardB, cardC));
            result = Math.min(result, calculateDifference(cardA, cardB, alternativeC));
        }

        System.out.println(result);
    }

    // 이분탐색
    private static int findNearest(int target, List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        int nearest = list.get((left + right) / 2);

        while (left <= right) {
            int mid = (left + right) / 2;
            int current = list.get(mid);

            if (current == target) {
                return target;
            } else if (current < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

            // 가장 가까운 값 갱신
            if (Math.abs(current - target) < Math.abs(nearest - target)) {
                nearest = current;
            }
        }

        return nearest;
    }

    // 세 값의 최대 최소 차이를 계산하는 메소드
    private static int calculateDifference(int a, int b, int c) {
        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.min(b, c));
        return max - min;
    }
}
/*
5 3 4
1 4 5 8 10
9 6 15
2 3 6 6

1

1 3 4
9
2 5 100
1 6 7 10
 */
