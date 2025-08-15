package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1197
 *
 * 제한)
 *  1 ≤ V ≤ 10,000
 *  1 ≤ E ≤ 100,000
 *
 * 문제)
 *  1. 그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하기
 *   최소 스패닝 트리
 *      - 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중 그 가중치의 합이 최소인 트리
 *
 * 풀이)
 *  프림 알고리즘
 *   - 최소 신장 트리(MST)구현에 사용되는 알고리즘으로 시작 정점에서 정점을 추가해가며 단계적으로 트리를 확장해가는 개념
 *  1. 정점, 가중치를 List로 저장하기위해 NODE class 선언 (point, cost)
 *  2. List<List<Node>>에 양방향으로 추가
 *  3. 우선순위 큐에 Node의 가중치 순으로 오름차순 정렬
 *  4. 방문하지 않은 정점중 가장 가중치가 낮은 값을 꺼내 우선순위 큐에 삽입
 *  5. 모든 정점을 방문하면 누적 COST 반환
 */
class Problem1197 {

    private static int V, E;
    private static List<List<Node>> GRAPH;

    public static void main(String args[]) throws IOException {
        input();
        int visitedCount = 1;
        int cost = 0;
        boolean[] visited = new boolean[V + 1];
        visited[1] = true;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        List<Node> list = GRAPH.get(1);
        for (Node n : list) {
            pq.add(n);
        }

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (visited[current.point]) {
                continue;
            }
            visited[current.point] = true;
            cost += current.cost;
            visitedCount++;
            if (visitedCount == V) {
                break;
            }
            List<Node> nextNodes = GRAPH.get(current.point);
            for (Node next : nextNodes) {
                if (!visited[next.point]) {
                    pq.add(next);
                }
            }
        }

        System.out.println(cost);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        GRAPH = new ArrayList<>();
        for (int i = 0 ; i <= V; i++) {
            GRAPH.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            GRAPH.get(start).add(new Node(end, cost));
            GRAPH.get(end).add(new Node(start, cost));
        }
    }

    private static class Node implements Comparable<Node> {
        private int point;
        private int cost;

        Node(int point, int cost) {
            this.point = point;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
/*
3 3
1 2 1
2 3 2
1 3 3

 */
