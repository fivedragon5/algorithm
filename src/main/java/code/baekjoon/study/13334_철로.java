package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/13334
 * 제한)
 *  1 <= n <= 100,000
 *  -100,000,000 <= h(i), o(i) <= 100,000,000
 *  1 <= d <= 200,000,000
 *
 * 문제)
 *  1. h(i), o(i) i번째 사람의 집, 사무실 위치
 *  2. 철로의 길이 d를 적절한 위치에 두었을 경우 가장 많은 사람들의 집, 사무실 위치 모두 포함되도록 철로를 놓기
 *  3. 집, 사무실 위치가 모두 그 선분에 포함되는 사람들의 최대 수를 출력
 *
 * 풀이)
 *  1. h(i) <= o(i) 를 항상 만족하지 않는다. (주의) point1 <= point2 로 기준을 잡기
 *  2. point2를 기준으로 오름차순 정렬
 *  3. 우선순위 큐에 현재 설치한 철도에 포함되는 사람의 point1을 저장
 *   - 반복문을 돌리면서 현재 철도에 포함되지 않는 사람을 point1을 remove
 *  4. 우선순위 큐의 최대값을 출력
 */
class Problem13334 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 사람의 수

        List<Human> humanList = new ArrayList<>();

        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine());
            int point1 = Integer.parseInt(st.nextToken());
            int point2 = Integer.parseInt(st.nextToken());
            humanList.add(new Human(point1, point2));
        }

        st = new StringTokenizer(br.readLine());
        int railLoadLength = Integer.parseInt(st.nextToken()); // 철도의 길이

        System.out.println(solve(humanList, railLoadLength));
    }

    private static int solve(List<Human> humanList, int railLoadLength) {
        int result = 0;
        int humanCount = humanList.size();
        humanList.sort(Comparator.comparingInt(a -> a.point2));

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 0 ; i < humanCount ; i++) {
            int point1 = humanList.get(i).point1;
            int railLoadEndPoint = humanList.get(i).point2;
            int railLoadStartPoint = railLoadEndPoint - railLoadLength;

            if (point1 >= railLoadStartPoint) {
                priorityQueue.add(point1);
            }

            while (!priorityQueue.isEmpty()) {
                if (priorityQueue.peek() < railLoadStartPoint) {
                    priorityQueue.remove();
                } else {
                    break;
                }
            }

            result = Math.max(result, priorityQueue.size());
        }

        return result;
    }

    public static class Human {
        private final int point1;
        private final int point2;

        public Human(int point1, int point2) {
            // 집, 사무실 상관없이 작은값:point1, 큰값:point2에 들어가도록 셋팅
            this.point1 = Math.min(point1, point2);
            this.point2 = Math.max(point1, point2);
        }

        public int getPoint1() {
            return this.point1;
        }

        public int getPoint2() {
            return this.point2;
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
