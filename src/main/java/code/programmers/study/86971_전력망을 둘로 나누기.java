package code.programmers.study;

import java.util.ArrayList;
import java.util.List;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/86971
 *
 * 제한)
 *  n은 2 이상 100 이하인 자연수입니다.
 *  wires는 길이가 n-1인 정수형 2차원 배열입니다.
 *      wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며, 이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
 *      1 ≤ v1 < v2 ≤ n 입니다.
 *      전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.
 *
 * 문제)
 *  1. n개의 송전탑이 전선을 토해 하나의 트리 형태로 연결되어 있다.
 *  2. 이 전선들 중 하나를 끊어 현재의 전력망 네트워크를 2개로 분할하려고 한다.
 *  3. 이 때, 두 개의 전력망 네트워크에 포함된 송전탑의 개수 차이가 최소가 되는 경우의 송전탑 개수 차이를 반환하기
 *
 * 풀이)
 *  DFS
 *  1. 그래프를 인접 리스트 형태로 표현한다.
 *  2. 각 전선(wire)을 끊고, 끊은 전선이 연결된 두 송전탑 중 하나를 시작점으로 DFS를 수행하여 해당 송전탑이 포함된 네트워크의 송전탑 개수를 구한다.
 *  3. 끊은 전선이 연결된 두 송전탑의 개수를 이용하여 두 네트워크의 송전탑 개수 차이를 계산한다.
 *   - 최소값 갱신
 */
class Lesson86971 {
    public static void main(String[] args) {
        int n = 9;
        int[][] wires = new int[][]{
                {1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}
        };
        System.out.println(solution(n, wires));

        int n2 = 4;
        int[][] wires2 = new int[][]{
                {1,2},{2,3},{3,4}
        };
        System.out.println(solution(n2, wires2));

        int n3 = 7;
        int[][] wires3= new int[][]{
                {1,2},{2,7},{3,7},{3,4},{4,5},{6,7}
        };
        System.out.println(solution(n3, wires3));
    }

    public static int solution(int n, int[][] wires) {
        int minDifference = Integer.MAX_VALUE;
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++)  {
            graph[i] = new ArrayList<>();
        }

        for (int[] wire : wires) {
            int u = wire[0];
            int v = wire[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 0; i < wires.length; i++) {
            int u = wires[i][0];
            int v = wires[i][1];

            graph[u].remove((Integer)v);
            graph[v].remove((Integer)u);

            boolean[] visited = new boolean[n + 1];
            int count = dfs(graph, visited, u);

            graph[u].add(v);
            graph[v].add(u);

            int difference = Math.abs(n - 2 * count);
            minDifference = Math.min(minDifference, difference);
        }

        return minDifference;
    }

    private static int dfs(List<Integer>[] graph, boolean[] visited, int node) {
        visited[node] = true;
        int count = 1;

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                count += dfs(graph, visited, neighbor);
            }
        }
        return count;
    }
}
