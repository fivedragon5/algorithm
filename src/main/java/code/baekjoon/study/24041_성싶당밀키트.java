package code.baekjoon.study;

import java.io.*;
import java.util.*;

/**
 * 접근)
 * 1. 며칠 후 의 범위가 넓음 -> 이분 탐색을 사용 (2e9)
 * 2. 며칠 후 에 따라 빼야할 재료가 달라지기 때문에 탐색 후 정렬을 한뒤 K개를 빼주는 식으로 접근했다.
 *  -> 우선순위 큐 리버스 사용
 *  
 * 풀이)
 * 1. x일을 구하는 과정에서 이분탐색 사용
 *  - x일 고정
 * 2. 밀키트 재료들을 순회 하며 x일 일때의 재료들의 세균수를 구함
 *  - 이때 중요하지 않은 재료(O = 1) 우선순위 큐에 추가
 * 3. 전체 세균 수 에서 K개의 세균 수 를 빼줌
 *  - 우선 순위큐에서 poll 하여 뺌
 * 4. x일 일때의 세균수의 최대값 출력
 * 
 * 제한)
 * 1 <= N <= 200,000
 * 1 <= G <= 10^9
 * 0 <= K <= N
 * 1 <= Si <= 10^9
 * 1 <= Li <= 10^9
 * O = {0,1}
 * 세균 수 : S(i) * max(1, x - L(i))
 */
class Problem24041 {
    public static void main(String args[]) throws IOException {
        long answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] itemList = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            itemList[i][0] = Integer.parseInt(st.nextToken());
            itemList[i][1] = Integer.parseInt(st.nextToken());
            itemList[i][2] = Integer.parseInt(st.nextToken());
        }

        long left = 1;
        long right = 2 * (int) Math.pow(10, 9);

        while (left <= right) {
            long mid = (left + right) / 2;
            long germCount = 0;
            PriorityQueue<Long> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

            for (int[] item : itemList) {
                long count = item[0] * Math.max(1L, mid - item[1]);
                if (item[2] == 1) priorityQueue.add(count);
                germCount += count;
            }

            for (int i = 0; i < K & !priorityQueue.isEmpty(); i++) {
                germCount -= priorityQueue.poll();
            }

            if (germCount > G) right = mid - 1;
            else {
                answer = mid;
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }
}
/*
4 36 0
2 14 1
3 8 1
5 12 1
7 10 0

12

4 36 1
2 14 1
3 8 1
5 12 1
7 10 0

13

4 36 2
2 14 1
3 8 1
5 12 1
7 10 0

14

4 200000000 1
200 14 1
500 8 1
5 12 1
1000 10 0

4 36 5
2 14 1
3 8 1
5 12 1
7 10 0
 */

