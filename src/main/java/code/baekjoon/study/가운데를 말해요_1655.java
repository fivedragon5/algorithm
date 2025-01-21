package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1655
 * 제한)
 *  1 <= N <= 100,000
 *  -10,000 <= Number(n) <= 10,000
 *
 * 문제)
 *  1. 숫자를 하나 외칠때 마다 지금까지 말한 수 중 중간값을 출력
 *  2. 지금까지 말한 수 가 짝수일 경우 중간값 중 작은 값을 출력
 *
 * 풀이)
 *  1. 우선순위 큐를 2개 만들어서 모래시계 형태로 설계
 *   - 3 2 1 | 6 5 4 (내림차순, 오름차순)
 *  2. 항상 첫벗째 원소가 모든 수의 중간값이 오도록 설계한다.
 *  3. 큐에 값을 추가할땐 아래의 규칙을 따른다
 *   - 원소의 수는 내림차순 우선 순위 큐 >= 오름차순 우선순위
 *   - 내림차순 우선순위 큐의 최대값 <= 오름차순 우선 순위 큐의 최소값
 *  4. 내림차순 우선순위 큐의 최대값을 StringBuilder에 하나씩 추가하며 마지막에 출력
 */
class Problem1655 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 주어진 수 의 갯수

        PriorityQueue<Integer> bigSizeHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> smallSizeHeap = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();

        // 최초 1회 저장
        st = new StringTokenizer(br.readLine());
        int number = Integer.parseInt(st.nextToken());
        bigSizeHeap.add(number);
        sb.append(number);

        for (int i = 1 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            number = Integer.parseInt(st.nextToken());
            if (i % 2 == 0) {
                bigSizeHeap.add(number);
            } else {
                smallSizeHeap.add(number);
            }
            setPriorityQueue(bigSizeHeap, smallSizeHeap);
            sb.append("\n").append(bigSizeHeap.peek());
        }
        System.out.println(sb);
    }

    private static void setPriorityQueue(PriorityQueue<Integer> bigSizeHeap, PriorityQueue<Integer> smallSizeHeap) {
        int bigSizeHeapMaxNumber = bigSizeHeap.peek();
        int smallSizeHeapMinNumber = smallSizeHeap.peek();
        if (bigSizeHeapMaxNumber > smallSizeHeapMinNumber) {
            bigSizeHeap.remove();
            bigSizeHeap.add(smallSizeHeapMinNumber);
            smallSizeHeap.remove();
            smallSizeHeap.add(bigSizeHeapMaxNumber);
        }
    }
}
/*
7
1
5
2
10
-99
7
5

1
1
2
2
2
2
5
 */
