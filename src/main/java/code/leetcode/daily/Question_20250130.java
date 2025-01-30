package code.leetcode.daily;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/description/?envType=daily-question&envId=2025-01-30
 * 제한)
 *  1 <= n <= 500
 *  1 <= edges.length <= 104
 *  edges[i].length == 2
 *  1 <= ai, bi <= n
 *  ai != bi
 *  There is at most one edge between any pair of vertices.
 *
 * 문제)
 *  양의 정수 n이 주어지며, 이는 무방향 그래프의 노드 개수를 나타냅니다. 노드는 1부터 n까지 번호가 매겨져 있습니다.
 *
 *  또한, edges라는 2D 정수 배열이 주어집니다. edges[i] = [aᵢ, bᵢ]는 노드 aᵢ와 bᵢ 사이에 양방향 간선이 존재함을 의미합니다.
 * (즉, 주어진 그래프는 연결 그래프가 아닐 수도 있음에 주의해야 합니다.)
 *
 * 이제, 그래프의 노드들을 1부터 시작하는 m개의 그룹으로 나누려고 합니다.
 * 이때, 다음 조건을 만족해야 합니다:
 *
 * 각 노드는 정확히 하나의 그룹에 속해야 합니다.
 * 노드 aᵢ와 bᵢ가 간선으로 직접 연결된 경우, 그룹 차이가 정확히 1이어야 합니다.
 * 즉, aᵢ가 그룹 x에 속해 있다면, bᵢ는 그룹 y = x ± 1에 속해야 합니다.
 *
 * 주어진 조건을 만족하면서 최대 그룹 수(m) 를 반환하세요.
 * 만약 주어진 조건을 만족하는 그룹 나누기가 불가능하다면 -1을 반환하세요.
 *
 * 풀이)
 *  0. 주어진 값들을 Input
 *  1. 인접한 노드들에 색(0,1)을 지정해준다.
 *      - 색을 지정하면서 연결된 Node의 배열을 따로 만들어 둔다(components)
 *      ex) 1(0) -> 2(1) -> 3(0) -> 4(1) -> 5(1)
 *         - 이때 인접한 노드의 색이 연속(0 -> 0)된다면 문제의 조건에 만족하지 않기에 -1을 return 해준다
 *  2. 새로 만들어준 compoents를 DFS로 순회 하면서 max depth를 구한다
 *  3. 구한 max depth를 더해서 출력해준다.
 */

public class Question_20250130 {
    public static void main(String args[]) throws IOException {
        int n = 6;
        int[][] edges = {{1,2},{1,4},{1,5},{2,6},{2,3},{4,6}};
        System.out.println(magnificentSets(n, edges)); // 4

        n = 3;
        int[][] edges2 = {{1,2},{2,3},{3,1}};
        System.out.println(magnificentSets(n, edges2)); // -1
    }

    private static int magnificentSets(int n, int[][] edges) {
        List<List<Integer>> graph = makeGraph(n, edges);
        int[] color = new int[n+1]; // 0,1 지정
        Arrays.fill(color, -1);

        List<List<Integer>> components = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if(color[i] == -1) {
                List<Integer> component = new ArrayList<>();
                if(color[i] == -1 && !setColor(graph, color, i, 0, component)) {
                    return -1;
                }
                components.add(component);
            }
        }
        int answer = 0;
        for (List<Integer> component : components) {
            answer = dfs(graph, component, n);
        }
        return answer;
    }

    private static int dfs(List<List<Integer>> graph, List<Integer> component, int n) {
        int max = 0;
        for (int startNode : component) {
            int[] depth = new int[n+1];
            Arrays.fill(depth, -1);
            Queue<Integer> queue = new LinkedList<>();
            queue.add(startNode);
            depth[startNode] = 0;
            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int nextNode : graph.get(node)) {
                    if (depth[nextNode] == -1) {
                        depth[nextNode] = depth[node] + 1;
                        max = Math.max(max, depth[nextNode]);
                        queue.add(nextNode);
                    }
                }
            }
        }
        // Depth + 1 == 그룹 수
        return max + 1;
    }

    // 인접한 노드들의 색을 지정, 인접한 노드를 만족하지 못 할 경우 false | 시작지점 Component 생성
    private static boolean setColor(List<List<Integer>> graph, int[] colorList, int node, int color, List<Integer> component) {
        colorList[node] = color;
        component.add(node);
        for (int currentNode : graph.get(node)) {
            if (colorList[currentNode] == color) {
                return false;
            }
            if (colorList[currentNode] == -1 && !setColor(graph, colorList, currentNode, 1 - color, component)) {
                return false;
            }
        }
        return true;
    }

    private static List<List<Integer>> makeGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0 ; i <= n ; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        return graph;
    }
}
