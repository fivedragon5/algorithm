package code.leetcode.daily.year2025.month03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/?envType=daily-question&envId=2025-03-23
 *
 * 제한)
 *  1 <= n <= 200
 *  n - 1 <= roads.length <= n * (n - 1) / 2
 *  roads[i].length == 3
 *  0 <= ui, vi <= n - 1
 *  1 <= time(i) <= 10^9
 *  ui != vi
 *  There is at most one road connecting any two intersections.
 *  You can reach any intersection from any other intersection.
 *
 * 문제)
 *  1. n개의 도시와 각도시 사이에는 최대 1개의 양방향 도로가 있다.
 *  2. roads[i]는 [u(i), v(i), time(i)]로 이루어저 있다
 *   = u(i) ~ v(i) 사이에 time(i)분이 걸리는걸 의미한다
 *  3. 가장 짧은 시간내에 교차로 0 -> n-1 로 이동할 수 있는 방법의 수를 반환하기
 *   - 반환한 수에 10^9 + 7로 나눈 나머지를 반환
 *
 * 풀이)
 *  다익스트라 알고리즘 사용
 *
 *  dist[i] : i까지의 최단 거리를 저장, 초기값 Long.MAX_VALUE
 *  ways[i] : i정점 까지의 최단 경로의 개수를 저장, 시작 정점(0)의 경로는 1로 초기화
 *  PriorityQueue를 사용하여 최소 거리를 가진 정점을 먼저 방문
 *
 *  1.우선순위 큐에서 현재 정점을 꺼내고, 현재 정점의 인접 정점을 방문
 *  2.인접 정점까지의 새로운 거리를 계산, dist 배열에 값과 비교
 *  3.새로운 거리가 더 짧으면 dist 배열을 갱신하고, ways 배열을 현재 정점의 경로 개수로 설정
 *  4.새로운 거리가 현재 저장된 거리와 같으면 ways 배열에 현재 정점의 경로 개수를 더하기
 *  ways[n-1] 을 반환하여 도착 정점 까지의 최단 경로의 개수를 반환
 */

public class Question_20250323 {
    public static void main(String args[]) throws IOException {
        int n = 7; int[][] roads = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
        System.out.println(countPaths(n, roads));

        int n2 = 2; int[][] roads2 = {{1,0,10}};
        System.out.println(countPaths(n2, roads2));

    }

    public static int countPaths(int n, int[][] roads) {
        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int x1 = road[0];
            int x2 = road[1];
            long time = road[2];

            graph.get(x1).add(new Node(x2, time));
            graph.get(x2).add(new Node(x1, time));
        }

        long[] dist = new long[n]; // 각 정점까지의 최소값을 정의
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        int[] ways = new int[n]; // 각 정점까지의 최단 경로의 개수를 저장, 시장점점 0의 경로는 1로 초기화
        ways[0] = 1;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingLong(a -> a.time));

        priorityQueue.offer(new Node(0,0));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            int currentPoint = currentNode.point;
            long currentTime = currentNode.time;

            // 우선순위 큐에서 꺼낸 정점의 거리가 현재 dist에 저장된 값보다 클 경우 무시 처리
            if (currentTime > dist[currentPoint]) {
                continue;
            }

            for (Node neighbor : graph.get(currentPoint)) {
                int neighborPoint = neighbor.point;
                // 현재까지 누적된 시간에 인접 시간을 더해준다
                long newTime = neighbor.time + currentTime;

                // 누적된 시간과 dist배열에 있는 시간을 비교해서 현재 시간이 작을 경우 최단시간 갱신처리
                if (dist[neighborPoint] > newTime) {
                    dist[neighborPoint] = newTime;
                    // 최단 경로 수 갱신
                    ways[neighborPoint] = ways[currentPoint];
                    priorityQueue.add(new Node(neighborPoint, newTime));
                } else if (newTime == dist[neighborPoint]) {
                    ways[neighborPoint] = (ways[neighborPoint] + ways[currentPoint]) % 1000000007;
                }
            }
        }
        return ways[n-1];
    }

    public static class Node {
        private final int point;
        private final long time;

        public Node(int point, long time) {
            this.point = point;
            this.time = time;
        }

        public int getPoint() {
            return this.point;
        }

        public long getTime() {
            return this.time;
        }
    }
}
