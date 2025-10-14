package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-cost-walk-in-weighted-graph/?envType=daily-question&envId=2025-03-20
 *
 * 제한)
 *  2 <= n <= 10^5
 *  0 <= edges.length <= 10^5
 *  edges[i].length == 3
 *  0 <= ui, vi <= n - 1
 *  ui != vi
 *  0 <= wi <= 10^5
 *  1 <= query.length <= 10^5
 *  query[i].length == 2
 *  0 <= si, ti <= n - 1
 *  si != ti
 *
 * 문제)
 *  1. edges[i] = {ui, vi, wi}
 *   = ui ~ vi로 연결돤 간선이 있고 wi만큼의 가중치를 가짐
 *  2. n : n개의 노드
 *  3. query[i] = {si, ei}
 *   = si ~ ei 로 가는데 필요한 최소한의 비용을 구하기
 *  4. 비용 계산
 *   - 이동 경로에 있는 간선들의 가중치를 비트 AND 연산한 값
 *  5. 이동할 수 없을 경우에는 -1 을 반환
 *
 * 풀이)
 *  연결된 노드들간의 컴포넌트화 및 AND 연산 값 유지 Union-Find 데이터 구조
 *  1. 각 노드를 -1(이진수에서 모든 비트가 1) AND 값을 가진 컴포넌트로 초기화  minPathCost[]
 *  2. 각 간선 처리
 *   - 간선의 끝점을 포함하는 컴포넌트의 루트 노드 찾기
 *   - 대상 컴포넌트의 AND 값을 간선 가중치로 업데이트
 *   - 끝점이 서로 다른 컴포넌트에 있는 경우, 컴포넌트 병합 및 AND 값을 결합
 *  3. 쿼리 수행
 *   - 시작, 목표 점이 같을 경우 0
 *   - Root가 다를 경우 -1
 *   - 그렇지 않다면 AND 연산한 값을 반환
 */

public class Question_20250320 {
    public static void main(String args[]) throws IOException {
        int n = 5;
        int[][] edges = new int[][]{{0,1,7},{1,3,7},{1,2,1}};
        int[][] query = new int[][]{{0,3},{3,4}};
        System.out.println(minimumCost(n, edges, query));

        int n2 = 3;
        int[][] edges2 = new int[][]{{0,2,7},{0,1,15},{1,2,6},{1,2,1}};
        int[][] query2 = new int[][]{{1, 2}};
        System.out.println(minimumCost(n2, edges2, query2));
    }

    public static int[] minimumCost(int n, int[][] edges, int[][] query) {
        int[] parent = new int[n];
        int[] minPathCost = new int[n];
        Arrays.fill(minPathCost, -1);

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < edges.length; i++) {
            int start = edges[i][0];
            int target = edges[i][1];
            int weight = edges[i][2];
            int startRoot = findRoot(parent, start);
            int targetRoot = findRoot(parent, target);

            minPathCost[targetRoot] &= weight;

            if (startRoot != targetRoot) {
                minPathCost[targetRoot] &= minPathCost[startRoot];
                parent[startRoot] = targetRoot;
            }
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int start = query[i][0];
            int end = query[i][1];
            if (start == end) {
                answer[i] = 0;
            } else if (findRoot(parent, start) != findRoot(parent, end)) {
                answer[i] = -1;
            } else {
                answer[i] = minPathCost[findRoot(parent, start)];
            }
        }

        return answer;
    }

    private static int findRoot(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = findRoot(parent, parent[node]);
        }
        return parent[node];
    }
}
