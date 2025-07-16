package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11779
 *
 * 제한)
 *  1 <= n <= 1,000
 *  1 <= m <= 100,000
 *  0 <= 버스 비용 <= 100,000
 *
 * 문제)
 *  1. n개의 도시, 한 도시에서 출발해서 다른 도시에 도착하는 m개의 버스가 있다.
 *  2. A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용의 최소 비용, 경로를 출력
 *   - 항상 시작점에서 도착점으로 경로가 존재
 *
 * 풀이)
 *  풀이 1
 *   - DFS -> 시간 초과 발생
 *   - Memozization -> 시간 초과
 *   - 모든 경로 탐색
 *  풀이 2
 *   - 다익스트라자 알고리즘 적용(우선순위 큐로 최단 경로만 탐색) + 경로 역추적 PATH
 *   1. NODE class를 만들어 city, cost 저장
 *    - 우선순위 큐 사용시 COST 비교를 위해 compareTo 오버라이드
 *   2. 인접 리스트 List<Node> 저장
 *   3. 시작지점(START_POINT) 부터 다익스트라 알고리즘 갱신 (적은 비용 COST)
 *   4. 적은 비용 갱신시 이전 city 저장 ( history 추적 용 )
 *   5. END_PONT부터 START_POINT 까지 역추적 PATH[END_POINT]
 *   6. 역추적한 List를 뒤집어서 경로를 sb담아 출력
 */
class Problem11779 {

    private static int n, m;
    private static int START_POINT, END_POINT;
    private static List<List<Node>> NODE_LIST = new ArrayList<>();
    private static int[] COSTS, PATH;

    public static void main(String args[]) throws IOException {
        input();

        dijkstra(START_POINT);

        // 정답출력하는 부분
        StringBuilder sb = new StringBuilder();
        sb.append(COSTS[END_POINT] + "\n");

        List<Integer> history = new ArrayList<>();
        for (int at = END_POINT; at != 0; at = PATH[at]) {
            history.add(at);
            if (at == START_POINT) {
                break;
            }
        }

        sb.append(history.size() + "\n");
        Collections.reverse(history);
        for (int city : history) {
            sb.append(city + " ");
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.cost > COSTS[current.city]) {
                continue;
            }

            for (Node next : NODE_LIST.get(current.city)) {
                int newCost = current.cost + next.cost;
                if (newCost < COSTS[next.city]) {
                    COSTS[next.city] = newCost;
                    PATH[next.city] = current.city;
                    pq.offer(new Node(next.city, newCost));
                }
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        COSTS = new int[n + 1];
        Arrays.fill(COSTS, Integer.MAX_VALUE);
        PATH = new int[n + 1];
        NODE_LIST.add(new ArrayList<>());

        for (int i = 0; i <= n; i++) {
            NODE_LIST.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            NODE_LIST.get(start).add(new Node(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        START_POINT = Integer.parseInt(st.nextToken());
        END_POINT = Integer.parseInt(st.nextToken());
    }

    private static class Node implements Comparable<Node> {
        int city, cost;

        Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
/*
5
8
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
1 5

4
3
1 3 5

 */
