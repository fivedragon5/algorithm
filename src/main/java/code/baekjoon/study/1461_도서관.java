package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1461
 * 제한)
 * 1 <= N, M <= 50
 * 0 <= | 책의 거리 | < 10,000
 *
 * 문제)
 * 1. 모든 책을 제자리에 놔둘때에 걸리는 최소 걸음 수 구하기
 * 2. 모든 책은 위치 '0' 에 있다.
 *
 * 풀이)
 * 1. 0을 기준 으로 책의 위치를 왼쪽, 오른쪽 으로 나눠서 저장
 * 2. 음수는 저장시 양수로 변환 해서 자장 (계산 편의성을 위해)
 * 3. 책 위치 내림차순으로 정렬
 * 4. 책을 두고올때 최소걸음으로 최대한 많은책을 두고 오도록 설계
 * 5. 먼저가는 방향에따라 최소 걸음수가 다를 수 있기에 두 방향을 모두 구한뒤 최소값 출력
 *  - 마지막에 둔 책을 기준으로 걸음수 계산이 끝나기 때문에 마지막에 가장 큰 걸음수를 빼기
 */
class Problem1461 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        List<Integer> rightBooks = new ArrayList<>();
        List<Integer> leftBooks = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 총 책의 수
        int M = Integer.parseInt(st.nextToken()); // 한번에 들 수 있는 책 수

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int book = Integer.parseInt(st.nextToken());
            if (book > 0) {
                // 책의 위치가 오른쪽 일때
                rightBooks.add(book);
            } else if (book < 0) {
                // 책의 위치가 왼쪽 일때 (양수로 전환)
                leftBooks.add(book * -1);
            } else {
                // 0일때는 계산 X;
            }
        }

        // 왼쪽 책 내림 차순 정렬
        leftBooks.sort(Collections.reverseOrder());
        // 오른쪽 책 내림 차순 정렬
        rightBooks.sort(Collections.reverseOrder());

        // 왼쪽 으로 먼저 갔을 경우, 오른쪽 으로 먼저 갔을 경우 에 대해 비교
        int firstLeftCount = countStep(leftBooks, M, false) + countStep(rightBooks, M, true);
        int firstRightCount = countStep(leftBooks, M, true) + countStep(rightBooks, M, false);

        System.out.println(Math.min(firstRightCount, firstLeftCount));
    }

    private static int countStep(List<Integer> books, int booksCarriedAtOnce, boolean isFinished) {
        if (books.isEmpty()) {
            return 0;
        }
        int totalStepCount = 0;
        boolean isPickUp = false;
        int pickUpBookPosition = 0;

        for (int i = 1; i <= books.size(); i++) {
            if (!isPickUp) {
                isPickUp = true;
                pickUpBookPosition = books.get(i - 1);
            }
            if (i % booksCarriedAtOnce == 0 || i == books.size()) {
                totalStepCount += pickUpBookPosition;
                isPickUp = false;
            }
        }
        if (isFinished) {
            return totalStepCount * 2 - books.get(0);
        }
        return totalStepCount * 2;
    }
}
/*
7 2
-37 2 -6 -39 -29 11 -28

1 1
0
 */
